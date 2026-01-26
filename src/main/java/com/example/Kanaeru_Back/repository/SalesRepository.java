package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, SalesEntity.SalesId> {
    
    /**
     * ユーザーIDと年/月の範囲で売上データを取得
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param startMonth 開始月
     * @param endYear 終了年
     * @param endMonth 終了月
     * @return 売上エンティティのリスト
     */
    @Query("SELECT s FROM SalesEntity s WHERE s.userId = :userId " +
           "AND ((s.year > :startYear) OR (s.year = :startYear AND s.month >= :startMonth)) " +
           "AND ((s.year < :endYear) OR (s.year = :endYear AND s.month <= :endMonth)) " +
           "ORDER BY s.year, s.month")
    List<SalesEntity> findByUserIdAndYearMonthRange(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("startMonth") Integer startMonth,
        @Param("endYear") Integer endYear,
        @Param("endMonth") Integer endMonth
    );

    /**
     * ユーザーID、年の範囲、特定の月で売上データを取得（事業年度開始月のみ）
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param endYear 終了年
     * @param month 月（事業年度開始月）
     * @return 売上エンティティのリスト
     */
    @Query("SELECT s FROM SalesEntity s WHERE s.userId = :userId " +
           "AND s.year >= :startYear AND s.year <= :endYear " +
           "AND s.month = :month " +
           "ORDER BY s.year")
    List<SalesEntity> findByUserIdAndYearRangeAndMonth(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("month") Integer month
    );

    /**
     * ユーザーIDと年で売上実績の合計を取得
     * 
     * @param userId ユーザーID
     * @param year 年
     * @return 売上実績の合計
     */
    @Query("SELECT COALESCE(SUM(s.saleResult), 0) FROM SalesEntity s WHERE s.userId = :userId AND s.year = :year")
    Long sumSaleResultByUserIdAndYear(@Param("userId") String userId, @Param("year") Integer year);
}
