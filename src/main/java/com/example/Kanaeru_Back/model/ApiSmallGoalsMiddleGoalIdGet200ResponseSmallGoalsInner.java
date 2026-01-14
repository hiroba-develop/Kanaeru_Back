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
 * ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner
 */

@JsonTypeName("_api_small_goals__middle_goal_id__get_200_response_small_goals_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner {

  private String smallGoalId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Boolean isCompleted;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime completedAt;

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner smallGoalId(String smallGoalId) {
    this.smallGoalId = smallGoalId;
    return this;
  }

  /**
   * Get smallGoalId
   * @return smallGoalId
  */
  
  @Schema(name = "small_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("small_goal_id")
  public String getSmallGoalId() {
    return smallGoalId;
  }

  public void setSmallGoalId(String smallGoalId) {
    this.smallGoalId = smallGoalId;
  }

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
  */
  
  @Schema(name = "position", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner goalTitle(String goalTitle) {
    this.goalTitle = goalTitle;
    return this;
  }

  /**
   * Get goalTitle
   * @return goalTitle
  */
  
  @Schema(name = "goal_title", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_title")
  public String getGoalTitle() {
    return goalTitle;
  }

  public void setGoalTitle(String goalTitle) {
    this.goalTitle = goalTitle;
  }

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner goalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
    return this;
  }

  /**
   * Get goalDescription
   * @return goalDescription
  */
  
  @Schema(name = "goal_description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_description")
  public String getGoalDescription() {
    return goalDescription;
  }

  public void setGoalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
  }

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner isCompleted(Boolean isCompleted) {
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

  public ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner completedAt(LocalDateTime completedAt) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner = (ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner) o;
    return Objects.equals(this.smallGoalId, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.smallGoalId) &&
        Objects.equals(this.position, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.position) &&
        Objects.equals(this.goalTitle, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.goalTitle) &&
        Objects.equals(this.goalDescription, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.goalDescription) &&
        Objects.equals(this.isCompleted, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.isCompleted) &&
        Objects.equals(this.completedAt, apiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner.completedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(smallGoalId, position, goalTitle, goalDescription, isCompleted, completedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner {\n");
    sb.append("    smallGoalId: ").append(toIndentedString(smallGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
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

