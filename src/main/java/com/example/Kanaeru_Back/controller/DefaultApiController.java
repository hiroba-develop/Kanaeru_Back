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
import com.example.Kanaeru_Back.model.ApiContactsSendPostRequest;
import com.example.Kanaeru_Back.model.ApiGetAdminUsersGet200Response;
import com.example.Kanaeru_Back.model.ApiGetUsersGet200Response;
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
import com.example.Kanaeru_Back.model.ApiSettingUpdateUserPut200Response;
import com.example.Kanaeru_Back.model.ApiSettingUserImagePost200Response;
import com.example.Kanaeru_Back.model.ApiUpdateAdminUsersPut200Response;
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
import com.example.Kanaeru_Back.service.auth.ForgotPasswordService;
import com.example.Kanaeru_Back.service.auth.LoginService;
import com.example.Kanaeru_Back.service.auth.LogoutService;
import com.example.Kanaeru_Back.service.auth.RegistrationAdminService;
import com.example.Kanaeru_Back.service.auth.RegistrationUserService;
import com.example.Kanaeru_Back.service.auth.ResetPasswordService;
import com.example.Kanaeru_Back.service.auth.UpdatePasswordService;
import com.example.Kanaeru_Back.service.contacts.ContactsService;
import com.example.Kanaeru_Back.service.users.delete.AccountService;
import com.example.Kanaeru_Back.service.users.get.AdminUsersService;
import com.example.Kanaeru_Back.service.users.get.UsersService;
import com.example.Kanaeru_Back.service.mandalaChart.CreateService;
import com.example.Kanaeru_Back.service.mandalaChart.GetService;
import com.example.Kanaeru_Back.service.mandalaChart.UpdateMainGoalService;
import com.example.Kanaeru_Back.service.setting.UserService;
import com.example.Kanaeru_Back.service.setting.UserImageService;
import com.example.Kanaeru_Back.service.screenDisplay.YearlyBudgetActualService;
import com.example.Kanaeru_Back.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
public class DefaultApiController implements DefaultApi {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApiController.class);

    @Autowired
    private RegistrationUserService registrationUserService;

    @Autowired
    private RegistrationAdminService registrationAdminService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private UpdatePasswordService updatePasswordService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserImageService userImageService;

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

    @Autowired
    private YearlyBudgetActualService yearlyBudgetActualService;

    @Autowired
    private com.example.Kanaeru_Back.service.sale.UpdateService saleUpdateService;

    @Autowired
    private com.example.Kanaeru_Back.service.grossProfit.UpdateService grossProfitUpdateService;

    @Autowired
    private com.example.Kanaeru_Back.service.operatingProfit.UpdateService operatingProfitUpdateService;

    @Autowired
    @Qualifier("getAdminUsersService")
    private AdminUsersService adminUsersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    @Qualifier("updateAdminUsersService")
    private com.example.Kanaeru_Back.service.users.update.AdminUsersService updateAdminUsersService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private com.example.Kanaeru_Back.service.screenDisplay.HomeService homeService;

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
        ApiAuthRegistrationUserPost200Response response = forgotPasswordService.sendPasswordResetEmail(apiAuthForgotPasswordPostRequest);
        return ResponseEntity.ok(response);
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
        ApiAuthRegistrationUserPost200Response response = registrationAdminService.registerAdmin(apiAuthRegistrationAdminPostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthRegistrationUserPost200Response> apiAuthResetPasswordPost(
            ApiAuthResetPasswordPostRequest apiAuthResetPasswordPostRequest) {
        ApiAuthRegistrationUserPost200Response response = resetPasswordService.resetPassword(apiAuthResetPasswordPostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiAuthUpdatePasswordPut(
            ApiAuthUpdatePasswordPutRequest apiAuthUpdatePasswordPutRequest) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();
        
        try {
            // SecurityContextから認証済みユーザーIDを取得
            org.springframework.security.core.Authentication authentication = 
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated()) {
                logger.warn("認証情報が見つかりません");
                response.setResponseStatus(0);
                response.setMessage("認証が必要です");
                return ResponseEntity.ok(response);
            }
            
            String userId = (String) authentication.getPrincipal();
            logger.debug("Authenticated userId: {}", userId);
            
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
    public ResponseEntity<ApiAuthLogoutPost200Response> apiContactsSendPost(
            ApiContactsSendPostRequest apiContactsSendPostRequest) {
        ApiAuthLogoutPost200Response response = contactsService.sendContactEmail(apiContactsSendPostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiGetAdminUsersGet200Response> apiGetAdminUsersGet() {
        logger.info("apiGetAdminUsersGet called");
        ApiGetAdminUsersGet200Response response = adminUsersService.getAdminUsers();
        logger.info("apiGetAdminUsersGet response status: {}, user count: {}", 
            response.getResponseStatus(),
            response.getAdminUserListSchema() != null ? response.getAdminUserListSchema().size() : 0);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiGetUsersGet200Response> apiGetUsersGet() {
        ApiGetUsersGet200Response response = usersService.getUsers();
        return ResponseEntity.ok(response);
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
        ApiGrossProfitUpdatePut200Response response = grossProfitUpdateService.updateGrossProfit(grossProfitSchema);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiHomeGet200Response> apiHomeGet(String userId) {
        ApiHomeGet200Response response = new ApiHomeGet200Response();
        
        try {
            // SecurityContextから認証済みユーザー情報を取得
            org.springframework.security.core.Authentication authentication = 
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated()) {
                logger.warn("認証情報が見つかりません");
                response.setResponseStatus(0);
                return ResponseEntity.ok(response);
            }
            
            String loggedInUserId = (String) authentication.getPrincipal();
            
            // ロール情報を取得
            String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                .orElse("0");
            
            logger.debug("apiHomeGet - loggedInUserId: {}, role: {}, userId param: {}", loggedInUserId, role, userId);
            
            // role:0の場合は自分のユーザーID、role:1または2の場合は選択されているユーザーIDを使用
            String targetUserId;
            if ("0".equals(role)) {
                targetUserId = loggedInUserId;
            } else if ("1".equals(role) || "2".equals(role)) {
                if (userId == null || userId.isEmpty()) {
                    logger.warn("userId parameter is null or empty for role: {}", role);
                    response.setResponseStatus(0);
                    return ResponseEntity.ok(response);
                }
                targetUserId = userId;
            } else {
                logger.warn("Invalid role: {}", role);
                response.setResponseStatus(0);
                return ResponseEntity.ok(response);
            }
            
            logger.info("apiHomeGet - targetUserId: {}", targetUserId);
            response = homeService.getHomeData(targetUserId);
            logger.info("apiHomeGet - response status: {}, mainGoalSchema: {}, largeGoalSchema size: {}", 
                response.getResponseStatus(),
                response.getMainGoalSchema() != null ? "present" : "null",
                response.getLargeGoalSchema() != null ? response.getLargeGoalSchema().size() : 0);
        } catch (Exception e) {
            logger.error("Error in apiHomeGet", e);
            e.printStackTrace();
            if (response == null) {
                response = new ApiHomeGet200Response();
            }
            response.setResponseStatus(0);
        }
        
        return ResponseEntity.ok(response);
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
        ApiOperatingProfitUpdatePut200Response response = operatingProfitUpdateService.updateOperatingProfit(operatingProfitSchema);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiSaleUpdatePut200Response> apiSaleUpdatePut(SaleSchema saleSchema) {
        ApiSaleUpdatePut200Response response = saleUpdateService.updateSale(saleSchema);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiUpdateAdminUsersPut200Response> apiSettingAdminGet(String userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiUpdateAdminUsersPut200Response> apiSettingUpdateAdminPut(
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
    public ResponseEntity<ApiSettingUserImagePost200Response> apiSettingUserImagePost(
            String userId, MultipartFile imageFile) {
        ApiSettingUserImagePost200Response response = new ApiSettingUserImagePost200Response();
        
        try {
            if (userId == null || userId.isEmpty()) {
                response.setResponseStatus(0);
                return ResponseEntity.ok(response);
            }
            
            if (imageFile == null || imageFile.isEmpty()) {
                response.setResponseStatus(0);
                return ResponseEntity.ok(response);
            }
            
            String imageUrl = userImageService.uploadUserImage(userId, imageFile);
            response.setResponseStatus(1);
            response.setImageUrl(imageUrl);
        } catch (Exception e) {
            logger.error("Error uploading user image", e);
            response.setResponseStatus(0);
        }
        
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
        ApiYearlyBudgetActualGet200Response response = yearlyBudgetActualService.getYearlyBudgetActual(userId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiUpdateAdminUsersPut200Response> apiUpdateAdminUsersPut(
            ApiAuthRegistrationAdminPostRequest apiAuthRegistrationAdminPostRequest) {
        ApiUpdateAdminUsersPut200Response response = updateAdminUsersService.updateAdminUser(apiAuthRegistrationAdminPostRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiDeleteAccountDelete(String userId) {
        ApiAuthLogoutPost200Response response = accountService.deleteAccount(userId);
        return ResponseEntity.ok(response);
    }
}

