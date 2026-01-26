package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
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
public class UpdatePasswordService {

    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Transactional
    public ApiAuthLogoutPost200Response updatePassword(String userId, String currentPasswordHash, String newPasswordHash) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            if (userId == null || currentPasswordHash == null || newPasswordHash == null) {
                response.setResponseStatus(0);
                response.setMessage("必要なパラメータが不足しています");
                return response;
            }

            Optional<UserEntity> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                
                if ("0".equals(user.getDelFlg())) {
                    if (currentPasswordHash.equals(user.getPasswordHash())) {
                        LocalDateTime now = LocalDateTime.now();
                        user.setPasswordHash(newPasswordHash);
                        user.setUpdatedAt(now);
                        
                        userRepository.save(user);
                        
                        // ★★★ パスワード変更完了メールを送信 ★★★
                        try {
                            boolean emailSent = emailTemplateService.sendPasswordChangedEmail(
                                user.getEmail(), 
                                user.getName()
                            );
                            
                            if (emailSent) {
                                logger.info("Password changed notification sent successfully to: {}", user.getEmail());
                            } else {
                                logger.warn("Failed to send password changed notification to: {}", user.getEmail());
                                // メール送信失敗してもパスワード変更は成功とする
                            }
                        } catch (Exception e) {
                            logger.error("Error sending password changed email to: {}", user.getEmail(), e);
                            // メール送信失敗してもパスワード変更は成功とする
                        }
                        
                        response.setResponseStatus(1);
                        response.setMessage("パスワードが正常に更新されました");
                    } else {
                        response.setResponseStatus(0);
                        response.setMessage("現在のパスワードが正しくありません");
                    }
                } else {
                    response.setResponseStatus(0);
                    response.setMessage("ユーザーが見つかりません");
                }
            } else {
                response.setResponseStatus(0);
                response.setMessage("ユーザーが見つかりません");
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
            response.setMessage("パスワード更新中にエラーが発生しました");
        }

        return response;
    }
}
