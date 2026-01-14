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
@Table(name = "SALES")
@IdClass(SalesEntity.SalesId.class)
public class SalesEntity {
    
    @Id
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Id
    @Column(name = "YEAR", nullable = false)
    private Integer year;
    
    @Id
    @Column(name = "MONTH", nullable = false)
    private Integer month;
    
    @Column(name = "SALE_TARGET")
    private Long saleTarget;
    
    @Column(name = "SALE_RESULT")
    private Long saleResult;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
    
    @Data
    public static class SalesId implements Serializable {
        private String userId;
        private Integer year;
        private Integer month;
    }
}
