package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LargeGoalRepository extends JpaRepository<LargeGoalEntity, String> {
    
    List<LargeGoalEntity> findByMainGoalIdAndDelFlgOrderByPositionAsc(String mainGoalId, String delFlg);

    /**
     * メイン目標IDとGOAL_TYPEで大目標を取得（GOAL_TYPEが2~4のPL連動項目）
     * 
     * @param mainGoalId メイン目標ID
     * @param delFlg 削除フラグ
     * @return 大目標エンティティのリスト
     */
    @Query("SELECT lg FROM LargeGoalEntity lg WHERE lg.mainGoalId = :mainGoalId " +
           "AND lg.delFlg = :delFlg " +
           "AND lg.goalType IN (2, 3, 4) " +
           "ORDER BY lg.position")
    List<LargeGoalEntity> findByMainGoalIdAndDelFlgAndGoalTypeInOrderByPosition(
        @Param("mainGoalId") String mainGoalId,
        @Param("delFlg") String delFlg
    );

    /**
     * メイン目標IDとpositionで大目標を取得
     * 
     * @param mainGoalId メイン目標ID
     * @param position position（1-8）
     * @param delFlg 削除フラグ
     * @return 大目標エンティティ
     */
    Optional<LargeGoalEntity> findByMainGoalIdAndPositionAndDelFlg(String mainGoalId, Integer position, String delFlg);
}
