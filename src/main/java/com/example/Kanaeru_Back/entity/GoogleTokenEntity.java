package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "GOOGLE_TOKENS")
public class GoogleTokenEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "GOOGLE_USER_ID", length = 64, nullable = false)
    private String googleUserId;
    
    @Column(name = "CALENDAR_ID", length = 64)
    private String calendarId;
    
    @Column(name = "REFRESH_TOKEN", length = 2000, nullable = false)
    private String refreshToken;
    
    @Column(name = "ACCESS_TOKEN", length = 2000)
    private String accessToken;
    
    @Column(name = "REFRESH_AT")
    private LocalDateTime refreshAt;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
