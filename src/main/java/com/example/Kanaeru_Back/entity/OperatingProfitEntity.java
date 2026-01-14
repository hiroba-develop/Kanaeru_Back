package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "OPERATING_PROFITS")
@IdClass(OperatingProfitEntity.OperatingProfitId.class)
public class OperatingProfitEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Id
    @Column(name = "YEAR", nullable = false)
    private Integer year;
    
    @Id
    @Column(name = "MONTH", nullable = false)
    private Integer month;
    
    @Column(name = "OPERATING_PROFIT_TARGET")
    private Long operatingProfitTarget;
    
    @Column(name = "OPERATING_PROFIT_RESULT")
    private Long operatingProfitResult;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
    
    @Data
    public static class OperatingProfitId implements Serializable {
        private String userId;
        private Integer year;
        private Integer month;
    }
}
