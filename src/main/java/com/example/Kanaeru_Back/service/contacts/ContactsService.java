package com.example.Kanaeru_Back.service.contacts;

import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiContactsSendPostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactsService {

    private static final Logger logger = LoggerFactory.getLogger(ContactsService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${contact.support.email}")
    private String supportEmail;

    @Value("${app.mail.from}")
    private String fromEmail;

    @Transactional
    public ApiAuthLogoutPost200Response sendContactEmail(ApiContactsSendPostRequest request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            if (request == null) {
                logger.error("Request is null");
                response.setResponseStatus(0);
                response.setMessage("リクエストが不正です");
                return response;
            }

            String title = request.getTitle();
            String userName = request.getUserName();
            String email = request.getEmail();
            String content = request.getContent();

            if (title == null || title.trim().isEmpty()) {
                logger.error("Title is null or empty");
                response.setResponseStatus(0);
                response.setMessage("件名が入力されていません");
                return response;
            }

            if (email == null || email.trim().isEmpty()) {
                logger.error("Email is null or empty");
                response.setResponseStatus(0);
                response.setMessage("メールアドレスが入力されていません");
                return response;
            }

            if (content == null || content.trim().isEmpty()) {
                logger.error("Content is null or empty");
                response.setResponseStatus(0);
                response.setMessage("お問い合わせ内容が入力されていません");
                return response;
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(supportEmail);
            message.setSubject("【kanaeru】お問い合わせ：" + title);
            
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("お問い合わせがありました。\n\n");
            emailBody.append("【件名】\n");
            emailBody.append(title).append("\n\n");
            if (userName != null && !userName.trim().isEmpty()) {
                emailBody.append("【送信者名】\n");
                emailBody.append(userName).append("\n\n");
            }
            emailBody.append("【送信者メールアドレス】\n");
            emailBody.append(email).append("\n\n");
            emailBody.append("【お問い合わせ内容】\n");
            
            // 自動折り返しを適用
            String wrappedContent = wrapText(content, 60);
            emailBody.append(wrappedContent);
            
            message.setText(emailBody.toString());

            mailSender.send(message);

            logger.info("Contact email sent successfully to {}", supportEmail);
            response.setResponseStatus(1);
            response.setMessage("お問い合わせを送信しました");

        } catch (Exception e) {
            logger.error("Error sending contact email", e);
            response.setResponseStatus(0);
            response.setMessage("メール送信に失敗しました: " + e.getMessage());
        }

        return response;
    }

    /**
     * テキストを指定文字数で自動折り返し
     * @param text 折り返し対象のテキスト
     * @param maxLineLength 1行の最大文字数
     * @return 折り返し後のテキスト
     */
    private String wrapText(String text, int maxLineLength) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        StringBuilder wrapped = new StringBuilder();
        String[] lines = text.split("\n");
        
        for (String line : lines) {
            if (line.length() <= maxLineLength) {
                wrapped.append(line).append("\n");
            } else {
                // 長い行を折り返し
                int start = 0;
                while (start < line.length()) {
                    int end = Math.min(start + maxLineLength, line.length());
                    
                    // 英単語の場合は単語の区切りで折り返し
                    if (end < line.length() && !Character.isWhitespace(line.charAt(end))) {
                        int lastSpace = line.lastIndexOf(' ', end);
                        if (lastSpace > start) {
                            end = lastSpace;
                        }
                    }
                    
                    wrapped.append(line, start, end).append("\n");
                    start = end;
                    
                    // 空白をスキップ
                    while (start < line.length() && Character.isWhitespace(line.charAt(start))) {
                        start++;
                    }
                }
            }
        }
        
        // 最後の改行を削除
        if (wrapped.length() > 0 && wrapped.charAt(wrapped.length() - 1) == '\n') {
            wrapped.setLength(wrapped.length() - 1);
        }
        
        return wrapped.toString();
    }
}