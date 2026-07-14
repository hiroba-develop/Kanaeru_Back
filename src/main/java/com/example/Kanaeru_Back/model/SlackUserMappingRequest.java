package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SlackUserMappingRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SlackUserMappingRequest {

  private String userId;

  private String slackUserId;

  public SlackUserMappingRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SlackUserMappingRequest(String userId, String slackUserId) {
    this.userId = userId;
    this.slackUserId = slackUserId;
  }

  public SlackUserMappingRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Kanaeru のユーザーID
   * @return userId
  */
  @NotNull 
  @Schema(name = "userId", description = "Kanaeru のユーザーID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public SlackUserMappingRequest slackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
    return this;
  }

  /**
   * Slack の Member ID（例: U012AB3CD）
   * @return slackUserId
  */
  @NotNull 
  @Schema(name = "slackUserId", description = "Slack の Member ID（例: U012AB3CD）", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("slackUserId")
  public String getSlackUserId() {
    return slackUserId;
  }

  public void setSlackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SlackUserMappingRequest slackUserMappingRequest = (SlackUserMappingRequest) o;
    return Objects.equals(this.userId, slackUserMappingRequest.userId) &&
        Objects.equals(this.slackUserId, slackUserMappingRequest.slackUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, slackUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SlackUserMappingRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    slackUserId: ").append(toIndentedString(slackUserId)).append("\n");
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

