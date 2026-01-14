package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPost200Response;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPostRequest;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Transactional
    public ApiAuthRegistrationUserPost200Response registerUser(ApiAuthRegistrationUserPostRequest request) {
        ApiAuthRegistrationUserPost200Response response = new ApiAuthRegistrationUserPost200Response();

        try {
            UserSchema userSchema = request.getUserSchema();
            SettingSchema settingSchema = request.getSettingSchema();

            if (userSchema == null) {
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
            userEntity.setRole(userSchema.getRole() != null ? userSchema.getRole() : "U");
            userEntity.setDelFlg("0");
            userEntity.setCreatedAt(LocalDateTime.now());
            userEntity.setUpdatedAt(LocalDateTime.now());
            userEntity.setBusinessStartHour(userSchema.getBusinessStartHour());
            userEntity.setBusinessEndHour(userSchema.getBusinessEndHour());
            userEntity.setStripeCustomerId("");

            userRepository.save(userEntity);

            if (settingSchema != null) {
                SettingEntity settingEntity = new SettingEntity();
                settingEntity.setUserId(userId);
                settingEntity.setAdminId(settingSchema.getAdminId() != null ? settingSchema.getAdminId() : "");
                settingEntity.setCompanySize(settingSchema.getCompanySize() != null ? Integer.parseInt(settingSchema.getCompanySize()) : null);
                settingEntity.setIndustry(settingSchema.getIndustry() != null ? Integer.parseInt(settingSchema.getIndustry()) : null);
                settingEntity.setCapital(settingSchema.getCapital() != null ? settingSchema.getCapital().longValue() : null);
                settingEntity.setFinancialKnowledge(settingSchema.getFinancialKnowledge() != null ? Integer.parseInt(settingSchema.getFinancialKnowledge()) : null);
                settingEntity.setFiscalYearStartYear(settingSchema.getFiscalYearStartYear());
                settingEntity.setFiscalYearStartMonth(settingSchema.getFiscalYearStartMonth());
                settingEntity.setCreatedAt(LocalDateTime.now());
                settingEntity.setUpdatedAt(LocalDateTime.now());

                settingRepository.save(settingEntity);
            }

            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}

