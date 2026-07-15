package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SlackOauthStatusResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SlackOauthStatusResponse {

  private Boolean connected;

  private String teamName;

  private String slackUserId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime connectedAt;

  public SlackOauthStatusResponse connected(Boolean connected) {
    this.connected = connected;
    return this;
  }

  /**
   * Slackワークスペースと連携済みかどうか
   * @return connected
  */
  
  @Schema(name = "connected", description = "Slackワークスペースと連携済みかどうか", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("connected")
  public Boolean getConnected() {
    return connected;
  }

  public void setConnected(Boolean connected) {
    this.connected = connected;
  }

  public SlackOauthStatusResponse teamName(String teamName) {
    this.teamName = teamName;
    return this;
  }

  /**
   * 連携先ワークスペース表示名
   * @return teamName
  */
  
  @Schema(name = "teamName", description = "連携先ワークスペース表示名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("teamName")
  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public SlackOauthStatusResponse slackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
    return this;
  }

  /**
   * Slack の Member ID（例: U012AB3CD）
   * @return slackUserId
  */
  
  @Schema(name = "slackUserId", description = "Slack の Member ID（例: U012AB3CD）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("slackUserId")
  public String getSlackUserId() {
    return slackUserId;
  }

  public void setSlackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
  }

  public SlackOauthStatusResponse connectedAt(LocalDateTime connectedAt) {
    this.connectedAt = connectedAt;
    return this;
  }

  /**
   * Get connectedAt
   * @return connectedAt
  */
  @Valid 
  @Schema(name = "connectedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("connectedAt")
  public LocalDateTime getConnectedAt() {
    return connectedAt;
  }

  public void setConnectedAt(LocalDateTime connectedAt) {
    this.connectedAt = connectedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SlackOauthStatusResponse slackOauthStatusResponse = (SlackOauthStatusResponse) o;
    return Objects.equals(this.connected, slackOauthStatusResponse.connected) &&
        Objects.equals(this.teamName, slackOauthStatusResponse.teamName) &&
        Objects.equals(this.slackUserId, slackOauthStatusResponse.slackUserId) &&
        Objects.equals(this.connectedAt, slackOauthStatusResponse.connectedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connected, teamName, slackUserId, connectedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SlackOauthStatusResponse {\n");
    sb.append("    connected: ").append(toIndentedString(connected)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    slackUserId: ").append(toIndentedString(slackUserId)).append("\n");
    sb.append("    connectedAt: ").append(toIndentedString(connectedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

