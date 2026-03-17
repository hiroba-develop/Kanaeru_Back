package com.example.Kanaeru_Back.service.support;

import com.example.Kanaeru_Back.model.ApiSupportSendPost200Response;
import com.example.Kanaeru_Back.model.ApiSupportSendPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * /api/support/send: チャットメッセージ送信サービス
 */
@Service
public class SendService {

    @Autowired
    private SupportService supportService;

    /**
     * メッセージを送信する
     *
     * @param request 送信リクエスト
     * @return ApiSupportSendPost200Response
     */
    public ApiSupportSendPost200Response send(ApiSupportSendPostRequest request) {
        return supportService.sendMessage(request);
    }
}
