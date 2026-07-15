package com.example.Kanaeru_Back.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.SecureRandom;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SlackTokenCipherTest {

    private SlackTokenCipher cipher;

    @BeforeEach
    void setUp() {
        cipher = new SlackTokenCipher();
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        ReflectionTestUtils.setField(cipher, "encryptionKeyBase64", Base64.getEncoder().encodeToString(key));
        cipher.init();
    }

    @Test
    void encryptThenDecrypt_returnsOriginalPlainText() {
        String plainToken = "xoxb-dummy-test-token-0000000000-0000000000000-0000000000000000000000000";

        String encrypted = cipher.encrypt(plainToken);
        String decrypted = cipher.decrypt(encrypted);

        assertThat(decrypted).isEqualTo(plainToken);
        assertThat(encrypted).isNotEqualTo(plainToken);
    }

    @Test
    void encrypt_producesDifferentCipherTextEachTime_becauseIvIsRandom() {
        String plainToken = "xoxb-same-token";

        String encryptedFirst = cipher.encrypt(plainToken);
        String encryptedSecond = cipher.encrypt(plainToken);

        assertThat(encryptedFirst).isNotEqualTo(encryptedSecond);
        assertThat(cipher.decrypt(encryptedFirst)).isEqualTo(plainToken);
        assertThat(cipher.decrypt(encryptedSecond)).isEqualTo(plainToken);
    }

    @Test
    void decrypt_withTamperedCipherText_throwsException() {
        String encrypted = cipher.encrypt("xoxb-token-to-tamper");
        byte[] raw = Base64.getDecoder().decode(encrypted);
        raw[raw.length - 1] ^= 0x01; // 認証タグ末尾を1bit改ざん
        String tampered = Base64.getEncoder().encodeToString(raw);

        assertThatThrownBy(() -> cipher.decrypt(tampered))
                .isInstanceOf(SlackTokenCipherException.class);
    }

    @Test
    void encrypt_withoutEncryptionKeyConfigured_throwsIllegalStateException() {
        SlackTokenCipher noKeyCipher = new SlackTokenCipher();
        // encryptionKeyBase64 未設定のままinit()するとキーが無い状態になる
        noKeyCipher.init();

        assertThatThrownBy(() -> noKeyCipher.encrypt("xoxb-anything"))
                .isInstanceOf(IllegalStateException.class);
    }
}
