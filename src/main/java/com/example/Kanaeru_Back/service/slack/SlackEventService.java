package com.example.Kanaeru_Back.service.slack;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.entity.SlackMessageEntity;
import com.example.Kanaeru_Back.entity.SlackUserMappingEntity;
import com.example.Kanaeru_Back.entity.SlackWorkspaceEntity;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import com.example.Kanaeru_Back.repository.SlackMessageRepository;
import com.example.Kanaeru_Back.repository.SlackUserMappingRepository;
import com.example.Kanaeru_Back.repository.SlackWorkspaceRepository;
import com.example.Kanaeru_Back.util.SlackTokenCipher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SlackEventService {

    private static final Logger logger = LoggerFactory.getLogger(SlackEventService.class);

    @Value("${slack.signing-secret:}")
    private String signingSecret;

    // ★ 追加：ローカル開発用 署名検証スキップフラグ
    @Value("${slack.skip-verification:false}")
    private boolean skipVerification;

    @Value("${app.frontend.url:http://localhost:5180}")
    private String frontendUrl;

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Autowired
    private SlackMessageRepository slackMessageRepository;

    @Autowired
    private SlackUserMappingRepository slackUserMappingRepository;

    @Autowired
    private SlackWorkspaceRepository slackWorkspaceRepository;

    @Autowired
    private SlackTokenCipher slackTokenCipher;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 引数順: signature → timestamp → body（コントローラーと統一）
     *
     * @param signature X-Slack-Signature ヘッダーの値
     * @param timestamp X-Slack-Request-Timestamp ヘッダーの値
     */
    public boolean verifySignature(String signature, String timestamp, String body) {
        // ★ ローカル開発用スキップ（本番環境では application-local.properties のみで有効化）
        if (skipVerification) {
            logger.warn("署名検証をスキップしています（開発環境専用）");
            return true;
        }

        if (signingSecret == null || signingSecret.isEmpty()) {
            logger.warn("slack.signing-secret が未設定のため署名検証をスキップします");
            return true;
        }

        try {
            // リプレイアタック防止：5分以上古いリクエストを拒否
            long requestTime = Long.parseLong(timestamp);
            long now = System.currentTimeMillis() / 1000;
            if (Math.abs(now - requestTime) > 300) {
                logger.warn("Slackリクエストのタイムスタンプが古すぎます: {}", timestamp);
                return false;
            }

            String baseString = "v0:" + timestamp + ":" + body;
            String expectedSig = "v0=" + hmacSha256Hex(signingSecret, baseString);

            return MessageDigest.isEqual(
                    expectedSig.getBytes(StandardCharsets.UTF_8),
                    signature.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            logger.error("Slack署名検証中にエラー", e);
            return false;
        }
    }

    /**
     * Slackイベントを非同期で処理する。
     * Slackは3秒以内にHTTP 200を受信しないとリトライするため、
     * コントローラーで即座に200を返した後にこのメソッドを呼ぶ。
     *
     * @param body リクエストボディの生文字列
     */
    public void processEventAsync(String body) {
        CompletableFuture.runAsync(() -> {
            try {
                processEvent(body);
            } catch (Exception e) {
                logger.error("Slackイベント非同期処理に失敗", e);
            }
        });
    }

    /**
     * Slackイベントの本処理。
     * app_mention イベントのみ処理し、他のイベントは無視する。
     */
    private void processEvent(String body) throws Exception {
        JsonNode root = objectMapper.readTree(body);

        String type = root.path("type").asText();
        if (!"event_callback".equals(type)) {
            return;
        }

        String teamId = root.path("team_id").asText();
        JsonNode event = root.path("event");
        String eventType = event.path("type").asText();

        // ワークスペースのアンインストール／トークン失効通知（app_mention以外の特殊イベント）
        if ("app_uninstalled".equals(eventType) || "tokens_revoked".equals(eventType)) {
            handleWorkspaceUninstall(teamId);
            return;
        }

        if (!"app_mention".equals(eventType)) {
            return;
        }

        // team_id からワークスペースを特定（未連携・アンインストール済みなら無視）
        Optional<SlackWorkspaceEntity> workspaceOpt = slackWorkspaceRepository.findByTeamIdAndDelFlg(teamId, "0");
        if (workspaceOpt.isEmpty()) {
            logger.warn("有効なSlackワークスペースが見つかりません team_id={}", teamId);
            return;
        }
        SlackWorkspaceEntity workspace = workspaceOpt.get();
        String workspaceId = workspace.getWorkspaceId();
        String botToken = slackTokenCipher.decrypt(workspace.getBotToken());

        String slackUserId = event.path("user").asText();
        String slackTs     = event.path("ts").asText();
        String channelId   = event.path("channel").asText();
        // :emoji: → Unicode変換を行ってから保存・パースする
        String rawText     = SlackEmojiConverter.convert(event.path("text").asText());

        // スレッド返信先（スレッド内メンションの場合は thread_ts、通常は ts を使用）
        String replyTs = event.has("thread_ts")
                ? event.path("thread_ts").asText()
                : slackTs;

        // 重複チェック（Slackのリトライによる二重登録を防止）
        if (slackMessageRepository.existsByWorkspaceIdAndSlackTs(workspaceId, slackTs)) {
            logger.info("Slack ts が重複しているためスキップ: {}", slackTs);
            return;
        }

        // SLACK_USER_MAPPINGS でkanaeruユーザーを特定（ワークスペース内で一意）
        Optional<SlackUserMappingEntity> mappingOpt =
                slackUserMappingRepository.findByWorkspaceIdAndSlackUserIdAndDelFlg(workspaceId, slackUserId, "0");
        if (mappingOpt.isEmpty()) {
            logger.warn("Slackユーザーに対応するkanaeruユーザーが見つかりません: {}", slackUserId);
            sendSlackMessage(channelId, replyTs,
                    "kanaeruに目標を登録するためには、SlackメンバーIDの紐づけが必要です。\nkanaeruの設定画面でSlackメンバーIDを登録してください。",
                    botToken);
            return;
        }

        String userId = mappingOpt.get().getUserId();

        // @メンション除去 → 箇条書きパース
        String cleanedText = removeMention(rawText);
        List<String> goals = parseBulletPoints(cleanedText);

        if (goals.isEmpty()) {
            sendSlackMessage(channelId, replyTs,
                    "目標が見つかりませんでした。箇条書きで入力してください（・、-、* など）",
                    botToken);
            return;
        }

        LocalDate      today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        LocalDateTime  now   = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));

        // SLACK_MESSAGES に原文を保存（RAW_TEXT は4000文字上限）
        SlackMessageEntity slackMsg = new SlackMessageEntity();
        slackMsg.setSlackMessageId(UUID.randomUUID().toString());
        slackMsg.setUserId(userId);
        slackMsg.setWorkspaceId(workspaceId);
        slackMsg.setSlackTs(slackTs);
        slackMsg.setChannelId(channelId);
        slackMsg.setRawText(rawText.length() > 4000 ? rawText.substring(0, 4000) : rawText);
        slackMsg.setPostedAt(now);
        slackMsg.setGoalCount(goals.size());
        slackMsg.setDelFlg("0");
        slackMsg.setCreatedAt(now);
        slackMsg.setUpdatedAt(now);
        slackMessageRepository.save(slackMsg);

        // 今日の最大ソート順を取得して次の番号を決定
        int nextSortOrder = dailyGoalRepository
                .findMaxSortOrderByUserIdAndGoalDate(userId, today)
                .orElse(0) + 1;

        // DAILY_GOALS に1件ずつ INSERT（タイトル長バリデーションを行い、結果を収集する）
        int successCount = 0;
        List<String> truncatedNotices = new ArrayList<>(); // 省略して登録されたもの
        List<String> failedTitles = new ArrayList<>();     // 登録に失敗したもの

        for (String title : goals) {
            // Oracle VARCHAR2(500) はバイト数制限。日本語UTF-8は1文字3バイトのため上限は約166文字。
            // バイト数でチェックし、超過分は安全にトリミングする
            String titleToSave = truncateToUtf8Bytes(title, 500);
            boolean truncated = !titleToSave.equals(title);
            try {
                DailyGoalEntity entity = new DailyGoalEntity();
                entity.setDailyGoalId(UUID.randomUUID().toString());
                entity.setUserId(userId);
                entity.setGoalDate(today);
                entity.setTitle(titleToSave);
                entity.setIsCompleted("0");
                entity.setSource("2");
                entity.setPlannedMin(0);
                entity.setActualMin(0);
                entity.setSortOrder(nextSortOrder++);
                entity.setDelFlg("0");
                entity.setCreatedAt(now);
                entity.setUpdatedAt(now);
                dailyGoalRepository.save(entity);
                successCount++;
                if (truncated) {
                    String preview = titleToSave.length() > 30 ? titleToSave.substring(0, 30) + "…" : titleToSave;
                    truncatedNotices.add("「" + preview + "」（タイトルが長いため途中まで登録しました）");
                }
            } catch (Exception e) {
                logger.error("目標の登録に失敗 title={}", title, e);
                String preview = title.length() > 30 ? title.substring(0, 30) + "…" : title;
                failedTitles.add("「" + preview + "」");
            }
        }

        // 返信メッセージを組み立てる
        StringBuilder reply = new StringBuilder();
        if (successCount > 0) {
            reply.append("✅ ").append(successCount).append("件登録しました\n");
            reply.append("kanaeruで確認しましょう\n");
            reply.append(frontendUrl).append("/dailyGoal");
        }
        if (!truncatedNotices.isEmpty()) {
            reply.append("\n\n📝 タイトルを省略して登録した目標：\n");
            for (String notice : truncatedNotices) {
                reply.append("• ").append(notice).append("\n");
            }
        }
        if (!failedTitles.isEmpty()) {
            if (reply.length() > 0) reply.append("\n\n");
            reply.append("❌ ").append(failedTitles.size()).append("件は登録に失敗しました：\n");
            for (String t : failedTitles) {
                reply.append("• ").append(t).append("\n");
            }
        }
        sendSlackMessage(channelId, replyTs, reply.toString().trim(), botToken);
        logger.info("Slack経由で{}件登録（省略{}件）、{}件エラー userId={}", successCount, truncatedNotices.size(), failedTitles.size(), userId);
    }

    /**
     * UTF-8バイト数が maxBytes を超えないよう安全にトリミングする。
     * CharsetDecoder の IGNORE モードを使うことで、不完全なマルチバイト列を
     * 置換文字(U+FFFD)に化けさせず、単純にスキップして確実に maxBytes 以内に収める。
     */
    private String truncateToUtf8Bytes(String text, int maxBytes) {
        if (text == null) return null;
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= maxBytes) {
            return text;
        }
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.IGNORE)
                .onUnmappableCharacter(CodingErrorAction.IGNORE);
        try {
            return decoder.decode(ByteBuffer.wrap(bytes, 0, maxBytes)).toString();
        } catch (Exception e) {
            // フォールバック: 保守的に3バイト/文字で換算した文字数で切る
            return text.substring(0, Math.min(text.length(), maxBytes / 3));
        }
    }

    /**
     * <@UXXXXXXXXXX> 形式のSlackメンションを除去する。
     */
    private String removeMention(String text) {
        return text.replaceAll("<@[A-Z0-9]+>", "").trim();
    }

    /**
     * 箇条書きテキストを分割してタイトルリストを返す。
     * 対応マーカー: ・ • - * 1. 1) など行頭の記号。
     * ★ 修正：箇条書きマーカーがない行（導入文など）をスキップする。
     *
     * 入力例:
     *   今日のToDoです          ← スキップ（箇条書きでない）
     *   ・朝のチームMTG準備     ← 取込
     *   ・営業資料を仕上げる    ← 取込
     *
     * @param text メンション除去後のテキスト
     * @return 目標タイトルのリスト
     */
    private List<String> parseBulletPoints(String text) {
        List<String> goals = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return goals;
        }

        // 同一行内に ・ や • が複数ある場合に改行を挿入して統一
        String normalized = text
                .replace("・", "\n・")
                .replace("•", "\n•");

        String[] lines = normalized.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // 箇条書きマーカーがない行はスキップ（導入文・説明文を除外）
            // 対応: ・ • - * 1. 1) 1。 ① ② ... ⑳
            boolean isBullet = line.matches("^[・•\\-*].*")
                    || line.matches("^\\d+[.)。].*")
                    || line.matches("^[①-⑳].*");
            if (!isBullet) {
                continue;
            }

            String cleaned = line
                    .replaceAll("^[・•]\\s*", "")
                    .replaceAll("^[-*]\\s+", "")
                    .replaceAll("^\\d+[.)。]\\s*", "")
                    .replaceAll("^[①-⑳]\\s*", "")
                    .trim();

            if (!cleaned.isEmpty()) {
                goals.add(cleaned);
            }
        }
        return goals;
    }

    /**
     * ワークスペースがアンインストールされた／トークンが失効した通知を受けて
     * SLACK_WORKSPACES.DEL_FLGを更新する。
     */
    private void handleWorkspaceUninstall(String teamId) {
        Optional<SlackWorkspaceEntity> workspaceOpt = slackWorkspaceRepository.findByTeamId(teamId);
        if (workspaceOpt.isEmpty()) {
            logger.warn("アンインストール通知を受信しましたが該当ワークスペースが見つかりません team_id={}", teamId);
            return;
        }
        SlackWorkspaceEntity workspace = workspaceOpt.get();
        workspace.setDelFlg("1");
        slackWorkspaceRepository.save(workspace);
        logger.info("Slackワークスペースの連携解除を検知しDEL_FLGを更新しました team_id={}", teamId);
    }

    /**
     * Slackチャンネルにメッセージを送信する。
     * スレッド返信に対応するため threadTs を指定する。
     *
     * @param channelId 送信先チャンネルID
     * @param threadTs  スレッド返信先のタイムスタンプ（通常メッセージの ts）
     * @param message   送信するテキスト
     * @param botToken  ワークスペース単位のBot Token（復号済み）
     */
    private void sendSlackMessage(String channelId, String threadTs, String message, String botToken) {
        if (botToken == null || botToken.isEmpty()) {
            logger.warn("Bot Tokenが未設定のためSlack返信をスキップします");
            return;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(botToken);

            Map<String, Object> payload = new HashMap<>();
            payload.put("channel",   channelId);
            payload.put("thread_ts", threadTs);
            payload.put("text",      message);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            restTemplate.postForObject(
                    "https://slack.com/api/chat.postMessage", request, String.class);

        } catch (Exception e) {
            logger.error("Slack返信に失敗しました channel={}", channelId, e);
        }
    }

    /**
     * HMAC-SHA256 で署名を計算して16進数文字列で返す。
     */
    private String hmacSha256Hex(String key, String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}