package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SETTINGS")
public class SettingEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "ADMIN_ID", length = 36, nullable = false)
    private String adminId;
    
    @Column(name = "COMPANY_SIZE")
    private Integer companySize;
    
    @Column(name = "INDUSTRY")
    private Integer industry;
    
    @Column(name = "CAPITAL")
    private Long capital;
    
    @Column(name = "FINANCIAL_KNOWLEDGE")
    private Integer financialKnowledge;
    
    @Column(name = "FISCAL_YEAR_START_YEAR")
    private Integer fiscalYearStartYear;
    
    @Column(name = "FISCAL_YEAR_START_MONTH")
    private Integer fiscalYearStartMonth;
    
    @Column(name = "GROSS_PROFIT_TARGET_RATE")
    private Integer grossProfitTargetRate;
    
    @Column(name = "OPERARING_PROFIT_TARGET_RATE")
    private Integer operaringProfitTargetRate;
    
    @Column(name = "LAST_ADMIN_COMMENT_DATE")
    private LocalDateTime lastAdminCommentDate;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
