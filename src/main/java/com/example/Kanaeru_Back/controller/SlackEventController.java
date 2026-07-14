package com.example.Kanaeru_Back.controller;

import com.example.Kanaeru_Back.model.ApiSlackEventsPost200Response;
import com.example.Kanaeru_Back.service.slack.SlackEventService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlackEventController implements SlackEventApi {

    private static final Logger logger = LoggerFactory.getLogger(SlackEventController.class);

    @Autowired
    private SlackEventService slackEventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ApiSlackEventsPost200Response> apiSlackEventsPost(
            String xSlackSignature,
            String xSlackRequestTimestamp,
            String body) {

        // ① Signing Secret で正規リクエスト検証
        if (!slackEventService.verifySignature(xSlackSignature, xSlackRequestTimestamp, body)) {
            logger.warn("Slack署名検証失敗 signature={}", xSlackSignature);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            JsonNode root = objectMapper.readTree(body);
            String type = root.path("type").asText();

            // ② url_verification チャレンジ対応
            if ("url_verification".equals(type)) {
                String challenge = root.path("challenge").asText();
                ApiSlackEventsPost200Response response = new ApiSlackEventsPost200Response();
                response.setChallenge(challenge);
                return ResponseEntity.ok(response);
            }

            // ③ イベントを非同期処理（3秒以内に200を返すため）
            slackEventService.processEventAsync(body);

        } catch (Exception e) {
            logger.error("Slackイベントの解析に失敗", e);
        }

        return ResponseEntity.ok(new ApiSlackEventsPost200Response());
    }
}
