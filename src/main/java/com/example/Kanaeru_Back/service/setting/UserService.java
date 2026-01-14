package com.example.Kanaeru_Back.service.setting;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.SlackWebhookSettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiSettingUpdateUserPut200Response;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.SlackWebhookSettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Transactional(readOnly = true)
    public ApiSettingUpdateUserPut200Response getUserSetting(String userId) {
        ApiSettingUpdateUserPut200Response response = new ApiSettingUpdateUserPut200Response();

        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);
            Optional<SlackWebhookSettingEntity> webhookOptional = slackWebhookSettingRepository.findByUserId(userId);

            if (userOptional.isPresent() && settingOptional.isPresent()) {
                UserEntity user = userOptional.get();
                SettingEntity setting = settingOptional.get();

                UserSchema userSchema = convertToUserSchema(user, webhookOptional);
                SettingSchema settingSchema = convertToSettingSchema(setting);

                response.setResponseStatus(1);
                response.setUserSchema(userSchema);
                response.setSettingSchema(settingSchema);
            } else {
                response.setResponseStatus(0);
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
            Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);

            if (userOptional.isPresent() && settingOptional.isPresent()) {
                UserEntity user = userOptional.get();
                SettingEntity setting = settingOptional.get();
                LocalDateTime now = LocalDateTime.now();

                updateUserEntity(user, userSchema, now);
                updateSettingEntity(setting, settingSchema, now);
                updateWebhookSetting(userId, userSchema.getWebhookUrl(), now);

                userRepository.save(user);
                settingRepository.save(setting);

                return getUserSetting(userId);
            } else {
                response.setResponseStatus(0);
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
