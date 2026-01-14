package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    public ApiAuthLogoutPost200Response logout(String token) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();
        
        response.setResponseStatus(1);
        response.setMessage("Logout successful");
        
        return response;
    }
}
