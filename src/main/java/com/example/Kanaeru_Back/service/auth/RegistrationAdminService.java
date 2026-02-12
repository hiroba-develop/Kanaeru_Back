package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationAdminPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPost200Response;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationAdminService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationAdminService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ApiAuthRegistrationUserPost200Response registerAdmin(ApiAuthRegistrationAdminPostRequest request) {
        ApiAuthRegistrationUserPost200Response response = new ApiAuthRegistrationUserPost200Response();

        try {
            UserSchema userSchema = request.getUserSchema();

            if (userSchema == null) {
                logger.warn("userSchema is null");
                response.setResponseStatus(0);
                return response;
            }

            // roleが"1"（管理者）または"2"（プラットフォームオーナー）であることを確認
            String role = userSchema.getRole();
            if (role == null || (!role.equals("1") && !role.equals("2"))) {
                logger.warn("Invalid role for admin registration: {}", role);
                response.setResponseStatus(0);
                return response;
            }

            // メールアドレスの重複チェック
            if (userSchema.getEmail() != null && userRepository.findByEmail(userSchema.getEmail()).isPresent()) {
                logger.warn("Email already exists: {}", userSchema.getEmail());
                response.setResponseStatus(0);
                return response;
            }

            UserEntity userEntity = new UserEntity();
            String userId = UUID.randomUUID().toString();
            userEntity.setUserId(userId);
            userEntity.setEmail(userSchema.getEmail());
            userEntity.setPasswordHash(userSchema.getPasswordHash());
            userEntity.setName(userSchema.getName());
            userEntity.setCompany(userSchema.getCompany());
            userEntity.setRole(role);
            userEntity.setDelFlg("0");
            userEntity.setCreatedAt(LocalDateTime.now());
            userEntity.setUpdatedAt(LocalDateTime.now());
            userEntity.setBusinessStartHour(userSchema.getBusinessStartHour());
            userEntity.setBusinessEndHour(userSchema.getBusinessEndHour());
            userEntity.setStripeCustomerId("");

            userRepository.save(userEntity);

            logger.info("Admin user registered successfully - userId: {}, email: {}, role: {}", 
                userId, userEntity.getEmail(), role);

            response.setResponseStatus(1);
        } catch (Exception e) {
            logger.error("Error registering admin user", e);
            response.setResponseStatus(0);
        }

        return response;
    }
}
