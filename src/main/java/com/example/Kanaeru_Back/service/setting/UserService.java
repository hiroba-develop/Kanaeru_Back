package com.example.Kanaeru_Back.service.setting;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.SlackWebhookSettingEntity;
import com.example.Kanaeru_Back.entity.SubscriptionEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiSettingUpdateUserPut200Response;
import com.example.Kanaeru_Back.model.ApiSettingUserGet200Response;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.SubscriptionSchema;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.SlackWebhookSettingRepository;
import com.example.Kanaeru_Back.repository.SubscriptionRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private SlackWebhookSettingRepository slackWebhookSettingRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional(readOnly = true)
    public ApiSettingUserGet200Response getUserSetting(String userId) {
        ApiSettingUserGet200Response response = new ApiSettingUserGet200Response();

        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            
            if (userOptional.isEmpty()) {
                response.setResponseStatus(0);
                return response;
            }
            
            UserEntity user = userOptional.get();
            String role = user.getRole();
            Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);
            Optional<SlackWebhookSettingEntity> webhookOptional = slackWebhookSettingRepository.findByUserId(userId);

            UserSchema userSchema = convertToUserSchema(user, webhookOptional);

            // サブスクリプション情報を取得
            Optional<SubscriptionEntity> subscriptionOptional = subscriptionRepository.findByUserId(userId);
            subscriptionOptional.ifPresent(subscription ->
                    response.setSubscriptionSchema(convertToSubscriptionSchema(subscription)));

            // role が 1（管理者）または 2（プラットフォームオーナー）の場合、SettingEntity がなくてもOK
            if ("1".equals(role) || "2".equals(role)) {
                response.setResponseStatus(1);
                response.setUserSchema(userSchema);
                // SettingSchema は存在すれば設定、なければ null
                if (settingOptional.isPresent()) {
                    response.setSettingSchema(convertToSettingSchema(settingOptional.get()));
                } else {
                    response.setSettingSchema(null);
                }
            } else {
                // role が 0（一般ユーザー）の場合は、SettingEntity が必須
                if (settingOptional.isPresent()) {
                    SettingSchema settingSchema = convertToSettingSchema(settingOptional.get());
                    response.setResponseStatus(1);
                    response.setUserSchema(userSchema);
                    response.setSettingSchema(settingSchema);
                } else {
                    response.setResponseStatus(0);
                }
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    @Transactional
    public ApiSettingUpdateUserPut200Response updateUserSetting(UserSchema userSchema, SettingSchema settingSchema) {
        ApiSettingUpdateUserPut200Response response = new ApiSettingUpdateUserPut200Response();

        try {
            String userId = userSchema.getUserId();
            if (userId == null) {
                response.setResponseStatus(0);
                return response;
            }

            Optional<UserEntity> userOptional = userRepository.findById(userId);
            
            if (userOptional.isEmpty()) {
                response.setResponseStatus(0);
                return response;
            }
            
            UserEntity user = userOptional.get();
            String role = user.getRole();
            LocalDateTime now = LocalDateTime.now();
            
            // UserEntity は常に更新
            updateUserEntity(user, userSchema, now);
            updateWebhookSetting(userId, userSchema.getWebhookUrl(), now);
            userRepository.save(user);
            
            // role が 1（管理者）または 2（プラットフォームオーナー）の場合、SettingEntity はオプショナル
            if ("1".equals(role) || "2".equals(role)) {
                Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);
                // SettingEntity が存在し、かつ settingSchema が渡されている場合のみ更新
                if (settingOptional.isPresent() && settingSchema != null) {
                    SettingEntity setting = settingOptional.get();
                    updateSettingEntity(setting, settingSchema, now);
                    settingRepository.save(setting);
                }
                ApiSettingUserGet200Response getResponse = getUserSetting(userId);
                response.setResponseStatus(getResponse.getResponseStatus());
                response.setUserSchema(getResponse.getUserSchema());
                response.setSettingSchema(getResponse.getSettingSchema());
                return response;
            } else {
                // role が 0（一般ユーザー）の場合は、SettingEntity が必須
                Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);
                if (settingOptional.isPresent()) {
                    SettingEntity setting = settingOptional.get();
                    updateSettingEntity(setting, settingSchema, now);
                    settingRepository.save(setting);
                    ApiSettingUserGet200Response getResponse = getUserSetting(userId);
                    response.setResponseStatus(getResponse.getResponseStatus());
                    response.setUserSchema(getResponse.getUserSchema());
                    response.setSettingSchema(getResponse.getSettingSchema());
                } else {
                    response.setResponseStatus(0);
                }
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

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
        user.setUpdatedAt(now);
    }

    private void updateSettingEntity(SettingEntity setting, SettingSchema settingSchema, LocalDateTime now) {
        if (settingSchema.getCompanySize() != null) {
            try {
                setting.setCompanySize(Integer.parseInt(settingSchema.getCompanySize()));
            } catch (NumberFormatException e) {
                setting.setCompanySize(null);
            }
        }
        if (settingSchema.getIndustry() != null) {
            try {
                setting.setIndustry(Integer.parseInt(settingSchema.getIndustry()));
            } catch (NumberFormatException e) {
                setting.setIndustry(null);
            }
        }
        if (settingSchema.getCapital() != null) {
            setting.setCapital(settingSchema.getCapital().longValue());
        }
        if (settingSchema.getFinancialKnowledge() != null) {
            try {
                setting.setFinancialKnowledge(Integer.parseInt(settingSchema.getFinancialKnowledge()));
            } catch (NumberFormatException e) {
                setting.setFinancialKnowledge(null);
            }
        }
        if (settingSchema.getFiscalYearStartYear() != null) {
            setting.setFiscalYearStartYear(settingSchema.getFiscalYearStartYear());
        }
        if (settingSchema.getFiscalYearStartMonth() != null) {
            setting.setFiscalYearStartMonth(settingSchema.getFiscalYearStartMonth());
        }
        if (settingSchema.getLastAdminCommentDate() != null) {
            setting.setLastAdminCommentDate(settingSchema.getLastAdminCommentDate().atStartOfDay());
        }
        setting.setUpdatedAt(now);
    }

    private void updateWebhookSetting(String userId, String webhookUrl, LocalDateTime now) {
        if (webhookUrl != null) {
            Optional<SlackWebhookSettingEntity> webhookOptional = slackWebhookSettingRepository.findByUserId(userId);
            if (webhookOptional.isPresent()) {
                SlackWebhookSettingEntity webhook = webhookOptional.get();
                webhook.setWebhookUrl(webhookUrl);
                webhook.setUpdatedAt(now);
                slackWebhookSettingRepository.save(webhook);
            }
        }
    }

    private SubscriptionSchema convertToSubscriptionSchema(SubscriptionEntity entity) {
        SubscriptionSchema schema = new SubscriptionSchema();
        schema.setId(entity.getStripeSubscriptionId());
        schema.setStatus(entity.getStatus());
        schema.setCurrentPeriodStart(entity.getCurrentPeriodStart());
        schema.setCurrentPeriodEnd(entity.getCurrentPeriodEnd());
        schema.setCancelAtPeriodEnd(entity.getCancelAtPeriodEnd());
        schema.setCanceledAt(entity.getCanceledAt());
        schema.setAmount(entity.getAmount());
        schema.setCreatedAt(entity.getCreatedAt());
        return schema;
    }

    private UserSchema convertToUserSchema(UserEntity user, Optional<SlackWebhookSettingEntity> webhookOptional) {
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
        userSchema.setTermsAgreedAt(user.getTermsAgreedAt());

        if (webhookOptional.isPresent()) {
            userSchema.setWebhookUrl(webhookOptional.get().getWebhookUrl());
        }

        return userSchema;
    }

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
