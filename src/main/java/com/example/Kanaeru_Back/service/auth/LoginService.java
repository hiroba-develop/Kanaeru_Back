package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthLoginPost200Response;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public ApiAuthLoginPost200Response login(String email, String passwordHash) {
        ApiAuthLoginPost200Response response = new ApiAuthLoginPost200Response();

        try {
            Optional<UserEntity> userOptional = userRepository.findByEmailAndPasswordHash(email, passwordHash);

            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                
                if ("0".equals(user.getDelFlg())) {
                    String token = jwtUtil.generateToken(user.getUserId(), user.getRole());
                    
                    response.setResponseStatus(1);
                    response.setUserId(user.getUserId());
                    response.setName(user.getName());
                    response.setRole(user.getRole());
                    response.setToken(token);
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

