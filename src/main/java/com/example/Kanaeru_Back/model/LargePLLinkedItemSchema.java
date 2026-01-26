package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LargePLLinkedItemSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LargePLLinkedItemSchema {

  private String largeGoalId;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  public LargePLLinkedItemSchema largeGoalId(String largeGoalId) {
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

  public LargePLLinkedItemSchema goalType(Integer goalType) {
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

  public LargePLLinkedItemSchema targetYear(Integer targetYear) {
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

  public LargePLLinkedItemSchema targetAmount(BigDecimal targetAmount) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LargePLLinkedItemSchema largePLLinkedItemSchema = (LargePLLinkedItemSchema) o;
    return Objects.equals(this.largeGoalId, largePLLinkedItemSchema.largeGoalId) &&
        Objects.equals(this.goalType, largePLLinkedItemSchema.goalType) &&
        Objects.equals(this.targetYear, largePLLinkedItemSchema.targetYear) &&
        Objects.equals(this.targetAmount, largePLLinkedItemSchema.targetAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(largeGoalId, goalType, targetYear, targetAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LargePLLinkedItemSchema {\n");
    sb.append("    largeGoalId: ").append(toIndentedString(largeGoalId)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    targetYear: ").append(toIndentedString(targetYear)).append("\n");
    sb.append("    targetAmount: ").append(toIndentedString(targetAmount)).append("\n");
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

