package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyGoalRepository extends JpaRepository<DailyGoalEntity, String> {

    List<DailyGoalEntity> findByUserIdAndGoalDateBetweenAndDelFlgOrderBySortOrderAsc(
            String userId, LocalDate startDate, LocalDate endDate, String delFlg);

    @Query("SELECT MAX(d.sortOrder) FROM DailyGoalEntity d WHERE d.userId = :userId AND d.goalDate = :goalDate AND d.delFlg = '0'")
    Optional<Integer> findMaxSortOrderByUserIdAndGoalDate(@Param("userId") String userId, @Param("goalDate") LocalDate goalDate);
}
