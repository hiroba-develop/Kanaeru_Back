package com.example.Kanaeru_Back.service.users.get;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiGetAdminUsersGet200Response;
import com.example.Kanaeru_Back.model.UserListSchema;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("getAdminUsersService")
public class AdminUsersService {

    private static final Logger logger = LoggerFactory.getLogger(AdminUsersService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public ApiGetAdminUsersGet200Response getAdminUsers() {
        ApiGetAdminUsersGet200Response response = new ApiGetAdminUsersGet200Response();

        try {
            // role:1またはrole:2で削除フラグが0のユーザーを取得
            List<String> roles = Arrays.asList("1", "2");
            logger.debug("Searching for admin users with roles: {}, delFlg: 0", roles);
            List<UserEntity> userEntities = userRepository.findByRoleInAndDelFlg(roles, "0");
            logger.debug("Found {} user entities from repository", userEntities != null ? userEntities.size() : 0);

            if (userEntities == null || userEntities.isEmpty()) {
                logger.info("No active admin users found");
                response.setResponseStatus(1);
                response.setAdminUserListSchema(new ArrayList<>());
                return response;
            }

            // デバッグ用：取得したユーザー情報をログ出力
            for (UserEntity entity : userEntities) {
                logger.debug("Admin user found - userId: {}, email: {}, name: {}, role: {}, delFlg: {}", 
                    entity.getUserId(), entity.getEmail(), entity.getName(), entity.getRole(), entity.getDelFlg());
            }

            // UserEntityをUserListSchemaに変換
            List<UserListSchema> adminUserListSchemas = userEntities.stream()
                    .map(this::convertToUserListSchema)
                    .collect(Collectors.toList());

            response.setResponseStatus(1);
            response.setAdminUserListSchema(adminUserListSchemas);

            logger.info("Found {} active admin users", userEntities.size());

        } catch (Exception e) {
            logger.error("Error getting admin users", e);
            response.setResponseStatus(0);
            response.setAdminUserListSchema(new ArrayList<>());
        }

        return response;
    }

    /**
     * UserEntityをUserListSchemaに変換
     */
    private UserListSchema convertToUserListSchema(UserEntity entity) {
        UserListSchema schema = new UserListSchema();
        schema.setUserId(entity.getUserId());
        schema.setEmail(entity.getEmail());
        schema.setName(entity.getName());
        schema.setCompany(entity.getCompany());
        schema.setRole(entity.getRole());
        // DEL_FLGをStringからIntegerに変換（"0" -> 0, "1" -> 1）
        schema.setDelFlg("0".equals(entity.getDelFlg()) ? 0 : 1);
        schema.setCreatedAt(entity.getCreatedAt());
        schema.setUpdatedAt(entity.getUpdatedAt());
        // 管理者ユーザー取得ではuserImageUrlは設定しない
        schema.setUserImageUrl(null);
        return schema;
    }
}
