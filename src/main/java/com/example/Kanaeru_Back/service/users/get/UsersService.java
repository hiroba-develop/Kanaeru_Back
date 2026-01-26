package com.example.Kanaeru_Back.service.users.get;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiGetUsersGet200Response;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.UserListSchema;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Transactional(readOnly = true)
    public ApiGetUsersGet200Response getUsers() {
        ApiGetUsersGet200Response response = new ApiGetUsersGet200Response();

        try {
            // 有効な一般ユーザー（role:0, DEL_FLG:0）を全件取得
            List<UserEntity> userEntities = userRepository.findByRoleAndDelFlg("0", "0");

            if (userEntities == null || userEntities.isEmpty()) {
                logger.info("No active general users found");
                response.setResponseStatus(1);
                response.setUserListSchema(new ArrayList<>());
                response.setSettingListSchema(new ArrayList<>());
                return response;
            }

            // ユーザーIDのリストを作成
            List<String> userIds = userEntities.stream()
                    .map(UserEntity::getUserId)
                    .collect(Collectors.toList());

            // 各ユーザーの設定情報を取得
            List<SettingEntity> settingEntities = settingRepository.findByUserIdIn(userIds);
            
            // userIdをキーとするMapを作成（設定情報の高速検索用）
            Map<String, SettingEntity> settingMap = settingEntities.stream()
                    .collect(Collectors.toMap(SettingEntity::getUserId, setting -> setting));

            // UserEntityをUserListSchemaに変換
            List<UserListSchema> userListSchemas = userEntities.stream()
                    .map(user -> convertToUserListSchema(user, settingMap.get(user.getUserId())))
                    .collect(Collectors.toList());

            // SettingEntityをSettingSchemaに変換
            List<SettingSchema> settingSchemas = settingEntities.stream()
                    .map(this::convertToSettingSchema)
                    .collect(Collectors.toList());

            response.setResponseStatus(1);
            response.setUserListSchema(userListSchemas);
            response.setSettingListSchema(settingSchemas);

            logger.info("Found {} active general users", userEntities.size());

        } catch (Exception e) {
            logger.error("Error getting users", e);
            response.setResponseStatus(0);
            response.setUserListSchema(new ArrayList<>());
            response.setSettingListSchema(new ArrayList<>());
        }

        return response;
    }

    /**
     * UserEntityをUserListSchemaに変換
     */
    private UserListSchema convertToUserListSchema(UserEntity entity, SettingEntity setting) {
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
        // userImageUrlはSettingEntityから取得
        if (setting != null) {
            schema.setUserImageUrl(setting.getUserImageUrl());
        } else {
            schema.setUserImageUrl(null);
        }
        return schema;
    }

    /**
     * SettingEntityをSettingSchemaに変換
     */
    private SettingSchema convertToSettingSchema(SettingEntity setting) {
        SettingSchema settingSchema = new SettingSchema();
        settingSchema.setUserId(setting.getUserId());
        settingSchema.setAdminId(setting.getAdminId());
        settingSchema.setCompanySize(setting.getCompanySize() != null ? String.valueOf(setting.getCompanySize()) : null);
        settingSchema.setIndustry(setting.getIndustry() != null ? String.valueOf(setting.getIndustry()) : null);
        settingSchema.setCapital(setting.getCapital() != null ? BigDecimal.valueOf(setting.getCapital()) : null);
        settingSchema.setFinancialKnowledge(setting.getFinancialKnowledge() != null ? String.valueOf(setting.getFinancialKnowledge()) : null);
        settingSchema.setFiscalYearStartYear(setting.getFiscalYearStartYear());
        settingSchema.setFiscalYearStartMonth(setting.getFiscalYearStartMonth());
        
        if (setting.getLastAdminCommentDate() != null) {
            settingSchema.setLastAdminCommentDate(setting.getLastAdminCommentDate().toLocalDate());
        }

        return settingSchema;
    }
}
