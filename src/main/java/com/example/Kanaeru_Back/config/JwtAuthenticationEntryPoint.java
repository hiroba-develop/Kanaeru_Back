package com.example.Kanaeru_Back.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 認証エラー時のレスポンスハンドラー
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        
        logger.warn("認証エラー: {} - Path: {}", authException.getMessage(), request.getRequestURI());
        
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        String jsonResponse = String.format(
            "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"認証が必要です。ログインしてください。\",\"path\":\"%s\"}",
            request.getRequestURI()
        );
        
        response.getWriter().write(jsonResponse);
    }
}
