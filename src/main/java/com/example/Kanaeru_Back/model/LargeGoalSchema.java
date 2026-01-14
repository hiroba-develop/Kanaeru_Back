package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.LargeGoalSchemaMiddleGoalsProgressInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LargeGoalSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LargeGoalSchema {

  private String largeGoalId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  private BigDecimal progress;

  @Valid
  private List<@Valid LargeGoalSchemaMiddleGoalsProgressInner> middleGoalsProgress;

  public LargeGoalSchema largeGoalId(String largeGoalId) {
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

  public LargeGoalSchema position(Integer position) {
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

  public LargeGoalSchema goalTitle(String goalTitle) {
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

  public LargeGoalSchema goalDescription(String goalDescription) {
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

  public LargeGoalSchema goalType(Integer goalType) {
    this.goalType = goalType;
    return this;
  }

  /**
   * Get goalType
   * @return goalType
  */
  
  @Schema(name = "goal_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_type")
  public Integer getGoalType() {
    return goalType;
  }

  public void setGoalType(Integer goalType) {
    this.goalType = goalType;
  }

  public LargeGoalSchema targetYear(Integer targetYear) {
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

  public LargeGoalSchema targetAmount(BigDecimal targetAmount) {
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

  public LargeGoalSchema progress(BigDecimal progress) {
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

  public LargeGoalSchema middleGoalsProgress(List<@Valid LargeGoalSchemaMiddleGoalsProgressInner> middleGoalsProgress) {
    this.middleGoalsProgress = middleGoalsProgress;
    return this;
  }

  public LargeGoalSchema addMiddleGoalsProgressItem(LargeGoalSchemaMiddleGoalsProgressInner middleGoalsProgressItem) {
    if (this.middleGoalsProgress == null) {
      this.middleGoalsProgress = new ArrayList<>();
    }
    this.middleGoalsProgress.add(middleGoalsProgressItem);
    return this;
  }

  /**
   * Get middleGoalsProgress
   * @return middleGoalsProgress
  */
  @Valid 
  @Schema(name = "middle_goals_progress", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middle_goals_progress")
  public List<@Valid LargeGoalSchemaMiddleGoalsProgressInner> getMiddleGoalsProgress() {
    return middleGoalsProgress;
  }

  public void setMiddleGoalsProgress(List<@Valid LargeGoalSchemaMiddleGoalsProgressInner> middleGoalsProgress) {
    this.middleGoalsProgress = middleGoalsProgress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LargeGoalSchema largeGoalSchema = (LargeGoalSchema) o;
    return Objects.equals(this.largeGoalId, largeGoalSchema.largeGoalId) &&
        Objects.equals(this.position, largeGoalSchema.position) &&
        Objects.equals(this.goalTitle, largeGoalSchema.goalTitle) &&
        Objects.equals(this.goalDescription, largeGoalSchema.goalDescription) &&
        Objects.equals(this.goalType, largeGoalSchema.goalType) &&
        Objects.equals(this.targetYear, largeGoalSchema.targetYear) &&
        Objects.equals(this.targetAmount, largeGoalSchema.targetAmount) &&
        Objects.equals(this.progress, largeGoalSchema.progress) &&
        Objects.equals(this.middleGoalsProgress, largeGoalSchema.middleGoalsProgress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(largeGoalId, position, goalTitle, goalDescription, goalType, targetYear, targetAmount, progress, middleGoalsProgress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LargeGoalSchema {\n");
    sb.append("    largeGoalId: ").append(toIndentedString(largeGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    targetYear: ").append(toIndentedString(targetYear)).append("\n");
    sb.append("    targetAmount: ").append(toIndentedString(targetAmount)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
    sb.append("    middleGoalsProgress: ").append(toIndentedString(middleGoalsProgress)).append("\n");
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

