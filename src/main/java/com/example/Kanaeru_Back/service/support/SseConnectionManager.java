package com.example.Kanaeru_Back.service.support;

import com.example.Kanaeru_Back.model.DmMessagesSchema;
import com.example.Kanaeru_Back.util.SseEventBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * SSE接続を管理するサービスクラス
 */
@Slf4j
@Service
public class SseConnectionManager {

    // ユーザーIDごとのSSE接続リストを管理
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<SseEmitter>> userConnections = new ConcurrentHashMap<>();

    // SSE接続のタイムアウト時間（30分）
    private static final long SSE_TIMEOUT = 30 * 60 * 1000L;

    /**
     * 新しいSSE接続を追加する
     * 
     * @param userId ユーザーID
     * @return SseEmitter
     */
    public SseEmitter addConnection(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            log.error("SSE connection attempted with null or empty userId");
            throw new IllegalArgumentException("ユーザーIDが指定されていません");
        }

        log.info("SSE connection requested for userId: {}", userId);

        try {
            SseEmitter emitter = new SseEmitter(SSE_TIMEOUT);

            // ユーザーの接続リストを取得または作成
            userConnections.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(emitter);

            // 接続完了時とタイムアウト時の処理
            emitter.onCompletion(() -> {
                log.info("SSE connection completed for userId: {}", userId);
                removeConnection(userId, emitter);
            });
            emitter.onTimeout(() -> {
                log.warn("SSE connection timeout for userId: {}", userId);
                removeConnection(userId, emitter);
            });
            emitter.onError((ex) -> {
                log.error("SSE connection error for userId: {}", userId, ex);
                removeConnection(userId, emitter);
            });

            // 接続確立イベントを送信
            try {
                emitter.send(SseEventBuilder.buildConnectEvent());
                log.debug("SSE connect event sent successfully for userId: {}", userId);
            } catch (IOException e) {
                log.error("Failed to send connect event for userId: {}", userId, e);
                removeConnection(userId, emitter);
                throw new RuntimeException("SSE接続の初期化に失敗しました", e);
            }

            log.info("SSE connection established successfully for userId: {}. Total connections: {}",
                    userId, getConnectionCount(userId));
            return emitter;

        } catch (Exception e) {
            log.error("Failed to create SSE connection for userId: {}", userId, e);
            throw new RuntimeException("SSE接続の作成に失敗しました", e);
        }
    }

    /**
     * SSE接続を削除する
     * 
     * @param userId  ユーザーID
     * @param emitter 削除するSseEmitter
     */
    private void removeConnection(String userId, SseEmitter emitter) {
        try {
            CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
            if (connections != null) {
                boolean removed = connections.remove(emitter);
                if (removed) {
                    log.debug("SSE connection removed for userId: {}. Remaining connections: {}",
                            userId, connections.size());
                }
                if (connections.isEmpty()) {
                    userConnections.remove(userId);
                    log.info("All SSE connections removed for userId: {}", userId);
                }
            }
        } catch (Exception e) {
            log.error("Error removing SSE connection for userId: {}", userId, e);
        }
    }

    /**
     * 特定のユーザーにメッセージイベントを送信する
     * 
     * @param userId        ユーザーID
     * @param messageSchema メッセージデータ
     */
    public void sendMessageToUser(String userId, DmMessagesSchema messageSchema) {
        if (userId == null || messageSchema == null) {
            log.warn("Invalid parameters for sendMessageToUser: userId={}, messageSchema={}",
                    userId, messageSchema);
            return;
        }

        CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
        if (connections == null || connections.isEmpty()) {
            log.debug("No SSE connections found for userId: {}", userId);
            return;
        }

        log.debug("Sending message event to {} connections for userId: {}", connections.size(), userId);

        final int[] counters = { 0, 0 }; // [successCount, failureCount]

        connections.removeIf(emitter -> {
            try {
                emitter.send(SseEventBuilder.buildMessageEvent(messageSchema));
                counters[0]++; // successCount
                return false; // 送信成功、削除しない
            } catch (IOException e) {
                log.warn("Failed to send message event to connection for userId: {}", userId, e);
                counters[1]++; // failureCount
                return true; // 送信失敗、削除する
            }
        });

        log.info("Message event sent to userId: {}. Success: {}, Failed: {}",
                userId, counters[0], counters[1]);
    }

    /**
     * 特定のユーザーに既読イベントを送信する
     * 
     * @param userId    ユーザーID
     * @param messageId メッセージID
     */
    public void sendReadEventToUser(String userId, String messageId) {
        CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
        if (connections != null) {
            connections.removeIf(emitter -> {
                try {
                    // フロントエンドが期待するデータ構造に合わせて既読イベントデータを構築
                    // 日本時間で現在時刻を取得し、LocalDateTime形式で送信
                    java.time.ZonedDateTime japanTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Tokyo"));
                    java.util.Map<String, Object> readEventData = java.util.Map.of(
                            "messageSeq", messageId,
                            "readAt", japanTime.toLocalDateTime().toString());
                    emitter.send(SseEventBuilder.buildReadEvent(readEventData));
                    return false; // 送信成功、削除しない
                } catch (IOException e) {
                    return true; // 送信失敗、削除する
                }
            });
        }
    }

    /**
     * 特定のユーザーに詳細な既読イベントを送信する
     * 
     * @param userId      ユーザーID
     * @param messageSeq  メッセージシーケンス番号
     * @param senderId    メッセージ送信者ID
     * @param recipientId メッセージ受信者ID
     * @param readAt      既読日時
     */
    public void sendDetailedReadEventToUser(String userId, String messageSeq, String senderId, String recipientId,
            String readAt) {
        CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
        if (connections != null) {
            connections.removeIf(emitter -> {
                try {
                    // フロントエンドが期待するデータ構造に合わせて既読イベントデータを構築
                    // readAtがZonedDateTime文字列の場合はLocalDateTime形式に変換
                    String formattedReadAt = readAt;
                    try {
                        // ZonedDateTime文字列をパースしてLocalDateTime形式に変換
                        java.time.ZonedDateTime zonedDateTime = java.time.ZonedDateTime.parse(readAt);
                        // 日本時間に変換してからLocalDateTime形式の文字列に変換
                        formattedReadAt = zonedDateTime.withZoneSameInstant(java.time.ZoneId.of("Asia/Tokyo"))
                                .toLocalDateTime().toString();
                    } catch (Exception e) {
                        // パースに失敗した場合、LocalDateTime文字列として再試行
                        try {
                            java.time.LocalDateTime localDateTime = java.time.LocalDateTime.parse(readAt);
                            formattedReadAt = localDateTime.toString();
                        } catch (Exception ex) {
                            // 両方失敗した場合はそのまま使用
                            formattedReadAt = readAt;
                        }
                    }

                    java.util.Map<String, Object> readEventData = java.util.Map.of(
                            "messageSeq", messageSeq,
                            "senderId", senderId,
                            "recipientId", recipientId,
                            "readAt", formattedReadAt);
                    emitter.send(SseEventBuilder.buildReadEvent(readEventData));
                    return false; // 送信成功、削除しない
                } catch (IOException e) {
                    return true; // 送信失敗、削除する
                }
            });
        }
    }

    /**
     * 特定のユーザーにエラーイベントを送信する
     * 
     * @param userId       ユーザーID
     * @param errorMessage エラーメッセージ
     */
    public void sendErrorToUser(String userId, String errorMessage) {
        CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
        if (connections != null) {
            connections.removeIf(emitter -> {
                try {
                    emitter.send(SseEventBuilder.buildErrorEvent(errorMessage));
                    return false; // 送信成功、削除しない
                } catch (IOException e) {
                    return true; // 送信失敗、削除する
                }
            });
        }
    }

    /**
     * 全ユーザーにハートビートを送信する
     */
    public void sendHeartbeatToAll() {
        userConnections.forEach((userId, connections) -> {
            connections.removeIf(emitter -> {
                try {
                    emitter.send(SseEventBuilder.buildHeartbeatEvent());
                    return false; // 送信成功、削除しない
                } catch (IOException e) {
                    return true; // 送信失敗、削除する
                }
            });
        });
    }

    /**
     * 特定のユーザーの接続数を取得する
     * 
     * @param userId ユーザーID
     * @return 接続数
     */
    public int getConnectionCount(String userId) {
        CopyOnWriteArrayList<SseEmitter> connections = userConnections.get(userId);
        return connections != null ? connections.size() : 0;
    }

    /**
     * 全体の接続数を取得する
     * 
     * @return 全体の接続数
     */
    public int getTotalConnectionCount() {
        return userConnections.values().stream()
                .mapToInt(CopyOnWriteArrayList::size)
                .sum();
    }
}
