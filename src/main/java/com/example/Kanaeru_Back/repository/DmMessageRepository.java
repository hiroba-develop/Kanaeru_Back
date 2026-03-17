package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.DmMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DmMessageRepository extends JpaRepository<DmMessageEntity, Long> {

        int countByRecipientIdAndSenderIdAndReadAtIsNull(String recipientId, String senderId);

    /**
     * 指定ユーザーに関連する全メッセージを取得（送信・受信どちらも含む）
     */
    @Query(value = """
            SELECT * FROM DM_MESSAGES
            WHERE SENDER_ID = :userId OR RECIPIENT_ID = :userId
            ORDER BY MESSAGE_SEQ ASC
            """, nativeQuery = true)
    List<DmMessageEntity> findMessagesByUserId(@Param("userId") String userId);

    /**
     * 指定ユーザーの最新メッセージシーケンス番号を取得
     */
    @Query(value = """
            SELECT MAX(MESSAGE_SEQ) FROM DM_MESSAGES
            WHERE SENDER_ID = :userId OR RECIPIENT_ID = :userId
            """, nativeQuery = true)
    Long findLatestMessageSeqByUserId(@Param("userId") String userId);

    /**
     * 2ユーザー間のメッセージを取得
     */
    @Query(value = """
            SELECT * FROM DM_MESSAGES
            WHERE (SENDER_ID = :userId1 AND RECIPIENT_ID = :userId2)
               OR (SENDER_ID = :userId2 AND RECIPIENT_ID = :userId1)
            ORDER BY MESSAGE_SEQ ASC
            """, nativeQuery = true)
    List<DmMessageEntity> findMessagesBetweenUsers(
            @Param("userId1") String userId1,
            @Param("userId2") String userId2);

    /**
     * 2ユーザー間の最新メッセージシーケンス番号を取得
     */
    @Query(value = """
            SELECT MAX(MESSAGE_SEQ) FROM DM_MESSAGES
            WHERE (SENDER_ID = :userId1 AND RECIPIENT_ID = :userId2)
               OR (SENDER_ID = :userId2 AND RECIPIENT_ID = :userId1)
            """, nativeQuery = true)
    Long findLatestMessageSeqBetweenUsers(
            @Param("userId1") String userId1,
            @Param("userId2") String userId2);

    /**
     * 指定メッセージシーケンス以前の未読メッセージを既読に更新する
     *
     * @param recipientId 既読を付けるユーザーID（受信者）
     * @param senderId    メッセージ送信者ID
     * @param messageSeq  このシーケンス番号以前のメッセージを対象にする
     * @param readAt      既読日時
     * @return 更新件数
     */
    @Modifying
    @Query(value = """
            UPDATE DM_MESSAGES
            SET READ_AT = :readAt, UPDATED_AT = :readAt
            WHERE SENDER_ID = :senderId
            AND (RECIPIENT_ID = :recipientId OR RECIPIENT_ID IS NULL)
            AND MESSAGE_SEQ <= :messageSeq
            AND READ_AT IS NULL
            """, nativeQuery = true)
    int markMessagesAsReadBeforeSeq(
            @Param("recipientId") String recipientId,
            @Param("senderId") String senderId,
            @Param("messageSeq") Long messageSeq,
            @Param("readAt") LocalDateTime readAt);

    /**
     * 既読になったメッセージの詳細を取得する
     *
     * @param userId1    ユーザーID1
     * @param userId2    ユーザーID2
     * @param messageSeq 基準シーケンス番号
     * @return 既読メッセージのリスト
     */
    @Query(value = """
        SELECT * FROM DM_MESSAGES
        WHERE SENDER_ID = :userId1
          AND (RECIPIENT_ID = :userId2 OR RECIPIENT_ID IS NULL)
          AND MESSAGE_SEQ <= :messageSeq
          AND READ_AT IS NOT NULL
        ORDER BY MESSAGE_SEQ ASC
        """, nativeQuery = true)
    List<DmMessageEntity> findReadMessagesBySeq(
            @Param("userId1") String userId1,
            @Param("userId2") String userId2,
            @Param("messageSeq") Long messageSeq);

    /**
     * 指定ユーザー宛の未読メッセージが存在するか確認する（一般ユーザー用）
     *
     * @param recipientId 受信者のユーザーID
     * @return 未読メッセージ件数
     */
    @Query(value = """
            SELECT COUNT(*) FROM DM_MESSAGES
            WHERE RECIPIENT_ID = :recipientId
              AND READ_AT IS NULL
            """, nativeQuery = true)
    long countUnreadByRecipientId(@Param("recipientId") String recipientId);

    // 修正後：一般ユーザー（role:3,4）からの未読メッセージの送信者IDを取得
    @Query(value = """
        SELECT DISTINCT m.SENDER_ID FROM DM_MESSAGES m
        WHERE m.RECIPIENT_ID IS NULL
          AND m.READ_AT IS NULL
        """, nativeQuery = true)
    List<String> findUnreadSenderIdsByRecipientId(@Param("recipientId") String recipientId);
}
