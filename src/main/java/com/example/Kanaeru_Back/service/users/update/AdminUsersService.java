package com.example.Kanaeru_Back.service.users.update;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationAdminPostRequest;
import com.example.Kanaeru_Back.model.ApiUpdateAdminUsersPut200Response;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("updateAdminUsersService")
public class AdminUsersService {

    private static final Logger logger = LoggerFactory.getLogger(AdminUsersService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ApiUpdateAdminUsersPut200Response updateAdminUser(ApiAuthRegistrationAdminPostRequest request) {
        ApiUpdateAdminUsersPut200Response response = new ApiUpdateAdminUsersPut200Response();

        try {
            UserSchema userSchema = request.getUserSchema();

            if (userSchema == null) {
                logger.warn("userSchema is null");
                response.setResponseStatus(0);
                return response;
            }

            String userId = userSchema.getUserId();
            if (userId == null) {
                logger.warn("userId is null");
                response.setResponseStatus(0);
                return response;
            }

            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                logger.warn("User not found: {}", userId);
                response.setResponseStatus(0);
                return response;
            }

            UserEntity user = userOptional.get();

            // roleが"1"（管理者）または"2"（プラットフォームオーナー）であることを確認
            String currentRole = user.getRole();
            if (currentRole == null || (!currentRole.equals("1") && !currentRole.equals("2"))) {
                logger.warn("User is not admin or platform owner: userId={}, role={}", userId, currentRole);
                response.setResponseStatus(0);
                return response;
            }

            // 更新するroleが"1"または"2"であることを確認
            String newRole = userSchema.getRole();
            if (newRole != null && !newRole.equals("1") && !newRole.equals("2")) {
                logger.warn("Invalid role for admin update: {}", newRole);
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now();

            // ユーザー情報を更新
            updateUserEntity(user, userSchema, now);

            // メールアドレスの重複チェック（他のユーザーと重複していないか）
            if (userSchema.getEmail() != null && !userSchema.getEmail().equals(user.getEmail())) {
                Optional<UserEntity> existingUser = userRepository.findByEmail(userSchema.getEmail());
                if (existingUser.isPresent() && !existingUser.get().getUserId().equals(userId)) {
                    logger.warn("Email already exists: {}", userSchema.getEmail());
                    response.setResponseStatus(0);
                    return response;
                }
            }

            userRepository.save(user);

            // 更新後のユーザー情報をレスポンスに設定
            UserSchema updatedUserSchema = convertToUserSchema(user);
            response.setResponseStatus(1);
            response.setUserSchema(updatedUserSchema);

            logger.info("Admin user updated successfully - userId: {}, email: {}, role: {}", 
                userId, user.getEmail(), user.getRole());

        } catch (Exception e) {
            logger.error("Error updating admin user", e);
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * UserEntityを更新
     */
    private void updateUserEntity(UserEntity user, UserSchema userSchema, LocalDateTime now) {
        if (userSchema.getName() != null) {
            user.setName(userSchema.getName());
        }
        if (userSchema.getEmail() != null) {
            user.setEmail(userSchema.getEmail());
        }
        if (userSchema.getCompany() != null) {
            user.setCompany(userSchema.getCompany());
        }
        if (userSchema.getBusinessStartHour() != null) {
            user.setBusinessStartHour(userSchema.getBusinessStartHour());
        }
        if (userSchema.getBusinessEndHour() != null) {
            user.setBusinessEndHour(userSchema.getBusinessEndHour());
        }
        if (userSchema.getPasswordHash() != null) {
            user.setPasswordHash(userSchema.getPasswordHash());
        }
        if (userSchema.getRole() != null) {
            user.setRole(userSchema.getRole());
        }
        user.setUpdatedAt(now);
    }

    /**
     * UserEntityをUserSchemaに変換
     */
    private UserSchema convertToUserSchema(UserEntity user) {
        UserSchema userSchema = new UserSchema();
        userSchema.setUserId(user.getUserId());
        userSchema.setEmail(user.getEmail());
        userSchema.setPasswordHash(user.getPasswordHash());
        userSchema.setName(user.getName());
        userSchema.setCompany(user.getCompany());
        userSchema.setRole(user.getRole());
        if (user.getDelFlg() != null) {
            try {
                userSchema.setDelFlg(Integer.parseInt(user.getDelFlg()));
            } catch (NumberFormatException e) {
                userSchema.setDelFlg(null);
            }
        } else {
            userSchema.setDelFlg(null);
        }
        userSchema.setCreatedAt(user.getCreatedAt());
        userSchema.setUpdatedAt(user.getUpdatedAt());
        userSchema.setBusinessStartHour(user.getBusinessStartHour());
        userSchema.setBusinessEndHour(user.getBusinessEndHour());
        return userSchema;
    }
}
