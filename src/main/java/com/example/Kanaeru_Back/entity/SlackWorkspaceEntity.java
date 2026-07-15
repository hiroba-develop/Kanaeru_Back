package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLACK_WORKSPACES")
public class SlackWorkspaceEntity {

    @Id
    @Column(name = "WORKSPACE_ID", length = 36, nullable = false)
    private String workspaceId;

    @Column(name = "TEAM_ID", length = 50, nullable = false, unique = true)
    private String teamId;

    @Column(name = "TEAM_NAME", length = 200)
    private String teamName;

    @Column(name = "BOT_USER_ID", length = 50)
    private String botUserId;

    @Column(name = "BOT_TOKEN", length = 500, nullable = false)
    private String botToken;

    @Column(name = "SCOPE", length = 1000)
    private String scope;

    @Column(name = "INSTALLED_BY_USER_ID", length = 36)
    private String installedByUserId;

    @Column(name = "DEL_FLG", length = 1, nullable = false)
    private String delFlg;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

}
