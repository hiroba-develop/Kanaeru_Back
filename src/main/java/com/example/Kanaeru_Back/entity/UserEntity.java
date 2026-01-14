package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "EMAIL", length = 255, nullable = false)
    private String email;
    
    @Column(name = "PASSWORD_HASH", length = 255)
    private String passwordHash;
    
    @Column(name = "NAME", length = 255)
    private String name;
    
    @Column(name = "COMPANY", length = 255)
    private String company;
    
    @Column(name = "ROLE", length = 1, nullable = false)
    private String role;
    
    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
    
    @Column(name = "BUSINESS_START_HOUR")
    private Integer businessStartHour;
    
    @Column(name = "BUSINESS_END_HOUR")
    private Integer businessEndHour;
    
    @Column(name = "RESET_TOKEN", length = 255)
    private String resetToken;
    
    @Column(name = "RESET_TOKEN_EXPIRY")
    private LocalDateTime resetTokenExpiry;
    
    @Column(name = "STRIPE_CUSTOMER_ID", length = 255, nullable = false)
    private String stripeCustomerId;
}
