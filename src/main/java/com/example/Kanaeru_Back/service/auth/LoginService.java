package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthLoginPost200Response;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public ApiAuthLoginPost200Response login(String email, String passwordHash) {
        ApiAuthLoginPost200Response response = new ApiAuthLoginPost200Response();

        try {
            Optional<UserEntity> userOptional = userRepository.findByEmailAndPasswordHash(email, passwordHash);

            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                
                if ("0".equals(user.getDelFlg())) {
                    String token = jwtUtil.generateToken(user.getUserId(), user.getRole());
                    
                    String userImageUrl = null;
                    Optional<SettingEntity> settingOptional = settingRepository.findByUserId(user.getUserId());
                    if (settingOptional.isPresent()) {
                        userImageUrl = settingOptional.get().getUserImageUrl();
                    }

                    // レスポンスには更新前の値をセット（初回ログインはnull）
                    LocalDateTime previousLastLoginAt = user.getLastLoginAt();

                    // lastLoginAt を現在時刻で更新
                    user.setLastLoginAt(LocalDateTime.now());
                    userRepository.save(user);
                    
                    response.setResponseStatus(1);
                    response.setUserId(user.getUserId());
                    response.setName(user.getName());
                    response.setEmail(user.getEmail());
                    response.setRole(user.getRole());
                    response.setToken(token);
                    response.setUserImageUrl(userImageUrl);
                    response.setTermsAgreedAt(user.getTermsAgreedAt());
                    response.setLastLoginAt(previousLastLoginAt);
                } else {
                    response.setResponseStatus(0);
                }
            } else {
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}

