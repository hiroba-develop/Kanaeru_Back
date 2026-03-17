package com.example.Kanaeru_Back.util;

import com.example.Kanaeru_Back.model.DmMessagesSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

/**
 * SSEイベントを構築するユーティリティクラス
 */
public class SseEventBuilder {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private SseEventBuilder() {
    }

    /**
     * SSE接続確立イベントを構築する
     */
    public static SseEmitter.SseEventBuilder buildConnectEvent() {
        return SseEmitter.event()
                .name("connect")
                .data("{\"status\":\"connected\"}");
    }

    /**
     * メッセージ受信イベントを構築する
     *
     * @param messageSchema メッセージデータ
     */
    public static SseEmitter.SseEventBuilder buildMessageEvent(DmMessagesSchema messageSchema) {
        try {
            String json = objectMapper.writeValueAsString(messageSchema);
            return SseEmitter.event()
                    .name("message")
                    .data(json);
        } catch (JsonProcessingException e) {
            return SseEmitter.event()
                    .name("error")
                    .data("{\"message\":\"メッセージのシリアライズに失敗しました\"}");
        }
    }

    /**
     * 既読イベントを構築する
     *
     * @param readEventData 既読イベントデータ
     */
    public static SseEmitter.SseEventBuilder buildReadEvent(Map<String, Object> readEventData) {
        try {
            String json = objectMapper.writeValueAsString(readEventData);
            return SseEmitter.event()
                    .name("read")
                    .data(json);
        } catch (JsonProcessingException e) {
            return SseEmitter.event()
                    .name("error")
                    .data("{\"message\":\"既読データのシリアライズに失敗しました\"}");
        }
    }

    /**
     * エラーイベントを構築する
     *
     * @param errorMessage エラーメッセージ
     */
    public static SseEmitter.SseEventBuilder buildErrorEvent(String errorMessage) {
        try {
            String json = objectMapper.writeValueAsString(Map.of("message", errorMessage));
            return SseEmitter.event()
                    .name("error")
                    .data(json);
        } catch (JsonProcessingException e) {
            return SseEmitter.event()
                    .name("error")
                    .data("{\"message\":\"エラー\"}");
        }
    }

    /**
     * ハートビートイベントを構築する
     */
    public static SseEmitter.SseEventBuilder buildHeartbeatEvent() {
        return SseEmitter.event()
                .name("heartbeat")
                .data("{\"status\":\"alive\"}");
    }
}
