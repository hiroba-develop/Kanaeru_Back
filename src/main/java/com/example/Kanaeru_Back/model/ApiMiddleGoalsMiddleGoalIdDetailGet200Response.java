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
 * ApiMiddleGoalsMiddleGoalIdDetailGet200Response
 */

@JsonTypeName("_api_middle_goals__middle_goal_id__detail_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMiddleGoalsMiddleGoalIdDetailGet200Response {

  private Integer responseStatus;

  private String middleGoalId;

  private String largeGoalId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  private BigDecimal progress;

  private Integer smallGoalsCount;

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response responseStatus(Integer responseStatus) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response middleGoalId(String middleGoalId) {
    this.middleGoalId = middleGoalId;
    return this;
  }

  /**
   * Get middleGoalId
   * @return middleGoalId
  */
  
  @Schema(name = "middle_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middle_goal_id")
  public String getMiddleGoalId() {
    return middleGoalId;
  }

  public void setMiddleGoalId(String middleGoalId) {
    this.middleGoalId = middleGoalId;
  }

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response largeGoalId(String largeGoalId) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response position(Integer position) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response goalTitle(String goalTitle) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response goalDescription(String goalDescription) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response goalType(Integer goalType) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response targetYear(Integer targetYear) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response targetAmount(BigDecimal targetAmount) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response progress(BigDecimal progress) {
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

  public ApiMiddleGoalsMiddleGoalIdDetailGet200Response smallGoalsCount(Integer smallGoalsCount) {
    this.smallGoalsCount = smallGoalsCount;
    return this;
  }

  /**
   * Get smallGoalsCount
   * @return smallGoalsCount
  */
  
  @Schema(name = "small_goals_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("small_goals_count")
  public Integer getSmallGoalsCount() {
    return smallGoalsCount;
  }

  public void setSmallGoalsCount(Integer smallGoalsCount) {
    this.smallGoalsCount = smallGoalsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMiddleGoalsMiddleGoalIdDetailGet200Response apiMiddleGoalsMiddleGoalIdDetailGet200Response = (ApiMiddleGoalsMiddleGoalIdDetailGet200Response) o;
    return Objects.equals(this.responseStatus, apiMiddleGoalsMiddleGoalIdDetailGet200Response.responseStatus) &&
        Objects.equals(this.middleGoalId, apiMiddleGoalsMiddleGoalIdDetailGet200Response.middleGoalId) &&
        Objects.equals(this.largeGoalId, apiMiddleGoalsMiddleGoalIdDetailGet200Response.largeGoalId) &&
        Objects.equals(this.position, apiMiddleGoalsMiddleGoalIdDetailGet200Response.position) &&
        Objects.equals(this.goalTitle, apiMiddleGoalsMiddleGoalIdDetailGet200Response.goalTitle) &&
        Objects.equals(this.goalDescription, apiMiddleGoalsMiddleGoalIdDetailGet200Response.goalDescription) &&
        Objects.equals(this.goalType, apiMiddleGoalsMiddleGoalIdDetailGet200Response.goalType) &&
        Objects.equals(this.targetYear, apiMiddleGoalsMiddleGoalIdDetailGet200Response.targetYear) &&
        Objects.equals(this.targetAmount, apiMiddleGoalsMiddleGoalIdDetailGet200Response.targetAmount) &&
        Objects.equals(this.progress, apiMiddleGoalsMiddleGoalIdDetailGet200Response.progress) &&
        Objects.equals(this.smallGoalsCount, apiMiddleGoalsMiddleGoalIdDetailGet200Response.smallGoalsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, middleGoalId, largeGoalId, position, goalTitle, goalDescription, goalType, targetYear, targetAmount, progress, smallGoalsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMiddleGoalsMiddleGoalIdDetailGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    middleGoalId: ").append(toIndentedString(middleGoalId)).append("\n");
    sb.append("    largeGoalId: ").append(toIndentedString(largeGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    targetYear: ").append(toIndentedString(targetYear)).append("\n");
    sb.append("    targetAmount: ").append(toIndentedString(targetAmount)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
    sb.append("    smallGoalsCount: ").append(toIndentedString(smallGoalsCount)).append("\n");
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

