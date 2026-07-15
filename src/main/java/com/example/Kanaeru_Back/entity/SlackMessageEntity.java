package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLACK_MESSAGES")
public class SlackMessageEntity {

    @Id
    @Column(name = "SLACK_MESSAGE_ID", length = 36, nullable = false)
    private String slackMessageId;

    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;

    @Column(name = "WORKSPACE_ID", length = 36, nullable = false)
    private String workspaceId;

    @Column(name = "SLACK_TS", length = 50, nullable = false)
    private String slackTs;

    @Column(name = "CHANNEL_ID", length = 100, nullable = false)
    private String channelId;

    @Column(name = "RAW_TEXT", length = 4000, nullable = false)
    private String rawText;

    @Column(name = "POSTED_AT", nullable = false)
    private LocalDateTime postedAt;

    @Column(name = "GOAL_COUNT", nullable = false)
    private Integer goalCount;

    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

}
