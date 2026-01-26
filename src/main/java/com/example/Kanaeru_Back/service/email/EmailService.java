package com.example.Kanaeru_Back.service.email;

import com.example.Kanaeru_Back.templates.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String fromEmail;

    /**
     * テンプレートを使用してメールを送信
     * 
     * @param to 送信先メールアドレス
     * @param template メールテンプレート（EmailTemplate型） ★★★ ここを確認 ★★★
     * @param variables テンプレート変数（プレースホルダーの置換用）
     * @return 送信成功の場合true
     */
    public boolean sendTemplatedEmail(String to, EmailTemplate template, Map<String, String> variables) {
        try {
            // テンプレートから件名と本文を取得
            String subject = template.getSubject();
            String body = template.getBody();

            // プレースホルダーを変数で置換
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                String placeholder = "{" + entry.getKey() + "}";
                body = body.replace(placeholder, entry.getValue());
                subject = subject.replace(placeholder, entry.getValue());
            }

            // メール送信
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            logger.info("Email sent successfully to {} with template {}", to, template.name());
            return true;

        } catch (Exception e) {
            logger.error("Failed to send email to {} with template {}", to, template.name(), e);
            return false;
        }
    }
}