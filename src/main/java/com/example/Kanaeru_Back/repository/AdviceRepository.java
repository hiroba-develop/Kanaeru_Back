package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.AdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdviceRepository extends JpaRepository<AdviceEntity, String> {

    /**
     * 指定ユーザーの有効なアドバイスを作成日時の降順で取得
     */
    List<AdviceEntity> findByUserIdAndDelFlgOrderByCreatedAtDesc(String userId, String delFlg);

    /**
     * adviceId（UUID文字列）で有効なアドバイスを取得
     */
    Optional<AdviceEntity> findByAdviceIdAndDelFlg(String adviceId, String delFlg);

    /**
     * 指定ユーザーの指定年月のアドバイスを取得
     */
    @Query(value = """
            SELECT * FROM ADVICES
            WHERE USER_ID = :userId
              AND DEL_FLG = '0'
              AND EXTRACT(YEAR  FROM CREATED_AT) = :year
              AND EXTRACT(MONTH FROM CREATED_AT) = :month
            ORDER BY CREATED_AT DESC
            """, nativeQuery = true)
    List<AdviceEntity> findByUserIdAndYearMonth(
            @Param("userId") String userId,
            @Param("year") Integer year,
            @Param("month") Integer month);


    /**
     * 指定ユーザーのアドバイスを取得
     */
    @Query(value = """
                SELECT * FROM ADVICES
                WHERE USER_ID = :userId
                        AND DEL_FLG = '0'
                ORDER BY CREATED_AT DESC
                """, nativeQuery = true)
    List<AdviceEntity> findByUserId(@Param("userId") String userId);

    /**
     * 指定ユーザーの最新データがある月のアドバイスを取得
     * データが存在しない場合は空リストを返す
     */
    @Query(value = """
            SELECT * FROM ADVICES
            WHERE USER_ID = :userId
              AND DEL_FLG = '0'
              AND TRUNC(CREATED_AT, 'MM') = (
                SELECT TRUNC(MAX(CREATED_AT), 'MM')
                FROM ADVICES
                WHERE USER_ID = :userId
                  AND DEL_FLG = '0'
              )
            ORDER BY CREATED_AT DESC
            """, nativeQuery = true)
    List<AdviceEntity> findLatestMonthAdviceByUserId(@Param("userId") String userId);

    /**
     * アドバイス内容を更新する
     */
    @Modifying
    @Query(value = """
            UPDATE ADVICES
            SET CONTENT = :content, UPDATED_AT = :updatedAt
            WHERE ADVICE_ID = :adviceId
              AND DEL_FLG = '0'
            """, nativeQuery = true)
    int updateContent(
            @Param("adviceId") String adviceId,
            @Param("content") String content,
            @Param("updatedAt") LocalDateTime updatedAt);

    /**
     * アドバイスを論理削除する
     */
    @Modifying
    @Query(value = """
            UPDATE ADVICES
            SET DEL_FLG = '1', UPDATED_AT = :updatedAt
            WHERE ADVICE_ID = :adviceId
              AND DEL_FLG = '0'
            """, nativeQuery = true)
    int logicalDelete(
            @Param("adviceId") String adviceId,
            @Param("updatedAt") LocalDateTime updatedAt);
}
