package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiddleGoalRepository extends JpaRepository<MiddleGoalEntity, String> {
    
    List<MiddleGoalEntity> findByLargeGoalIdAndDelFlgOrderByPositionAsc(String largeGoalId, String delFlg);

    /**
     * 大目標IDとGOAL_TYPEで中目標を取得（GOAL_TYPEが2~4のPL連動項目）
     * 
     * @param largeGoalId 大目標ID
     * @param delFlg 削除フラグ
     * @return 中目標エンティティのリスト
     */
    @Query("SELECT mg FROM MiddleGoalEntity mg WHERE mg.largeGoalId = :largeGoalId " +
           "AND mg.delFlg = :delFlg " +
           "AND mg.goalType IN (2, 3, 4) " +
           "ORDER BY mg.position")
    List<MiddleGoalEntity> findByLargeGoalIdAndDelFlgAndGoalTypeInOrderByPosition(
        @Param("largeGoalId") String largeGoalId,
        @Param("delFlg") String delFlg
    );
}
