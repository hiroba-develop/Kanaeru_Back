package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SubscriptionEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {

    Optional<SubscriptionEntity> findByUserId(String userId);

    Optional<SubscriptionEntity> findByStripeSubscriptionId(String stripeSubscriptionId);

    /**
     * Webhook の並列処理による重複メール送信を防ぐための悲観的ロック付きクエリ。
     * 同一サブスクリプションへの同時アクセスを直列化する。
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SubscriptionEntity s WHERE s.stripeSubscriptionId = :id")
    Optional<SubscriptionEntity> findByStripeSubscriptionIdForUpdate(@Param("id") String id);

    /**
     * CANCEL_AT_PERIOD_END = true かつ CURRENT_PERIOD_END が現在日時より過去のサブスクリプションを取得する
     */
    @Query("SELECT s FROM SubscriptionEntity s WHERE s.cancelAtPeriodEnd = true AND s.currentPeriodEnd < :now")
    List<SubscriptionEntity> findExpiredCanceledSubscriptions(@Param("now") LocalDateTime now);

    /**
     * status = canceled かつ CURRENT_PERIOD_END が現在日時より過去のサブスクリプションを取得する
     * （handleSubscriptionDeletedで canceled になったものが対象）
     */
    @Query("SELECT s FROM SubscriptionEntity s WHERE s.status = 'canceled' AND s.currentPeriodEnd < :now AND s.cancelAtPeriodEnd = false")
    List<SubscriptionEntity> findCanceledExpiredSubscriptions(@Param("now") LocalDateTime now);
}
