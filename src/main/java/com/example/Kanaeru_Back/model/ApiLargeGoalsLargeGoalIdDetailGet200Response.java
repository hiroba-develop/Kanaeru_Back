package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiLargeGoalsLargeGoalIdDetailGet200Response
 */

@JsonTypeName("_api_large_goals__large_goal_id__detail_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiLargeGoalsLargeGoalIdDetailGet200Response {

  private Integer responseStatus;

  private String largeGoalId;

  private String mainGoalId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  private BigDecimal progress;

  private Integer middleGoalsCount;

  public ApiLargeGoalsLargeGoalIdDetailGet200Response responseStatus(Integer responseStatus) {
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

  public ApiLargeGoalsLargeGoalIdDetailGet200Response largeGoalId(String largeGoalId) {
    this.largeGoalId = largeGoalId;
    return this;
  }

  /**
   * Get largeGoalId
   * @return largeGoalId
  */
  
  @Schema(name = "large_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("large_goal_id")
  public String getLargeGoalId() {
    return largeGoalId;
  }

  public void setLargeGoalId(String largeGoalId) {
    this.largeGoalId = largeGoalId;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response mainGoalId(String mainGoalId) {
    this.mainGoalId = mainGoalId;
    return this;
  }

  /**
   * Get mainGoalId
   * @return mainGoalId
  */
  
  @Schema(name = "main_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("main_goal_id")
  public String getMainGoalId() {
    return mainGoalId;
  }

  public void setMainGoalId(String mainGoalId) {
    this.mainGoalId = mainGoalId;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response position(Integer position) {
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

  public ApiLargeGoalsLargeGoalIdDetailGet200Response goalTitle(String goalTitle) {
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

  public ApiLargeGoalsLargeGoalIdDetailGet200Response goalDescription(String goalDescription) {
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

  public ApiLargeGoalsLargeGoalIdDetailGet200Response goalType(Integer goalType) {
    this.goalType = goalType;
    return this;
  }

  /**
   * 1=定性, 2=売上, 3=粗利益, 4=営業利益
   * @return goalType
  */
  
  @Schema(name = "goal_type", description = "1=定性, 2=売上, 3=粗利益, 4=営業利益", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_type")
  public Integer getGoalType() {
    return goalType;
  }

  public void setGoalType(Integer goalType) {
    this.goalType = goalType;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response targetYear(Integer targetYear) {
    this.targetYear = targetYear;
    return this;
  }

  /**
   * Get targetYear
   * @return targetYear
  */
  
  @Schema(name = "target_year", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("target_year")
  public Integer getTargetYear() {
    return targetYear;
  }

  public void setTargetYear(Integer targetYear) {
    this.targetYear = targetYear;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response targetAmount(BigDecimal targetAmount) {
    this.targetAmount = targetAmount;
    return this;
  }

  /**
   * Get targetAmount
   * @return targetAmount
  */
  @Valid 
  @Schema(name = "target_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("target_amount")
  public BigDecimal getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(BigDecimal targetAmount) {
    this.targetAmount = targetAmount;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response progress(BigDecimal progress) {
    this.progress = progress;
    return this;
  }

  /**
   * Get progress
   * @return progress
  */
  @Valid 
  @Schema(name = "progress", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("progress")
  public BigDecimal getProgress() {
    return progress;
  }

  public void setProgress(BigDecimal progress) {
    this.progress = progress;
  }

  public ApiLargeGoalsLargeGoalIdDetailGet200Response middleGoalsCount(Integer middleGoalsCount) {
    this.middleGoalsCount = middleGoalsCount;
    return this;
  }

  /**
   * Get middleGoalsCount
   * @return middleGoalsCount
  */
  
  @Schema(name = "middle_goals_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middle_goals_count")
  public Integer getMiddleGoalsCount() {
    return middleGoalsCount;
  }

  public void setMiddleGoalsCount(Integer middleGoalsCount) {
    this.middleGoalsCount = middleGoalsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiLargeGoalsLargeGoalIdDetailGet200Response apiLargeGoalsLargeGoalIdDetailGet200Response = (ApiLargeGoalsLargeGoalIdDetailGet200Response) o;
    return Objects.equals(this.responseStatus, apiLargeGoalsLargeGoalIdDetailGet200Response.responseStatus) &&
        Objects.equals(this.largeGoalId, apiLargeGoalsLargeGoalIdDetailGet200Response.largeGoalId) &&
        Objects.equals(this.mainGoalId, apiLargeGoalsLargeGoalIdDetailGet200Response.mainGoalId) &&
        Objects.equals(this.position, apiLargeGoalsLargeGoalIdDetailGet200Response.position) &&
        Objects.equals(this.goalTitle, apiLargeGoalsLargeGoalIdDetailGet200Response.goalTitle) &&
        Objects.equals(this.goalDescription, apiLargeGoalsLargeGoalIdDetailGet200Response.goalDescription) &&
        Objects.equals(this.goalType, apiLargeGoalsLargeGoalIdDetailGet200Response.goalType) &&
        Objects.equals(this.targetYear, apiLargeGoalsLargeGoalIdDetailGet200Response.targetYear) &&
        Objects.equals(this.targetAmount, apiLargeGoalsLargeGoalIdDetailGet200Response.targetAmount) &&
        Objects.equals(this.progress, apiLargeGoalsLargeGoalIdDetailGet200Response.progress) &&
        Objects.equals(this.middleGoalsCount, apiLargeGoalsLargeGoalIdDetailGet200Response.middleGoalsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, largeGoalId, mainGoalId, position, goalTitle, goalDescription, goalType, targetYear, targetAmount, progress, middleGoalsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiLargeGoalsLargeGoalIdDetailGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    largeGoalId: ").append(toIndentedString(largeGoalId)).append("\n");
    sb.append("    mainGoalId: ").append(toIndentedString(mainGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    targetYear: ").append(toIndentedString(targetYear)).append("\n");
    sb.append("    targetAmount: ").append(toIndentedString(targetAmount)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
    sb.append("    middleGoalsCount: ").append(toIndentedString(middleGoalsCount)).append("\n");
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

