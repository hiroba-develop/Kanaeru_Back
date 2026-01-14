package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DM_MESSAGES")
public class DmMessageEntity {
    
    @Id
    @Column(name = "MESSAGE_SEQ", nullable = false)
    private Long messageSeq;
    
    @Column(name = "SENDER_ID", length = 36, nullable = false)
    private String senderId;
    
    @Column(name = "RECIPIENT_ID", length = 36, nullable = false)
    private String recipientId;
    
    @Column(name = "CONTENT", length = 4000)
    private String content;
    
    @Column(name = "READ_AT")
    private LocalDateTime readAt;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
