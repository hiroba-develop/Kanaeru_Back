package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLACK_WEBHOOK_SETTINGS")
public class SlackWebhookSettingEntity {
    
    @Id
    @Column(name = "SLACK_ID", length = 36, nullable = false)
    private String slackId;
    
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "WEBHOOK_URL", length = 1000, nullable = false)
    private String webhookUrl;
    
    @Column(name = "IS_ENABLED", length = 1, nullable = false)
    private String isEnabled;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
