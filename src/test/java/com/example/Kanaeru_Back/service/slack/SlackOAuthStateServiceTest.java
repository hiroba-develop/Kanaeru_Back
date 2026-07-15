package com.example.Kanaeru_Back.service.slack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SlackOAuthStateServiceTest {

    private SlackOAuthStateService stateService;

    @BeforeEach
    void setUp() {
        stateService = new SlackOAuthStateService();
        ReflectionTestUtils.setField(stateService, "secret", "test-only-state-secret-key-not-for-production-use");
    }

    @Test
    void generateStateThenVerify_returnsOriginalUserIdAndReturnUrl() {
        String userId = "user-12345";
        String returnUrl = "https://kanaeru.etomoji.co.jp/setting";

        String state = stateService.generateState(userId, returnUrl);
        SlackOAuthStateService.SlackOAuthStateClaims claims = stateService.verifyState(state);

        assertThat(claims.userId()).isEqualTo(userId);
        assertThat(claims.returnUrl()).isEqualTo(returnUrl);
    }

    @Test
    void generateState_producesDifferentTokenEachTime_becauseNonceIsRandom() {
        String state1 = stateService.generateState("user-1", "https://example.com/a");
        String state2 = stateService.generateState("user-1", "https://example.com/a");

        assertThat(state1).isNotEqualTo(state2);
    }

    @Test
    void verifyState_withTamperedToken_throwsInvalidException() {
        String state = stateService.generateState("user-1", "https://example.com/a");
        String tampered = state.substring(0, state.length() - 1) + (state.endsWith("a") ? "b" : "a");

        assertThatThrownBy(() -> stateService.verifyState(tampered))
                .isInstanceOf(SlackOAuthStateInvalidException.class);
    }

    @Test
    void verifyState_signedWithDifferentSecret_throwsInvalidException() {
        String state = stateService.generateState("user-1", "https://example.com/a");

        SlackOAuthStateService otherService = new SlackOAuthStateService();
        ReflectionTestUtils.setField(otherService, "secret", "a-completely-different-secret-key-value");

        assertThatThrownBy(() -> otherService.verifyState(state))
                .isInstanceOf(SlackOAuthStateInvalidException.class);
    }
}
