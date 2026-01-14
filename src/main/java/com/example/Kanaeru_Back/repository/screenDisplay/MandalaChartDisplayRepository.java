package com.example.Kanaeru_Back.repository.screenDisplay;

import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandalaChartDisplayRepository extends JpaRepository<MandalaChartEntity, String> {
    
    @Query(value = "SELECT mc.* FROM MANDALA_CHARTS mc " +
            "INNER JOIN MAIN_GOALS mg ON mc.CHART_ID = mg.CHART_ID " +
            "WHERE mc.USER_ID = :userId " +
            "AND mc.IS_ACTIVE = '1' " +
            "AND mc.DEL_FLG = '0' " +
            "AND mg.DEL_FLG = '0' " +
            "ORDER BY mc.CREATED_AT DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<MandalaChartEntity> findActiveMandalaChartByUserId(@Param("userId") String userId);
}
