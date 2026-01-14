package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LARGE_GOALS")
public class LargeGoalEntity {
    
    @Id
    @Column(name = "LARGE_GOAL_ID", length = 36, nullable = false)
    private String largeGoalId;
    
    @Column(name = "MAIN_GOAL_ID", length = 36, nullable = false)
    private String mainGoalId;
    
    @Column(name = "POSITION", nullable = false)
    private Integer position;
    
    @Column(name = "GOAL_TITLE", length = 200, nullable = false)
    private String goalTitle;
    
    @Column(name = "GOAL_DESCRIPTION", length = 1000)
    private String goalDescription;
    
    @Column(name = "GOAL_TYPE", nullable = false)
    private Integer goalType;
    
    @Column(name = "TARGET_YEAR")
    private Integer targetYear;
    
    @Column(name = "TARGET_AMOUNT", precision = 15, scale = 2)
    private BigDecimal targetAmount;
    
    @Column(name = "PROGRESS", nullable = false)
    private Integer progress;
    
    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
