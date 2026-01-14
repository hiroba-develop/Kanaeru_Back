package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.MainGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainGoalRepository extends JpaRepository<MainGoalEntity, String> {
    
    Optional<MainGoalEntity> findByChartIdAndDelFlg(String chartId, String delFlg);
}
