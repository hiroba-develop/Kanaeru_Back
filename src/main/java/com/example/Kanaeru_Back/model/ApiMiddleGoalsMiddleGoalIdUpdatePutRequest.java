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
 * ApiMiddleGoalsMiddleGoalIdUpdatePutRequest
 */

@JsonTypeName("_api_middle_goals__middle_goal_id__update_put_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMiddleGoalsMiddleGoalIdUpdatePutRequest {

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest position(Integer position) {
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

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest goalTitle(String goalTitle) {
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

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest goalDescription(String goalDescription) {
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

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest goalType(Integer goalType) {
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

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest targetYear(Integer targetYear) {
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

  public ApiMiddleGoalsMiddleGoalIdUpdatePutRequest targetAmount(BigDecimal targetAmount) {
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
    ApiMiddleGoalsMiddleGoalIdUpdatePutRequest apiMiddleGoalsMiddleGoalIdUpdatePutRequest = (ApiMiddleGoalsMiddleGoalIdUpdatePutRequest) o;
    return Objects.equals(this.position, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.position) &&
        Objects.equals(this.goalTitle, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.goalTitle) &&
        Objects.equals(this.goalDescription, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.goalDescription) &&
        Objects.equals(this.goalType, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.goalType) &&
        Objects.equals(this.targetYear, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.targetYear) &&
        Objects.equals(this.targetAmount, apiMiddleGoalsMiddleGoalIdUpdatePutRequest.targetAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, goalTitle, goalDescription, goalType, targetYear, targetAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMiddleGoalsMiddleGoalIdUpdatePutRequest {\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
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

