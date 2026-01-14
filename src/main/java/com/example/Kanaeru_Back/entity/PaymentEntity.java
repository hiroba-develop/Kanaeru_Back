package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PAYMENTS")
public class PaymentEntity {
    
    @Id
    @Column(name = "PAYMENTS_ID", length = 36, nullable = false)
    private String paymentsId;
    
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "STRIPE_SUBSCRIPTION_ID", length = 255, nullable = false)
    private String stripeSubscriptionId;
    
    @Column(name = "CANCEL_REASON", length = 255)
    private String cancelReason;
    
    @Column(name = "CANCEL_AT")
    private LocalDateTime cancelAt;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
