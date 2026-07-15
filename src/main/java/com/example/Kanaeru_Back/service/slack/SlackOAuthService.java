package com.example.Kanaeru_Back.service.slack;

import com.example.Kanaeru_Back.entity.SlackUserMappingEntity;
import com.example.Kanaeru_Back.entity.SlackWorkspaceEntity;
import com.example.Kanaeru_Back.model.SlackOauthStatusResponse;
import com.example.Kanaeru_Back.repository.SlackUserMappingRepository;
import com.example.Kanaeru_Back.repository.SlackWorkspaceRepository;
import com.example.Kanaeru_Back.util.SlackTokenCipher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

/**
 * SlackのOAuth v2認可フロー（authorize/callback）と連携状態取得を担う。
 * 1ワークスペースに複数kanaeruユーザーが紐づくため、同じフローが
 * 「新規ワークスペース導入」「同じ会社の2人目以降のメンバー連携」の両方で使われる。
 */
@Service
public class SlackOAuthService {

    private static final Logger logger = LoggerFactory.getLogger(SlackOAuthService.class);
    private static final String AUTHORIZE_URL = "https://slack.com/oauth/v2/authorize";
    private static final String ACCESS_TOKEN_URL = "https://slack.com/api/oauth.v2.access";

    @Value("${slack.oauth.client-id:}")
    private String clientId;

    @Value("${slack.oauth.client-secret:}")
    private String clientSecret;

    @Value("${slack.oauth.redirect-uri:}")
    private String redirectUri;

    @Value("${slack.oauth.scopes:}")
    private String scopes;

    @Value("${app.frontend.url:http://localhost:5180}")
    private String frontendUrl;

    @Autowired
    private SlackOAuthStateService slackOAuthStateService;

    @Autowired
    private SlackTokenCipher slackTokenCipher;

    @Autowired
    private SlackWorkspaceRepository slackWorkspaceRepository;

    @Autowired
    private SlackUserMappingRepository slackUserMappingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public String buildAuthorizeUrl(String userId, String returnUrl) {
        String state = slackOAuthStateService.generateState(userId, returnUrl);
        return UriComponentsBuilder.fromUriString(AUTHORIZE_URL)
                .queryParam("client_id", clientId)
                .queryParam("scope", scopes)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("state", state)
                .build()
                .toUriString();
    }

    public OAuthCallbackResult handleCallback(String state, String code, String error) {
        SlackOAuthStateService.SlackOAuthStateClaims claims;
        try {
            claims = slackOAuthStateService.verifyState(state);
        } catch (SlackOAuthStateInvalidException e) {
            logger.warn("Slack OAuth stateの検証に失敗しました", e);
            return new OAuthCallbackResult(false, frontendUrl, "invalid_state");
        }

        if (error != null && !error.isBlank()) {
            logger.info("Slack OAuth認可がキャンセルまたは失敗しました reason={}", error);
            return new OAuthCallbackResult(false, claims.returnUrl(), error);
        }

        if (code == null || code.isBlank()) {
            return new OAuthCallbackResult(false, claims.returnUrl(), "missing_code");
        }

        try {
            JsonNode result = exchangeCodeForToken(code);
            if (!result.path("ok").asBoolean(false)) {
                logger.error("Slack oauth.v2.access がエラーを返しました: {}", result.path("error").asText());
                return new OAuthCallbackResult(false, claims.returnUrl(), "slack_api_error");
            }

            String teamId = result.path("team").path("id").asText();
            String teamName = result.path("team").path("name").asText();
            String botUserId = result.path("bot_user_id").asText();
            String accessToken = result.path("access_token").asText();
            String authedUserId = result.path("authed_user").path("id").asText();
            String grantedScope = result.path("scope").asText();

            String workspaceId = upsertWorkspace(teamId, teamName, botUserId, accessToken, grantedScope, claims.userId());
            upsertUserMapping(claims.userId(), workspaceId, authedUserId);

            return new OAuthCallbackResult(true, claims.returnUrl(), null);
        } catch (Exception e) {
            logger.error("Slack OAuthコールバック処理に失敗しました", e);
            return new OAuthCallbackResult(false, claims.returnUrl(), "internal_error");
        }
    }

    public SlackOauthStatusResponse getStatus(String userId) {
        SlackOauthStatusResponse response = new SlackOauthStatusResponse();
        Optional<SlackUserMappingEntity> mapping = slackUserMappingRepository.findByUserIdAndDelFlg(userId, "0");
        if (mapping.isEmpty()) {
            response.setConnected(false);
            return response;
        }

        Optional<SlackWorkspaceEntity> workspace =
                slackWorkspaceRepository.findByWorkspaceIdAndDelFlg(mapping.get().getWorkspaceId(), "0");
        if (workspace.isEmpty()) {
            response.setConnected(false);
            return response;
        }

        response.setConnected(true);
        response.setTeamName(workspace.get().getTeamName());
        response.setSlackUserId(mapping.get().getSlackUserId());
        response.setConnectedAt(mapping.get().getCreatedAt());
        return response;
    }

    private JsonNode exchangeCodeForToken(String code) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        String responseBody = restTemplate.postForObject(ACCESS_TOKEN_URL, request, String.class);
        return objectMapper.readTree(responseBody);
    }

    /**
     * TEAM_IDはテーブル全体でUNIQUEなので、既存行があれば新規/再インストールを問わずUPDATEする。
     */
    private String upsertWorkspace(String teamId, String teamName, String botUserId,
                                    String accessToken, String scope, String installedByUserId) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        String encryptedToken = slackTokenCipher.encrypt(accessToken);

        Optional<SlackWorkspaceEntity> existing = slackWorkspaceRepository.findByTeamId(teamId);
        SlackWorkspaceEntity workspace = existing.orElseGet(SlackWorkspaceEntity::new);

        if (existing.isEmpty()) {
            workspace.setWorkspaceId(UUID.randomUUID().toString());
            workspace.setInstalledByUserId(installedByUserId);
            workspace.setCreatedAt(now);
        }
        workspace.setTeamId(teamId);
        workspace.setTeamName(teamName);
        workspace.setBotUserId(botUserId);
        workspace.setBotToken(encryptedToken);
        workspace.setScope(scope);
        workspace.setDelFlg("0");
        workspace.setUpdatedAt(now);
        slackWorkspaceRepository.save(workspace);
        return workspace.getWorkspaceId();
    }

    /**
     * kanaeruユーザー1人につき有効なSlackマッピングは1件の前提で、
     * 既存があればworkspaceId/slackUserIdを更新し、無ければ新規作成する。
     * （旧・手動入力機能で登録された古いマッピングが残っていても、ここで正しい値に上書きされる）
     */
    private void upsertUserMapping(String userId, String workspaceId, String slackUserId) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));

        Optional<SlackUserMappingEntity> existing =
                slackUserMappingRepository.findByUserIdAndDelFlg(userId, "0");

        SlackUserMappingEntity mapping = existing.orElseGet(SlackUserMappingEntity::new);
        if (existing.isEmpty()) {
            mapping.setMappingId(UUID.randomUUID().toString());
            mapping.setUserId(userId);
            mapping.setCreatedAt(now);
        }
        mapping.setWorkspaceId(workspaceId);
        mapping.setSlackUserId(slackUserId);
        mapping.setDelFlg("0");
        mapping.setUpdatedAt(now);
        slackUserMappingRepository.save(mapping);
    }

    public record OAuthCallbackResult(boolean success, String returnUrl, String reason) {
    }
}
