package com.example.Kanaeru_Back.repository.screenDisplay;

import com.example.Kanaeru_Back.entity.MainGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainGoalDisplayRepository extends JpaRepository<MainGoalEntity, String> {
    
    @Query(value = "SELECT mg.* FROM MAIN_GOALS mg " +
            "INNER JOIN MANDALA_CHARTS mc ON mg.CHART_ID = mc.CHART_ID " +
            "WHERE mc.CHART_ID = :chartId " +
            "AND mg.DEL_FLG = '0' " +
            "AND mc.DEL_FLG = '0' " +
            "LIMIT 1", nativeQuery = true)
    Optional<MainGoalEntity> findMainGoalByChartId(@Param("chartId") String chartId);
}
