package com.example.Kanaeru_Back.service.users.delete;

import com.example.Kanaeru_Back.entity.*;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.repository.*;
import com.example.Kanaeru_Back.service.email.EmailTemplateService;
import com.example.Kanaeru_Back.service.stripe.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Transactional
    public ApiAuthLogoutPost200Response deleteAccount(String userId) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            String trimmedUserId = userId != null ? userId.trim() : null;
            if (trimmedUserId == null || trimmedUserId.isEmpty()) {
                logger.error("UserId is null or empty");
                response.setResponseStatus(0);
                response.setMessage("ユーザーIDが不正です");
                return response;
            }

            Optional<UserEntity> userOptional = userRepository.findById(trimmedUserId);

            if (userOptional.isEmpty()) {
                logger.error("User not found: {}", trimmedUserId);
                response.setResponseStatus(0);
                response.setMessage("ユーザーが見つかりません");
                return response;
            }

            UserEntity user = userOptional.get();

            if (!"0".equals(user.getDelFlg())) {
                logger.warn("User is already deleted: {}", trimmedUserId);
                response.setResponseStatus(0);
                response.setMessage("ユーザーは既に削除されています");
                return response;
            }

            // 有料プランが有効な場合は Stripe サブスクリプションを即時解約
            subscriptionService.cancelSubscriptionOnAccountDeletion(trimmedUserId);

            // ユーザーに紐づくすべてのデータを論理削除
            deleteUserRelatedData(trimmedUserId);

            // ★★★ メールアドレスと名前を一度だけ取得 ★★★
            String originalEmail = user.getEmail();
            String userName = user.getName();

            // 退会完了メールを送信
            try {
                boolean emailSent = emailTemplateService.sendAccountDeletedEmail(originalEmail, userName);
                if (emailSent) {
                    logger.info("Account deleted email sent successfully to: {}", originalEmail);
                } else {
                    logger.warn("Failed to send account deleted email to: {}", originalEmail);
                    // メール送信失敗しても退会処理は続行する
                }
            } catch (Exception e) {
                logger.error("Error sending account deleted email to: {}", originalEmail, e);
                // メール送信失敗しても退会処理は続行する
            }

            // ★★★ メールアドレスにdelete_YYYYMMDDを追加（ドメインの最後） ★★★
            String deletedEmail = addDeleteSuffixToEmail(originalEmail);
            user.setEmail(deletedEmail);
            user.setDelFlg("1");
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);

            logger.info("Account deleted successfully for user: {}", trimmedUserId);
            response.setResponseStatus(1);
            response.setMessage("アカウントを削除しました");

        } catch (Exception e) {
            logger.error("Error deleting account", e);
            response.setResponseStatus(0);
            response.setMessage("アカウント削除中にエラーが発生しました: " + e.getMessage());
            org.springframework.transaction.interceptor.TransactionAspectSupport
                .currentTransactionStatus().setRollbackOnly();
        }

        return response;
    }

    /**
     * ユーザーに紐づくすべてのデータを論理削除
     */
    private void deleteUserRelatedData(String userId) {
        // MANDALA_CHARTSを検索して論理削除
        List<MandalaChartEntity> mandalaCharts = mandalaChartRepository.findByUserIdAndDelFlg(userId, "0");

        for (MandalaChartEntity chart : mandalaCharts) {
            chart.setDelFlg("1");
            chart.setUpdatedAt(LocalDateTime.now());
            mandalaChartRepository.save(chart);

            // MAIN_GOALSを検索して論理削除
            List<MainGoalEntity> mainGoals = mainGoalRepository.findAll().stream()
                    .filter(goal -> chart.getChartId().equals(goal.getChartId()) && "0".equals(goal.getDelFlg()))
                    .toList();

            for (MainGoalEntity mainGoal : mainGoals) {
                mainGoal.setDelFlg("1");
                mainGoal.setUpdatedAt(LocalDateTime.now());
                mainGoalRepository.save(mainGoal);

                // LARGE_GOALSを検索して論理削除
                List<LargeGoalEntity> largeGoals = largeGoalRepository.findAll().stream()
                        .filter(goal -> mainGoal.getMainGoalId().equals(goal.getMainGoalId()) && "0".equals(goal.getDelFlg()))
                        .toList();

                for (LargeGoalEntity largeGoal : largeGoals) {
                    largeGoal.setDelFlg("1");
                    largeGoal.setUpdatedAt(LocalDateTime.now());
                    largeGoalRepository.save(largeGoal);

                    // MIDDLE_GOALSを検索して論理削除
                    List<MiddleGoalEntity> middleGoals = middleGoalRepository.findAll().stream()
                            .filter(goal -> largeGoal.getLargeGoalId().equals(goal.getLargeGoalId()) && "0".equals(goal.getDelFlg()))
                            .toList();

                    for (MiddleGoalEntity middleGoal : middleGoals) {
                        middleGoal.setDelFlg("1");
                        middleGoal.setUpdatedAt(LocalDateTime.now());
                        middleGoalRepository.save(middleGoal);

                        // SMALL_GOALSを検索して論理削除
                        List<SmallGoalEntity> smallGoals = smallGoalRepository.findAll().stream()
                                .filter(goal -> middleGoal.getMiddleGoalId().equals(goal.getMiddleGoalId()) && "0".equals(goal.getDelFlg()))
                                .toList();

                        for (SmallGoalEntity smallGoal : smallGoals) {
                            smallGoal.setDelFlg("1");
                            smallGoal.setUpdatedAt(LocalDateTime.now());
                            smallGoalRepository.save(smallGoal);
                        }
                    }
                }
            }
        }
    }

    /**
     * メールアドレスのドメインの最後に delete_YYYYMMDD を追加
     * 例: example@domain.com → example@domain.com.delete_20260124
     */
    private String addDeleteSuffixToEmail(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        
        // 現在の日付を YYYYMMDD 形式で取得
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateSuffix = now.format(formatter);
        
        // メールアドレスの最後に .delete_YYYYMMDD を追加
        return email + ".delete_" + dateSuffix;
    }
}
