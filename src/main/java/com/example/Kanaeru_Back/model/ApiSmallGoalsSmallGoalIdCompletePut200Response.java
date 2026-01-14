package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ApiSmallGoalsSmallGoalIdCompletePut200Response
 */

@JsonTypeName("_api_small_goals__small_goal_id__complete_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSmallGoalsSmallGoalIdCompletePut200Response {

  private Integer responseStatus;

  private Boolean isCompleted;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime completedAt;

  private String message;

  public ApiSmallGoalsSmallGoalIdCompletePut200Response responseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
    return this;
  }

  /**
   * Get responseStatus
   * @return responseStatus
  */
  
  @Schema(name = "responseStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("responseStatus")
  public Integer getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public ApiSmallGoalsSmallGoalIdCompletePut200Response isCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
    return this;
  }

  /**
   * Get isCompleted
   * @return isCompleted
  */
  
  @Schema(name = "is_completed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_completed")
  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public ApiSmallGoalsSmallGoalIdCompletePut200Response completedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
    return this;
  }

  /**
   * Get completedAt
   * @return completedAt
  */
  @Valid 
  @Schema(name = "completed_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("completed_at")
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public ApiSmallGoalsSmallGoalIdCompletePut200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSmallGoalsSmallGoalIdCompletePut200Response apiSmallGoalsSmallGoalIdCompletePut200Response = (ApiSmallGoalsSmallGoalIdCompletePut200Response) o;
    return Objects.equals(this.responseStatus, apiSmallGoalsSmallGoalIdCompletePut200Response.responseStatus) &&
        Objects.equals(this.isCompleted, apiSmallGoalsSmallGoalIdCompletePut200Response.isCompleted) &&
        Objects.equals(this.completedAt, apiSmallGoalsSmallGoalIdCompletePut200Response.completedAt) &&
        Objects.equals(this.message, apiSmallGoalsSmallGoalIdCompletePut200Response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, isCompleted, completedAt, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSmallGoalsSmallGoalIdCompletePut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

