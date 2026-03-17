package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ADVICES")
public class AdviceEntity {

    @Id
    @Column(name = "ADVICE_ID", length = 36, nullable = false)
    private String adviceId;

    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;

    @Column(name = "ADMIN_ID", length = 36, nullable = false)
    private String adminId;

    @Column(name = "CONTENT", length = 4000, nullable = false)
    private String content;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;
}