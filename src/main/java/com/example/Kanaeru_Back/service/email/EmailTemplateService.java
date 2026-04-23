package com.example.Kanaeru_Back.service.email;

import com.example.Kanaeru_Back.templates.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * メールテンプレートの高度な処理を提供するサービス
 */
@Service
public class EmailTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(EmailTemplateService.class);

    @Autowired
    private EmailService emailService;

    @Value("${app.frontend.url:http://localhost:5180}")
    private String frontendUrl;

    @Value("${app.support.email:kanaeru@etomoji.co.jp}")
    private String supportEmail;

    @Value("${contact.support.email}")
    private String adminEmail;

    @Value("${app.company.name:kanaeru}")
    private String companyName;

    /**
     * パスワードリセットメールを送信
     */
    public boolean sendPasswordResetEmail(String email, String resetToken, int expirationMinutes, String userName) {
        try {
            String resetUrl = frontendUrl + "/password-reset/" + resetToken;

            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName != null ? userName : "お客様");
            variables.put("resetUrl", resetUrl);
            variables.put("expirationMinutes", String.valueOf(expirationMinutes));

            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.PASSWORD_RESET, variables);
            
            if (sent) {
                logger.info("Password reset email sent to: {}", email);
            } else {
                logger.error("Failed to send password reset email to: {}", email);
            }
            
            return sent;

        } catch (Exception e) {
            logger.error("Error sending password reset email to: {}", email, e);
            return false;
        }
    }

    /**
     * 会員登録完了メールを送信
     */
    public boolean sendWelcomeEmail(String email, String userName) {
        try {
            String loginUrl = frontendUrl + "/login";

            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName);
            variables.put("loginUrl", loginUrl);
            variables.put("supportEmail", supportEmail);

            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.WELCOME, variables);
            
            if (sent) {
                logger.info("Welcome email sent to: {}", email);
            } else {
                logger.error("Failed to send welcome email to: {}", email);
            }
            
            return sent;

        } catch (Exception e) {
            logger.error("Error sending welcome email to: {}", email, e);
            return false;
        }
    }

    /**
     * パスワード変更完了メールを送信
     */
    public boolean sendPasswordChangedEmail(String email, String userName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
            String changeDateTime = LocalDateTime.now().format(formatter);

            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName);
            variables.put("changeDateTime", changeDateTime);
            variables.put("supportEmail", supportEmail);

            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.PASSWORD_CHANGED, variables);
            
            if (sent) {
                logger.info("Password changed notification sent to: {}", email);
            } else {
                logger.error("Failed to send password changed notification to: {}", email);
            }
            
            return sent;

        } catch (Exception e) {
            logger.error("Error sending password changed email to: {}", email, e);
            return false;
        }
    }

    /**
     * 退会完了メールを送信
     */
    public boolean sendAccountDeletedEmail(String email, String userName) {
        try {
            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName);
            variables.put("supportEmail", supportEmail);

            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.ACCOUNT_DELETED, variables);
            
            if (sent) {
                logger.info("Account deleted notification sent to: {}", email);
            } else {
                logger.error("Failed to send account deleted notification to: {}", email);
            }
            
            return sent;

        } catch (Exception e) {
            logger.error("Error sending account deleted email to: {}", email, e);
            return false;
        }
    }
    /**
     * 有料プランアップグレード完了メールを送信
     */
    public boolean sendSubscriptionUpgradedEmail(String email, String userName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
            String upgradeDate = LocalDateTime.now().format(formatter);
    
            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName != null ? userName : "お客様");
            variables.put("upgradeDate", upgradeDate);
            variables.put("supportEmail", supportEmail);
    
            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.SUBSCRIPTION_UPGRADED, variables);
    
            if (sent) {
                logger.info("Subscription upgraded email sent to: {}", email);
            } else {
                logger.error("Failed to send subscription upgraded email to: {}", email);
            }
    
            return sent;
        } catch (Exception e) {
            logger.error("Error sending subscription upgraded email to: {}", email, e);
            return false;
        }
    }

    /**
     * 解約予約完了メールを送信
     */
    public boolean sendSubscriptionCanceledEmail(String email, String userName, String canceledDate, String periodEnd) {
        try {
            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName != null ? userName : "お客様");
            variables.put("periodEnd", periodEnd);
            variables.put("supportEmail", supportEmail);
            variables.put("canceledDate", canceledDate);

            boolean sent = emailService.sendTemplatedEmail(email, EmailTemplate.SUBSCRIPTION_CANCELED, variables);

            if (sent) {
                logger.info("Subscription canceled email sent to: {}", email);
            } else {
                logger.error("Failed to send subscription canceled email to: {}", email);
            }

            return sent;
        } catch (Exception e) {
            logger.error("Error sending subscription canceled email to: {}", email, e);
            return false;
        }
    }
    /**
     * 管理者宛：新規会員登録通知メールを送信
     */
    public boolean sendAdminUserRegisteredNotification(String userEmail, String userName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
            String registeredAt = LocalDateTime.now().format(formatter);

            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName != null ? userName : "（未設定）");
            variables.put("email", userEmail);
            variables.put("registeredAt", registeredAt);
            variables.put("loginUrl", frontendUrl + "/login");

            boolean sent = emailService.sendTemplatedEmail(adminEmail, EmailTemplate.ADMIN_USER_REGISTERED, variables);

            if (sent) {
                logger.info("Admin notification (user registered) sent for: {}", userEmail);
            } else {
                logger.error("Failed to send admin notification (user registered) for: {}", userEmail);
            }

            return sent;
        } catch (Exception e) {
            logger.error("Error sending admin notification (user registered) for: {}", userEmail, e);
            return false;
        }
    }

    /**
     * 管理者宛：有料プランアップグレード通知メールを送信
     */
    public boolean sendAdminSubscriptionUpgradedNotification(String userEmail, String userName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
            String upgradedAt = LocalDateTime.now().format(formatter);

            Map<String, String> variables = new HashMap<>();
            variables.put("name", userName != null ? userName : "（未設定）");
            variables.put("email", userEmail);
            variables.put("upgradedAt", upgradedAt);
            variables.put("loginUrl", frontendUrl + "/login");
            boolean sent = emailService.sendTemplatedEmail(adminEmail, EmailTemplate.ADMIN_SUBSCRIPTION_UPGRADED, variables);

            if (sent) {
                logger.info("Admin notification (subscription upgraded) sent for: {}", userEmail);
            } else {
                logger.error("Failed to send admin notification (subscription upgraded) for: {}", userEmail);
            }

            return sent;
        } catch (Exception e) {
            logger.error("Error sending admin notification (subscription upgraded) for: {}", userEmail, e);
            return false;
        }
    }

    /**
     * テンプレートのプレビューを取得（開発・デバッグ用）
     */
    public String getTemplatePreview(EmailTemplate template, Map<String, String> variables) {
        String body = template.getBody();
        
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            body = body.replace(placeholder, entry.getValue());
        }
        
        return body;
    }

    /**
     * テンプレート内の未置換プレースホルダーを検出（バリデーション用）
     */
    public boolean validateTemplate(String content) {
        Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(content);
        
        if (matcher.find()) {
            logger.warn("Unreplaced placeholder found: {}", matcher.group(0));
            return false;
        }
        
        return true;
    }

    /**
     * 共通変数を自動設定してメール送信
     */
    public boolean sendEmailWithCommonVariables(
            String email, 
            EmailTemplate template, 
            Map<String, String> customVariables) {
        
        // 共通変数を追加
        Map<String, String> allVariables = new HashMap<>(customVariables);
        allVariables.putIfAbsent("companyName", companyName);
        allVariables.putIfAbsent("supportEmail", supportEmail);
        allVariables.putIfAbsent("frontendUrl", frontendUrl);
        allVariables.putIfAbsent("currentYear", String.valueOf(LocalDateTime.now().getYear()));
        
        return emailService.sendTemplatedEmail(email, template, allVariables);
    }

    /**
     * 複数の宛先に同じメールを一括送信
     */
    public Map<String, Boolean> sendBulkEmail(
            java.util.List<String> emails, 
            EmailTemplate template, 
            Map<String, String> variables) {
        
        Map<String, Boolean> results = new HashMap<>();
        
        for (String email : emails) {
            boolean sent = emailService.sendTemplatedEmail(email, template, variables);
            results.put(email, sent);
            
            // 負荷を考慮して少し待機（オプション）
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Bulk email sending interrupted", e);
                break;
            }
        }
        
        logger.info("Bulk email sent. Success: {}, Failed: {}", 
            results.values().stream().filter(v -> v).count(),
            results.values().stream().filter(v -> !v).count());
        
        return results;
    }

    /**
     * ユーザー名の敬称を自動付与
     */
    public String formatUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return "お客様";
        }
        return userName.trim() + " 様";
    }

    /**
     * URLの安全性をチェック
     */
    private boolean isValidUrl(String url) {
        try {
            new java.net.URL(url);
            return url.startsWith("http://") || url.startsWith("https://");
        } catch (Exception e) {
            return false;
        }
    }
}