package com.example.Kanaeru_Back.controller;

import com.example.Kanaeru_Back.model.ApiAuthForgotPasswordPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthLoginPost200Response;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationAdminPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPost200Response;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthResetPasswordPostRequest;
import com.example.Kanaeru_Back.model.ApiAuthUpdatePasswordPutRequest;
import com.example.Kanaeru_Back.model.ApiAvailabilityGet200Response;
import com.example.Kanaeru_Back.model.ApiClientGet200Response;
import com.example.Kanaeru_Back.model.ApiGrossProfitUpdatePut200Response;
import com.example.Kanaeru_Back.model.ApiHomeGet200Response;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePostRequest;
import com.example.Kanaeru_Back.model.ApiLargeGoalsLargeGoalIdDetailGet200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsChartIdLargeGoalsGet200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsChartIdMainGoalGet200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsChartIdUpdatePutRequest;
import com.example.Kanaeru_Back.model.ApiMandalaChartsCreatePostRequest;
import com.example.Kanaeru_Back.model.ApiMandalaChartsCreatePostRequestMainGoal;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdGet200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsMiddleGoalIdDetailGet200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsMiddleGoalIdUpdatePutRequest;
import com.example.Kanaeru_Back.model.ApiNetAssetUpdatePut200Response;
import com.example.Kanaeru_Back.model.ApiOperatingProfitUpdatePut200Response;
import com.example.Kanaeru_Back.model.ApiSaleUpdatePut200Response;
import com.example.Kanaeru_Back.model.ApiSettingUpdateAdminPut200Response;
import com.example.Kanaeru_Back.model.ApiSettingUpdateUserPut200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdGet200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsSmallGoalIdCompletePut200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsSmallGoalIdDetailGet200Response;
import com.example.Kanaeru_Back.model.ApiSupportGet200Response;
import com.example.Kanaeru_Back.model.ApiSupportReservationAllGet200Response;
import com.example.Kanaeru_Back.model.ApiSupportReservationApprovalPostRequest;
import com.example.Kanaeru_Back.model.ApiSupportReservationGet200Response;
import com.example.Kanaeru_Back.model.ApiSupportReservationPostRequest;
import com.example.Kanaeru_Back.model.ApiSupportSendPostRequest;
import com.example.Kanaeru_Back.model.ApiSupportStreamGet200Response;
import com.example.Kanaeru_Back.model.ApiYearlyBudgetActualGet200Response;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.model.NetAssetsSchema;
import com.example.Kanaeru_Back.model.OperatingProfitSchema;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.example.Kanaeru_Back.service.auth.LoginService;
import com.example.Kanaeru_Back.service.auth.LogoutService;
import com.example.Kanaeru_Back.service.auth.RegistrationUserService;
import com.example.Kanaeru_Back.service.auth.UpdatePasswordService;
import com.example.Kanaeru_Back.service.mandalaChart.CreateService;
import com.example.Kanaeru_Back.service.mandalaChart.GetService;
import com.example.Kanaeru_Back.service.mandalaChart.UpdateMainGoalService;
import com.example.Kanaeru_Back.service.setting.UserService;
import com.example.Kanaeru_Back.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;

