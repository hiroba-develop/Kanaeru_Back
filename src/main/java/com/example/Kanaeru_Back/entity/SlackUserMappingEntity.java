package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLACK_USER_MAPPINGS")
public class SlackUserMappingEntity {

    @Id
    @Column(name = "MAPPING_ID", length = 36, nullable = false)
    private String mappingId;

    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;

    @Column(name = "SLACK_USER_ID", length = 100, nullable = false)
    private String slackUserId;

    @Column(name = "WORKSPACE_ID", length = 36, nullable = false)
    private String workspaceId;

    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

}
