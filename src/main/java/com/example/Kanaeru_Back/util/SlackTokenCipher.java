package com.example.Kanaeru_Back.util;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * SLACK_WORKSPACES.BOT_TOKEN をAES-256-GCMで暗号化/復号する。
 * 暗号文はBase64(IV(12byte) || 暗号文 || 認証タグ)の1本の文字列にして
 * BOT_TOKEN VARCHAR2(500) にそのまま保存できるようにしている。
 */
@Component
public class SlackTokenCipher {

    private static final Logger logger = LoggerFactory.getLogger(SlackTokenCipher.class);
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int IV_LENGTH_BYTES = 12;

    @Value("${slack.token.encryption-key:}")
    private String encryptionKeyBase64;

    private SecretKeySpec secretKey;

    @PostConstruct
    public void init() {
        if (encryptionKeyBase64 == null || encryptionKeyBase64.isBlank()) {
            logger.warn("slack.token.encryption-key が未設定です。Slack OAuth連携機能の利用時に例外が発生します。");
            return;
        }
        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(encryptionKeyBase64);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("slack.token.encryption-key はBase64形式で指定してください", e);
        }
        if (keyBytes.length != 32) {
            throw new IllegalStateException(
                    "slack.token.encryption-key は256bit(32byte)をBase64エンコードした値である必要があります");
        }
        this.secretKey = new SecretKeySpec(keyBytes, "AES");
    }

    public String encrypt(String plainToken) {
        requireKey();
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] cipherBytes = cipher.doFinal(plainToken.getBytes(StandardCharsets.UTF_8));

            ByteBuffer buffer = ByteBuffer.allocate(iv.length + cipherBytes.length);
            buffer.put(iv);
            buffer.put(cipherBytes);
            return Base64.getEncoder().encodeToString(buffer.array());
        } catch (Exception e) {
            throw new SlackTokenCipherException("Slack Bot Tokenの暗号化に失敗しました", e);
        }
    }

    public String decrypt(String cipherTextBase64) {
        requireKey();
        try {
            byte[] all = Base64.getDecoder().decode(cipherTextBase64);
            byte[] iv = Arrays.copyOfRange(all, 0, IV_LENGTH_BYTES);
            byte[] cipherBytes = Arrays.copyOfRange(all, IV_LENGTH_BYTES, all.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] plainBytes = cipher.doFinal(cipherBytes);
            return new String(plainBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SlackTokenCipherException("Slack Bot Tokenの復号に失敗しました", e);
        }
    }

    private void requireKey() {
        if (secretKey == null) {
            throw new IllegalStateException("slack.token.encryption-key が未設定のため暗号化/復号できません");
        }
    }
}
