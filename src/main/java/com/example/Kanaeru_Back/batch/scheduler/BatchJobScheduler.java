package com.example.Kanaeru_Back.batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * バッチジョブのスケジューラクラス
 */
@Component
@RestController
@Slf4j
public class BatchJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job expiredSubscriptionCleanupJob;

    @Autowired
    public BatchJobScheduler(
            JobLauncher jobLauncher,
            @Qualifier("expiredSubscriptionCleanupJob") Job expiredSubscriptionCleanupJob) {
        this.jobLauncher = jobLauncher;
        this.expiredSubscriptionCleanupJob = expiredSubscriptionCleanupJob;
        log.info("BatchJobScheduler が初期化されました");
        log.info("jobLauncher: {}", jobLauncher);
        log.info("expiredSubscriptionCleanupJob: {}", expiredSubscriptionCleanupJob);
    }

    @PostConstruct
    public void init() {
        log.info("BatchJobScheduler PostConstruct実行 - スケジュールジョブが設定されています");
        log.info("現在時刻: {}", java.time.LocalDateTime.now());
    }

    /**
     * 毎日午前0時0分に実行
     * 期限切れサブスクリプションのクリーンアップジョブを実行
     */
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Tokyo")
    public void runExpiredSubscriptionCleanupJob() {
        log.info("=== 期限切れサブスクリプションクリーンアップジョブ @Scheduled メソッドが呼び出されました ===");
        log.info("現在時刻: {}", java.time.LocalDateTime.now());
        log.info("期限切れサブスクリプションクリーンアップジョブを開始します");
        
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(expiredSubscriptionCleanupJob, params);
            log.info("期限切れサブスクリプションクリーンアップジョブが正常に完了しました");
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            log.error("期限切れサブスクリプションクリーンアップジョブの実行中にエラーが発生しました", e);
        }
    }

    /**
     * 手動で期限切れサブスクリプションクリーンアップジョブを実行するテスト用エンドポイント
     */
    @GetMapping("/test/batch/expired-subscription-cleanup")
    public String testExpiredSubscriptionCleanupJob() {
        log.info("=== 手動で期限切れサブスクリプションクリーンアップジョブを実行します ===");
        try {
            runExpiredSubscriptionCleanupJob();
            return "期限切れサブスクリプションクリーンアップジョブの実行が完了しました";
        } catch (Exception e) {
            log.error("手動バッチジョブ実行でエラーが発生しました", e);
            return "期限切れサブスクリプションクリーンアップジョブの実行でエラーが発生しました: " + e.getMessage();
        }
    }
}
