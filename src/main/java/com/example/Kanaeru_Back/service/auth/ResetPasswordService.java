package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthResetPasswordPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResetPasswordService {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Transactional
    public ApiAuthTermsAgreePost200Response resetPassword(
            ApiAuthResetPasswordPostRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();

        try {
            // バリデーション
            if (request == null || 
                request.getToken() == null || 
                request.getToken().trim().isEmpty() ||
                request.getNewPasswordHash() == null || 
                request.getNewPasswordHash().trim().isEmpty()) {
                logger.error("Token or password hash is null or empty");
                response.setResponseStatus(0);
                return response;
            }

            String token = request.getToken().trim();
            String passwordHash = request.getNewPasswordHash().trim();

            Optional<UserEntity> userOptional = userRepository.findByResetToken(token);

            if (userOptional.isEmpty()) {
                logger.error("Invalid reset token: {}", token);
                response.setResponseStatus(0);
                return response;
            }

            UserEntity user = userOptional.get();

            if (!"0".equals(user.getDelFlg())) {
                logger.error("User is deleted");
                response.setResponseStatus(0);
                return response;
            }

            if (user.getResetTokenExpiry() == null || 
                LocalDateTime.now().isAfter(user.getResetTokenExpiry())) {
                logger.error("Reset token has expired for user: {}", user.getEmail());
                response.setResponseStatus(0);
                return response;
            }

            // パスワードを更新
            user.setPasswordHash(passwordHash);
            user.setResetToken(null);
            user.setResetTokenExpiry(null);
            user.setUpdatedAt(LocalDateTime.now());
            
            userRepository.save(user);

            // ★★★ パスワード変更完了メールを送信 ★★★
            emailTemplateService.sendPasswordChangedEmail(user.getEmail(), user.getName());

            logger.info("Password reset successfully for user: {}", user.getEmail());
            response.setResponseStatus(1);

        } catch (Exception e) {
            logger.error("Error resetting password", e);
            response.setResponseStatus(0);
        }

        return response;
    }
}