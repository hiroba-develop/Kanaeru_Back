package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LargeGoalRepository extends JpaRepository<LargeGoalEntity, String> {
    
    List<LargeGoalEntity> findByMainGoalIdAndDelFlgOrderByPositionAsc(String mainGoalId, String delFlg);
}
