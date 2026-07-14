package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DAILY_GOALS")
public class DailyGoalEntity {

    @Id
    @Column(name = "DAILY_GOAL_ID", length = 36, nullable = false)
    private String dailyGoalId;

    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;

    @Column(name = "GOAL_DATE", nullable = false)
    private LocalDate goalDate;

    @Column(name = "TITLE", length = 500, nullable = false)
    private String title;

    @Column(name = "IS_COMPLETED", length = 1, nullable = false)
    private String isCompleted;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

    @Column(name = "SOURCE", length = 1, nullable = false)
    private String source;

    @Column(name = "MEMO", length = 2000)
    private String memo;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "CATEGORY_GOAL_ID", length = 36)
    private String categoryGoalId;

    @Column(name = "PLANNED_MIN")
    private Integer plannedMin;

    @Column(name = "ACTUAL_MIN")
    private Integer actualMin;

    @Column(name = "SORT_ORDER", nullable = false)
    private Integer sortOrder;

    @Column(name = "CARRIED_FROM")
    private LocalDate carriedFrom;

    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

}
