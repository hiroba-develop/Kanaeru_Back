package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SMALL_GOALS")
public class SmallGoalEntity {
    
    @Id
    @Column(name = "SMALL_GOAL_ID", length = 36, nullable = false)
    private String smallGoalId;
    
    @Column(name = "MIDDLE_GOAL_ID", length = 36, nullable = false)
    private String middleGoalId;
    
    @Column(name = "POSITION", nullable = false)
    private Integer position;
    
    @Column(name = "GOAL_TITLE", length = 200, nullable = false)
    private String goalTitle;
    
    @Column(name = "GOAL_DESCRIPTION", length = 1000)
    private String goalDescription;
    
    @Column(name = "IS_COMPLETED", length = 1, nullable = false)
    private String isCompleted;
    
    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;
    
    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
