package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandalaChartRepository extends JpaRepository<MandalaChartEntity, String> {
    
    Optional<MandalaChartEntity> findFirstByUserIdAndIsActiveAndDelFlgOrderByCreatedAtDesc(String userId, String isActive, String delFlg);
}
