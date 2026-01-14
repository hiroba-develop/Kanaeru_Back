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
 * ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner
 */

@JsonTypeName("_api_middle_goals__large_goal_id__get_200_response_middle_goals_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner {

  private String middleGoalId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  private BigDecimal progress;

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner middleGoalId(String middleGoalId) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * 1-8の範囲
   * @return position
  */
  
  @Schema(name = "position", description = "1-8の範囲", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner goalTitle(String goalTitle) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner goalDescription(String goalDescription) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner goalType(Integer goalType) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner targetYear(Integer targetYear) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner targetAmount(BigDecimal targetAmount) {
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

  public ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner progress(BigDecimal progress) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner = (ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner) o;
    return Objects.equals(this.middleGoalId, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.middleGoalId) &&
        Objects.equals(this.position, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.position) &&
        Objects.equals(this.goalTitle, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.goalTitle) &&
        Objects.equals(this.goalDescription, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.goalDescription) &&
        Objects.equals(this.goalType, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.goalType) &&
        Objects.equals(this.targetYear, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.targetYear) &&
        Objects.equals(this.targetAmount, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.targetAmount) &&
        Objects.equals(this.progress, apiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner.progress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(middleGoalId, position, goalTitle, goalDescription, goalType, targetYear, targetAmount, progress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner {\n");
    sb.append("    middleGoalId: ").append(toIndentedString(middleGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    targetYear: ").append(toIndentedString(targetYear)).append("\n");
    sb.append("    targetAmount: ").append(toIndentedString(targetAmount)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
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

