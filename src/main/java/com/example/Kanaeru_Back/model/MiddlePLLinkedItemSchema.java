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
 * MiddlePLLinkedItemSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MiddlePLLinkedItemSchema {

  private String middleGoalId;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  public MiddlePLLinkedItemSchema middleGoalId(String middleGoalId) {
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

  public MiddlePLLinkedItemSchema goalType(Integer goalType) {
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

  public MiddlePLLinkedItemSchema targetYear(Integer targetYear) {
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

  public MiddlePLLinkedItemSchema targetAmount(BigDecimal targetAmount) {
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
    MiddlePLLinkedItemSchema middlePLLinkedItemSchema = (MiddlePLLinkedItemSchema) o;
    return Objects.equals(this.middleGoalId, middlePLLinkedItemSchema.middleGoalId) &&
        Objects.equals(this.goalType, middlePLLinkedItemSchema.goalType) &&
        Objects.equals(this.targetYear, middlePLLinkedItemSchema.targetYear) &&
        Objects.equals(this.targetAmount, middlePLLinkedItemSchema.targetAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(middleGoalId, goalType, targetYear, targetAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MiddlePLLinkedItemSchema {\n");
    sb.append("    middleGoalId: ").append(toIndentedString(middleGoalId)).append("\n");
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