@RestController
public class DefaultApiController implements DefaultApi {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApiController.class);

    @Autowired
    private RegistrationUserService registrationUserService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private UpdatePasswordService updatePasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("mandalaChartCreateService")
    private CreateService createService;

    @Autowired
    private GetService getService;

    @Autowired
    private UpdateMainGoalService updateMainGoalService;

    @Autowired
    private com.example.Kanaeru_Back.service.largeGoals.CreateService largeGoalCreateService;

    @Autowired
    private com.example.Kanaeru_Back.service.largeGoals.UpdateService largeGoalUpdateService;

    @Autowired
    private com.example.Kanaeru_Back.service.largeGoals.GetMiddleGoalsService getMiddleGoalsService;

    @Autowired
    private com.example.Kanaeru_Back.service.middleGoals.CreateService middleGoalCreateService;

    @Autowired
    private com.example.Kanaeru_Back.service.middleGoals.UpdateService middleGoalUpdateService;

    @Autowired
    private com.example.Kanaeru_Back.service.smallGoals.GetService smallGoalGetService;

    @Autowired
    private com.example.Kanaeru_Back.service.smallGoals.CreateService smallGoalCreateService;

    @Autowired
    private com.example.Kanaeru_Back.service.smallGoals.UpdateService smallGoalUpdateService;

    @Autowired
    private com.example.Kanaeru_Back.service.smallGoals.CompleteService smallGoalCompleteService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthRegistrationUserPost(
            ApiAuthRegistrationUserPostRequest apiAuthRegistrationUserPostRequest) {
        ApiAuthRegistrationUserPost200Response response = registrationUserService.registerUser(apiAuthRegistrationUserPostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthDeleteDelete(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthForgotPasswordPost(
            ApiAuthForgotPasswordPostRequest apiAuthForgotPasswordPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthLoginPost200Response> apiAuthLoginPost(String email, String passwordHash) {
        ApiAuthLoginPost200Response response = loginService.login(email, passwordHash);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiAuthLogoutPost(
            ApiAuthLogoutPostRequest apiAuthLogoutPostRequest) {
        ApiAuthLogoutPost200Response response = logoutService.logout(apiAuthLogoutPostRequest.getToken());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthRegistrationAdminPost(
            ApiAuthRegistrationAdminPostRequest apiAuthRegistrationAdminPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthResetPasswordPost(
            ApiAuthResetPasswordPostRequest apiAuthResetPasswordPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiAuthUpdatePasswordPut(
            ApiAuthUpdatePasswordPutRequest apiAuthUpdatePasswordPutRequest) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();
        
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                logger.error("RequestContextHolder.getRequestAttributes() returned null");
                response.setResponseStatus(0);
                response.setMessage("リクエスト情報が取得できません");
                return ResponseEntity.ok(response);
            }
            
            jakarta.servlet.http.HttpServletRequest request = attributes.getRequest();
            
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null) {
                authorizationHeader = request.getHeader("authorization");
            }
            
            logger.debug("Authorization header: {}", authorizationHeader != null ? "present" : "null");
            logger.debug("All headers: {}", java.util.Collections.list(request.getHeaderNames()));
            
            if (authorizationHeader == null || authorizationHeader.trim().isEmpty()) {
                logger.warn("Authorization header is missing or empty");
                response.setResponseStatus(0);
                response.setMessage("認証トークンがありません。リクエストヘッダーに「Authorization: Bearer <token>」を含めてください。");
                return ResponseEntity.ok(response);
            }
            
            authorizationHeader = authorizationHeader.trim();
            String token;
            if (authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
            } else if (authorizationHeader.startsWith("Bearer")) {
                token = authorizationHeader.substring(6).trim();
            } else {
                logger.warn("Authorization header does not start with 'Bearer': {}", authorizationHeader);
                response.setResponseStatus(0);
                response.setMessage("認証トークンの形式が正しくありません。「Bearer <token>」の形式で送信してください。");
                return ResponseEntity.ok(response);
            }
            
            if (token == null || token.isEmpty()) {
                logger.warn("Token is empty after extracting from Authorization header");
                response.setResponseStatus(0);
                response.setMessage("認証トークンが空です");
                return ResponseEntity.ok(response);
            }
            
            String userId = jwtUtil.extractUserId(token);
            logger.debug("Extracted userId from token: {}", userId);
            
            response = updatePasswordService.updatePassword(
                    userId,
                    apiAuthUpdatePasswordPutRequest.getCurrentPasswordHash(),
                    apiAuthUpdatePasswordPutRequest.getNewPasswordHash());
        } catch (Exception e) {
            logger.error("Error in apiAuthUpdatePasswordPut", e);
            response.setResponseStatus(0);
            response.setMessage("パスワード更新中にエラーが発生しました: " + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAvailabilityGet200Response> apiAvailabilityGet(String userId, LocalDate date) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiClientGet200Response> apiClientGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<Void> apiGoogleAuthorizeGet(String userId, String returnUrl) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<Void> apiGoogleCallbackGet(String state, String code, String error, String errorDescription) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiGrossProfitUpdatePut200Response> apiGrossProfitUpdatePut(GrossProfitSchema grossProfitSchema) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiHomeGet200Response> apiHomeGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiLargeGoalsChartIdCreatePost200Response> apiLargeGoalsChartIdCreatePost(
            String chartId, ApiLargeGoalsChartIdCreatePostRequest apiLargeGoalsChartIdCreatePostRequest) {
        ApiLargeGoalsChartIdCreatePost200Response response = largeGoalCreateService.createLargeGoal(chartId, apiLargeGoalsChartIdCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiLargeGoalsLargeGoalIdDeleteDelete(
            String largeGoalId, String chartId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiLargeGoalsLargeGoalIdDetailGet200Response> apiLargeGoalsLargeGoalIdDetailGet(
            String largeGoalId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiMiddleGoalsLargeGoalIdGet200Response> apiMiddleGoalsLargeGoalIdGet(String largeGoalId) {
        ApiMiddleGoalsLargeGoalIdGet200Response response = getMiddleGoalsService.getMiddleGoals(largeGoalId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiLargeGoalsLargeGoalIdUpdatePut(
            String largeGoalId, ApiLargeGoalsChartIdCreatePostRequest apiLargeGoalsChartIdCreatePostRequest) {
        ApiAuthLogoutPost200Response response = largeGoalUpdateService.updateLargeGoal(largeGoalId, apiLargeGoalsChartIdCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiMandalaChartsChartIdDeleteDelete(
            String chartId, String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiMandalaChartsChartIdLargeGoalsGet200Response> apiMandalaChartsChartIdLargeGoalsGet(
            String chartId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiMandalaChartsChartIdMainGoalGet200Response> apiMandalaChartsChartIdMainGoalGet(
            String chartId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiMandalaChartsChartIdMainGoalUpdatePut(
            String chartId, ApiMandalaChartsCreatePostRequestMainGoal apiMandalaChartsCreatePostRequestMainGoal) {
        ApiAuthLogoutPost200Response response = updateMainGoalService.updateMainGoal(chartId, apiMandalaChartsCreatePostRequestMainGoal);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiMandalaChartsChartIdUpdatePut(
            String chartId, ApiMandalaChartsChartIdUpdatePutRequest apiMandalaChartsChartIdUpdatePutRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiMandalaChartsCreatePost(
            ApiMandalaChartsCreatePostRequest apiMandalaChartsCreatePostRequest) {
        ApiAuthRegistrationUserPost200Response response = createService.createMandalaChart(apiMandalaChartsCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiMandalaChartsGet200Response> apiMandalaChartsGet(String userId) {
        ApiMandalaChartsGet200Response response = getService.getMandalaCharts(userId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiMiddleGoalsLargeGoalIdCreatePost200Response> apiMiddleGoalsLargeGoalIdCreatePost(
            String largeGoalId, ApiMiddleGoalsLargeGoalIdCreatePostRequest apiMiddleGoalsLargeGoalIdCreatePostRequest) {
        ApiMiddleGoalsLargeGoalIdCreatePost200Response response = middleGoalCreateService.createMiddleGoal(largeGoalId, apiMiddleGoalsLargeGoalIdCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiMiddleGoalsMiddleGoalIdDeleteDelete(
            String middleGoalId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiMiddleGoalsMiddleGoalIdDetailGet200Response> apiMiddleGoalsMiddleGoalIdDetailGet(
            String middleGoalId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSmallGoalsMiddleGoalIdGet200Response> apiSmallGoalsMiddleGoalIdGet(String middleGoalId) {
        ApiSmallGoalsMiddleGoalIdGet200Response response = smallGoalGetService.getSmallGoals(middleGoalId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiMiddleGoalsMiddleGoalIdUpdatePut(
            String middleGoalId, ApiMiddleGoalsMiddleGoalIdUpdatePutRequest apiMiddleGoalsMiddleGoalIdUpdatePutRequest) {
        ApiAuthLogoutPost200Response response = middleGoalUpdateService.updateMiddleGoal(middleGoalId, apiMiddleGoalsMiddleGoalIdUpdatePutRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiNetAssetUpdatePut200Response> apiNetAssetUpdatePut(NetAssetsSchema netAssetsSchema) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiOperatingProfitUpdatePut200Response> apiOperatingProfitUpdatePut(
            OperatingProfitSchema operatingProfitSchema) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSaleUpdatePut200Response> apiSaleUpdatePut(SaleSchema saleSchema) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSettingUpdateAdminPut200Response> apiSettingAdminGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSettingUpdateAdminPut200Response> apiSettingUpdateAdminPut(
            ApiAuthRegistrationAdminPostRequest apiAuthRegistrationAdminPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSettingUpdateUserPut200Response> apiSettingUpdateUserPut(
            ApiAuthRegistrationUserPostRequest apiAuthRegistrationUserPostRequest) {
        ApiSettingUpdateUserPut200Response response = userService.updateUserSetting(
                apiAuthRegistrationUserPostRequest.getUserSchema(),
                apiAuthRegistrationUserPostRequest.getSettingSchema());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiSettingUpdateUserPut200Response> apiSettingUserGet(String userId) {
        ApiSettingUpdateUserPut200Response response = userService.getUserSetting(userId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiSmallGoalsMiddleGoalIdCreatePost200Response> apiSmallGoalsMiddleGoalIdCreatePost(
            String middleGoalId, ApiSmallGoalsMiddleGoalIdCreatePostRequest apiSmallGoalsMiddleGoalIdCreatePostRequest) {
        ApiSmallGoalsMiddleGoalIdCreatePost200Response response = smallGoalCreateService.createSmallGoal(middleGoalId, apiSmallGoalsMiddleGoalIdCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiSmallGoalsSmallGoalIdCompletePut200Response> apiSmallGoalsSmallGoalIdCompletePut(
            String smallGoalId) {
        ApiSmallGoalsSmallGoalIdCompletePut200Response response = smallGoalCompleteService.completeSmallGoal(smallGoalId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiSmallGoalsSmallGoalIdDeleteDelete(
            String smallGoalId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSmallGoalsSmallGoalIdDetailGet200Response> apiSmallGoalsSmallGoalIdDetailGet(
            String smallGoalId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiSmallGoalsSmallGoalIdUpdatePut(
            String smallGoalId, ApiSmallGoalsMiddleGoalIdCreatePostRequest apiSmallGoalsMiddleGoalIdCreatePostRequest) {
        ApiAuthLogoutPost200Response response = smallGoalUpdateService.updateSmallGoal(smallGoalId, apiSmallGoalsMiddleGoalIdCreatePostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiSupportGet200Response> apiSupportGet(String userId, String selecteId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSupportGet200Response> apiSupportReadGet(
            String senderId, String recipientId, String content, Integer messageSeq) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSupportReservationAllGet200Response> apiSupportReservationAllGet(
            String userId, String selecteId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiSupportReservationApprovalPost(
            ApiSupportReservationApprovalPostRequest apiSupportReservationApprovalPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSupportReservationGet200Response> apiSupportReservationGet(
            String userId, String selecteId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiSupportReservationPost(
            ApiSupportReservationPostRequest apiSupportReservationPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiSupportReservationRejectPost(
            ApiSupportReservationApprovalPostRequest apiSupportReservationApprovalPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSupportGet200Response> apiSupportSendPost(
            ApiSupportSendPostRequest apiSupportSendPostRequest) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiSupportStreamGet200Response> apiSupportStreamGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiYearlyBudgetActualGet200Response> apiYearlyBudgetActualGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

