package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiddleGoalRepository extends JpaRepository<MiddleGoalEntity, String> {
    
    List<MiddleGoalEntity> findByLargeGoalIdAndDelFlgOrderByPositionAsc(String largeGoalId, String delFlg);
}
