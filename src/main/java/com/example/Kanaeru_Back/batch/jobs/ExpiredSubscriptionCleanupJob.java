package com.example.Kanaeru_Back.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.Kanaeru_Back.entity.SubscriptionEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.repository.SubscriptionRepository;
import com.example.Kanaeru_Back.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 期限切れサブスクリプションクリーンアップジョブの設定クラス
 * 毎日日本時間0時に実行され、キャンセル予定で期限が切れたサブスクリプションを処理する
 */
@Configuration("expiredSubscriptionCleanupJobConfig")
@Slf4j
public class ExpiredSubscriptionCleanupJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExpiredSubscriptionCleanupJob(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    /**
     * 期限切れサブスクリプションクリーンアップのTasklet
     * 
     * @return Tasklet
     */
    @Bean
    public Tasklet expiredSubscriptionCleanupTasklet() {
        return (contribution, chunkContext) -> {
            log.info("期限切れサブスクリプションクリーンアップ処理を開始します");

            LocalDateTime now = LocalDateTime.now();
            log.info("現在日時: {}", now);

            // CANCEL_AT_PERIOD_END = true かつ CURRENT_PERIOD_END < 現在日時 のサブスクリプションを取得
            List<SubscriptionEntity> expiredSubscriptions = subscriptionRepository.findExpiredCanceledSubscriptions(now);

            log.info("期限切れサブスクリプション件数: {}", expiredSubscriptions.size());

            int processedCount = 0;
            int errorCount = 0;

            for (SubscriptionEntity subscription : expiredSubscriptions) {
                try {
                    String userId = subscription.getUserId();
                    log.info("処理中: userId={}, subscriptionId={}, currentPeriodEnd={}",
                            userId, subscription.getStripeSubscriptionId(), subscription.getCurrentPeriodEnd());

                    // ユーザーのROLEを3（無料会員）に更新
                    Optional<UserEntity> userOpt = userRepository.findById(userId);
                    if (userOpt.isPresent()) {
                        UserEntity user = userOpt.get();
                        String previousRole = user.getRole();
                        user.setRole("3");
                        user.setUpdatedAt(LocalDateTime.now());
                        userRepository.save(user);
                        log.info("ユーザーロール更新完了: userId={}, previousRole={}, newRole=3", userId, previousRole);
                    } else {
                        log.warn("ユーザーが見つかりません: userId={}", userId);
                    }

                    // サブスクリプションレコードを削除
                    subscriptionRepository.delete(subscription);
                    log.info("サブスクリプション削除完了: userId={}, subscriptionId={}",
                            userId, subscription.getStripeSubscriptionId());

                    processedCount++;
                } catch (Exception e) {
                    errorCount++;
                    log.error("サブスクリプション処理中にエラーが発生しました: userId={}, subscriptionId={}",
                            subscription.getUserId(), subscription.getStripeSubscriptionId(), e);
                }
            }
            // ★ 追加：handleSubscriptionDeletedでcanceledになったもの
            List<SubscriptionEntity> canceledSubscriptions = subscriptionRepository.findCanceledExpiredSubscriptions(now);
            log.info("canceled期限切れサブスクリプション件数: {}", canceledSubscriptions.size());

            for (SubscriptionEntity subscription : canceledSubscriptions) {
                try {
                    String userId = subscription.getUserId();
                    log.info("canceled処理中: userId={}, currentPeriodEnd={}", userId, subscription.getCurrentPeriodEnd());

                    Optional<UserEntity> userOpt = userRepository.findById(userId);
                    if (userOpt.isPresent()) {
                        UserEntity user = userOpt.get();
                        String previousRole = user.getRole();
                        user.setRole("3");
                        user.setUpdatedAt(LocalDateTime.now());
                        userRepository.save(user);
                        log.info("ユーザーロール更新完了: userId={}, previousRole={}, newRole=3", userId, previousRole);
                    } else {
                        log.warn("ユーザーが見つかりません: userId={}", userId);
                    }

                    subscriptionRepository.delete(subscription);
                    log.info("サブスクリプション削除完了: userId={}", userId);

                    processedCount++;
                } catch (Exception e) {
                    errorCount++;
                    log.error("canceled処理中にエラー: userId={}", subscription.getUserId(), e);
                }
            }

            log.info("期限切れサブスクリプションクリーンアップ処理が完了しました。処理件数: {}, エラー件数: {}",
                    processedCount, errorCount);

            return RepeatStatus.FINISHED;
        };
    }

    /**
     * 期限切れサブスクリプションクリーンアップのStep
     * 
     * @return Step
     */
    @Bean
    public Step expiredSubscriptionCleanupStep() {
        return new StepBuilder("expiredSubscriptionCleanupStep", jobRepository)
                .tasklet(expiredSubscriptionCleanupTasklet(), transactionManager)
                .build();
    }

    /**
     * 期限切れサブスクリプションクリーンアップのJob
     * 
     * @return Job
     */
    @Bean
    public Job expiredSubscriptionCleanupJob() {
        return new JobBuilder("expiredSubscriptionCleanupJob", jobRepository)
                .start(expiredSubscriptionCleanupStep())
                .build();
    }
}
