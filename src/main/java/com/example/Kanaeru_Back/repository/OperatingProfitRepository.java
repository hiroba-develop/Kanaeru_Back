package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.OperatingProfitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatingProfitRepository extends JpaRepository<OperatingProfitEntity, OperatingProfitEntity.OperatingProfitId> {
    
    /**
     * ユーザーIDと年/月の範囲で営業利益データを取得
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param startMonth 開始月
     * @param endYear 終了年
     * @param endMonth 終了月
     * @return 営業利益エンティティのリスト
     */
    @Query("SELECT o FROM OperatingProfitEntity o WHERE o.userId = :userId " +
           "AND ((o.year > :startYear) OR (o.year = :startYear AND o.month >= :startMonth)) " +
           "AND ((o.year < :endYear) OR (o.year = :endYear AND o.month <= :endMonth)) " +
           "ORDER BY o.year, o.month")
    List<OperatingProfitEntity> findByUserIdAndYearMonthRange(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("startMonth") Integer startMonth,
        @Param("endYear") Integer endYear,
        @Param("endMonth") Integer endMonth
    );

    /**
     * ユーザーID、年の範囲、特定の月で営業利益データを取得（事業年度開始月のみ）
     * 
     * @param userId ユーザーID
     * @param startYear 開始年
     * @param endYear 終了年
     * @param month 月（事業年度開始月）
     * @return 営業利益エンティティのリスト
     */
    @Query("SELECT o FROM OperatingProfitEntity o WHERE o.userId = :userId " +
           "AND o.year >= :startYear AND o.year <= :endYear " +
           "AND o.month = :month " +
           "ORDER BY o.year")
    List<OperatingProfitEntity> findByUserIdAndYearRangeAndMonth(
        @Param("userId") String userId,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("month") Integer month
    );

    /**
     * ユーザーIDと年で営業利益実績の合計を取得
     * 
     * @param userId ユーザーID
     * @param year 年
     * @return 営業利益実績の合計
     */
    @Query("SELECT COALESCE(SUM(o.operatingProfitResult), 0) FROM OperatingProfitEntity o WHERE o.userId = :userId AND o.year = :year")
    Long sumOperatingProfitResultByUserIdAndYear(@Param("userId") String userId, @Param("year") Integer year);

    /**
     * ユーザーID、年、月で営業利益データを取得
     * 
     * @param userId ユーザーID
     * @param year 年
     * @param month 月
     * @return 営業利益エンティティ（Optional）
     */
    Optional<OperatingProfitEntity> findByUserIdAndYearAndMonth(String userId, Integer year, Integer month);
}
