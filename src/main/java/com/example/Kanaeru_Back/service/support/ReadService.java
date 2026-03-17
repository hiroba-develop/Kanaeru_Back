package com.example.Kanaeru_Back.service.support;

import com.example.Kanaeru_Back.model.ApiSupportReadGet200Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * /api/support/read: チャットメッセージ既読サービス
 */
@Service
public class ReadService {

    @Autowired
    private SupportService supportService;

    /**
     * 指定したメッセージシーケンス番号以前のメッセージを既読にする
     *
     * @param senderId    メッセージ送信者ID（既読にするメッセージの送信者）
     * @param recipientId 既読を付けるユーザーID（受信者）
     * @param messageSeq  基準メッセージシーケンス番号
     * @return 処理結果を含むレスポンス
     */
    public ApiSupportReadGet200Response markAsRead(String senderId, String recipientId, Integer messageSeq) {
        Integer result = supportService.markMessagesAsRead(
                recipientId,
                senderId,
                messageSeq != null ? messageSeq.toString() : null);

        ApiSupportReadGet200Response response = new ApiSupportReadGet200Response();
        response.setResponseStatus(result);
        return response;
    }
}
