package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthForgotPasswordPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPost200Response;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Value("${app.password-reset.token.expiration:30}")
    private int tokenExpirationMinutes;

    @Transactional
    public ApiAuthRegistrationUserPost200Response sendPasswordResetEmail(
            ApiAuthForgotPasswordPostRequest request) {
        ApiAuthRegistrationUserPost200Response response = new ApiAuthRegistrationUserPost200Response();

        try {
            if (request == null || request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                logger.error("Email is null or empty");
                response.setResponseStatus(0);
                return response;
            }

            String email = request.getEmail().trim();
            Optional<UserEntity> userOptional = userRepository.findByEmail(email);

            if (userOptional.isEmpty()) {
                logger.info("User not found for email: {}", email);
                response.setResponseStatus(1);
                return response;
            }

            UserEntity user = userOptional.get();

            if (!"0".equals(user.getDelFlg())) {
                logger.info("User is deleted for email: {}", email);
                response.setResponseStatus(1);
                return response;
            }

            String resetToken = UUID.randomUUID().toString();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(tokenExpirationMinutes);

            user.setResetToken(resetToken);
            user.setResetTokenExpiry(expiryTime);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);

            // ★★★ パスワードリセットメールを送信（ユーザー名を含む） ★★★
            boolean emailSent = emailTemplateService.sendPasswordResetEmail(
                email,
                resetToken,
                tokenExpirationMinutes,
                user.getName()
            );

            if (emailSent) {
                logger.info("Password reset email sent successfully to {}", email);
                response.setResponseStatus(1);
            } else {
                logger.error("Failed to send password reset email to {}", email);
                response.setResponseStatus(0);
            }

        } catch (Exception e) {
            logger.error("Error sending password reset email", e);
            response.setResponseStatus(0);
        }

        return response;
    }
}