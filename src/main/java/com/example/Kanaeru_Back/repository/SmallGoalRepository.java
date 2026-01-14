package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmallGoalRepository extends JpaRepository<SmallGoalEntity, String> {
    
    List<SmallGoalEntity> findByMiddleGoalIdAndDelFlgOrderByPositionAsc(String middleGoalId, String delFlg);
}
