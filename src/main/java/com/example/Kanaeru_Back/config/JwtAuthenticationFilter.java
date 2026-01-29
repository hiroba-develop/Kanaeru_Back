package com.example.Kanaeru_Back.config;

import com.example.Kanaeru_Back.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT Cookie認証フィルター
 * リクエストからCookieまたはAuthorizationヘッダーのトークンを取得し、認証を行う
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String token = extractToken(request);
            
            if (token != null && !token.isEmpty()) {
                // トークンからユーザー情報を取得
                String userId = jwtUtil.extractUserId(token);
                String role = jwtUtil.extractRole(token);
                
                logger.debug("JWT認証成功 - userId: {}, role: {}", userId, role);
                
                // Spring Securityの認証オブジェクトを作成
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // SecurityContextに認証情報を設定
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                logger.debug("SecurityContextに認証情報を設定しました");
            }
        } catch (Exception e) {
            logger.error("JWT認証エラー: {}", e.getMessage());
            // エラーが発生しても処理は継続（認証失敗として扱われる）
        }
        
        filterChain.doFilter(request, response);
    }

    /**
     * リクエストからJWTトークンを抽出
     * 優先順位: 1. Authorizationヘッダー, 2. authToken Cookie
     */
    private String extractToken(HttpServletRequest request) {
        // 1. Authorizationヘッダーをチェック
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            authorizationHeader = request.getHeader("authorization");
        }
        
        if (authorizationHeader != null && !authorizationHeader.trim().isEmpty()) {
            authorizationHeader = authorizationHeader.trim();
            if (authorizationHeader.startsWith("Bearer ")) {
                return authorizationHeader.substring(7);
            } else if (authorizationHeader.startsWith("Bearer")) {
                return authorizationHeader.substring(6).trim();
            }
        }
        
        // 2. Cookieから取得
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    logger.debug("CookieからauthTokenを取得しました");
                    return cookie.getValue();
                }
            }
        }
        
        return null;
    }
}
