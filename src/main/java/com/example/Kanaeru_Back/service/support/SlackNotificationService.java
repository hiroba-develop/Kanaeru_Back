package com.example.Kanaeru_Back.service.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class SlackNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SlackNotificationService.class);

    // ★ application.propertiesから読み込む
    @Value("${slack.webhook.url:}")
    private String slackWebhookUrl;
    
    @Value("${app.frontend.url:http://localhost:5180}")
    private String frontendUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessageNotification(String senderId, String senderName, String content) {
        sendToSlack(senderName, content, senderId);
    }
    
    public int sendMessageNotificationToAllAdmins(String senderId, String senderName, String content) {
        boolean success = sendToSlack(senderName, content, senderId);
        return success ? 1 : 0;
    }

    // ★ 新規追加：Slack Webhook へ実際にPOSTするメソッド
    private boolean sendToSlack(String senderName, String content, String recipientId) {
        if (slackWebhookUrl == null || slackWebhookUrl.isBlank()) {
            logger.warn("Slack Webhook URLが設定されていません。通知をスキップします。");
            return false;
        }

        try {
            // 送信するJSONを組み立て
            Map<String, Object> payload = new HashMap<>();
            payload.put("text", String.format("*%s* からメッセージが届きました", senderName));

            // ★ 日時フォーマット
            String formattedTime = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

            // ★ 管理画面URL（ユーザーIDをクエリパラメータで渡す）
            String supportUrl = String.format("%s/support?userId=%s", frontendUrl, recipientId);

            Map<String, String> attachment = new HashMap<>();
            attachment.put("color", "#13AE67");
            attachment.put("text", content);
            attachment.put("footer", String.format("%s　|<%s|　管理画面で返信する>",
                formattedTime, supportUrl));
            payload.put("attachments", new Object[]{attachment});

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response =
                restTemplate.postForEntity(slackWebhookUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Slack通知送信成功: senderName={}", senderName);
                return true;
            } else {
                logger.warn("Slack通知送信失敗: status={}", response.getStatusCode());
                return false;
            }

        } catch (Exception e) {
            // ★ 通知失敗してもメッセージ送信処理は止めない
            logger.error("Slack通知エラー: senderName={}, error={}", senderName, e.getMessage());
            return false;
        }
    }
}