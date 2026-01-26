package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.GrossProfitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrossProfitRepository extends JpaRepository<GrossProfitEntity, GrossProfitEntity.GrossProfitId> {
    
    /**
     * ユーザーIDと年/月の範囲で粗利益データを取得
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param startMonth 開始月
     * @param endYear 終了年
     * @param endMonth 終了月
     * @return 粗利益エンティティのリスト
     */
    @Query("SELECT g FROM GrossProfitEntity g WHERE g.userId = :userId " +
           "AND ((g.year > :startYear) OR (g.year = :startYear AND g.month >= :startMonth)) " +
           "AND ((g.year < :endYear) OR (g.year = :endYear AND g.month <= :endMonth)) " +
           "ORDER BY g.year, g.month")
    List<GrossProfitEntity> findByUserIdAndYearMonthRange(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("startMonth") Integer startMonth,
        @Param("endYear") Integer endYear,
        @Param("endMonth") Integer endMonth
    );

    /**
     * ユーザーID、年の範囲、特定の月で粗利益データを取得（事業年度開始月のみ）
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param endYear 終了年
     * @param month 月（事業年度開始月）
     * @return 粗利益エンティティのリスト
     */
    @Query("SELECT g FROM GrossProfitEntity g WHERE g.userId = :userId " +
           "AND g.year >= :startYear AND g.year <= :endYear " +
           "AND g.month = :month " +
           "ORDER BY g.year")
    List<GrossProfitEntity> findByUserIdAndYearRangeAndMonth(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("month") Integer month
    );

    /**
     * ユーザーIDと年で粗利益実績の合計を取得
     * 
     * @param userId ユーザーID
     * @param year 年
     * @return 粗利益実績の合計
     */
    @Query("SELECT COALESCE(SUM(g.grossProfitResult), 0) FROM GrossProfitEntity g WHERE g.userId = :userId AND g.year = :year")
    Long sumGrossProfitResultByUserIdAndYear(@Param("userId") String userId, @Param("year") Integer year);
}
