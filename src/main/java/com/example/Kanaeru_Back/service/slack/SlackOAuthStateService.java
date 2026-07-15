package com.example.Kanaeru_Back.service.slack;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Slack OAuthのstateパラメータ（CSRF対策）を発行・検証する。
 * ログイン用のJwtUtilとは責務が異なるため鍵・実装ともに独立させている。
 * 認可URLを管理者に共有して後で開いてもらう運用を想定し、有効期限は24時間としている。
 */
@Service
public class SlackOAuthStateService {

    private static final long STATE_EXPIRATION_MILLIS = 24 * 60 * 60 * 1000L;

    @Value("${jwt.state.secret:KanaeruSlackOAuthStateSecretKey2024ChangeMeInProd}")
    private String secret;

    private SecretKey getSigningKey() {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    public String generateState(String userId, String returnUrl) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + STATE_EXPIRATION_MILLIS);

        Map<String, Object> claims = new HashMap<>();
        claims.put("returnUrl", returnUrl);
        claims.put("nonce", UUID.randomUUID().toString());

        return Jwts.builder()
                .claims(claims)
                .subject(userId)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    public SlackOAuthStateClaims verifyState(String state) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(state)
                    .getPayload();

            String userId = claims.getSubject();
            String returnUrl = claims.get("returnUrl", String.class);
            if (userId == null || returnUrl == null) {
                throw new SlackOAuthStateInvalidException("stateトークンに必要な情報が含まれていません");
            }
            return new SlackOAuthStateClaims(userId, returnUrl);
        } catch (SlackOAuthStateInvalidException e) {
            throw e;
        } catch (Exception e) {
            throw new SlackOAuthStateInvalidException("stateトークンの検証に失敗しました（期限切れまたは改ざん）", e);
        }
    }

    public record SlackOAuthStateClaims(String userId, String returnUrl) {
    }
}
