package com.example.Kanaeru_Back.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * /api/support/stream: SSEストリーム接続管理サービス
 */
@Service
public class StreamService {

    @Autowired
    private SseConnectionManager sseConnectionManager;

    /**
     * SSE接続を確立してSseEmitterを返す
     *
     * @param userId ユーザーID
     * @return SseEmitter
     */
    public SseEmitter createConnection(String userId) {
        return sseConnectionManager.addConnection(userId);
    }
}
