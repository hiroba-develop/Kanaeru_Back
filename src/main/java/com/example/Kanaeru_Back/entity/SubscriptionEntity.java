package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SUBSCRIPTIONS")
public class SubscriptionEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "STRIPE_SUBSCRIPTION_ID", length = 255, nullable = false)
    private String stripeSubscriptionId;
    
    @Column(name = "STATUS", length = 50, nullable = false)
    private String status;
    
    @Column(name = "AMOUNT", length = 10)
    private Integer amount;

    @Column(name = "CURRENT_PERIOD_START")
    private LocalDateTime currentPeriodStart;
    
    @Column(name = "CURRENT_PERIOD_END")
    private LocalDateTime currentPeriodEnd;
    
    @Column(name = "CANCEL_AT_PERIOD_END")
    private Boolean cancelAtPeriodEnd;

    @Column(name = "CANCELED_AT", nullable = false)
    private LocalDateTime canceledAt;

    @Column(name = "STRIPE_CUSTOMER_ID", length = 255, nullable = false)
    private String stripeCustomerId;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
