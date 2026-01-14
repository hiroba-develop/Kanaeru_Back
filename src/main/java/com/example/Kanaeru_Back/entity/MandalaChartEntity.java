package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MANDALA_CHARTS")
public class MandalaChartEntity {
    
    @Id
    @Column(name = "CHART_ID", length = 36, nullable = false)
    private String chartId;
    
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "START_YEAR_MONTH", nullable = false)
    private Integer startYearMonth;
    
    @Column(name = "END_YEAR_MONTH")
    private Integer endYearMonth;
    
    @Column(name = "IS_ACTIVE", length = 1, nullable = false)
    private String isActive;
    
    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
