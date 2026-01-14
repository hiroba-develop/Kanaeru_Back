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
 * ApiLargeGoalsChartIdCreatePostRequest
 */

@JsonTypeName("_api_large_goals__chart_id__create_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiLargeGoalsChartIdCreatePostRequest {

  private String chartId;

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  private Integer goalType;

  private Integer targetYear;

  private BigDecimal targetAmount;

  public ApiLargeGoalsChartIdCreatePostRequest chartId(String chartId) {
    this.chartId = chartId;
    return this;
  }

  /**
   * Get chartId
   * @return chartId
  */
  
  @Schema(name = "chart_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chart_id")
  public String getChartId() {
    return chartId;
  }

  public void setChartId(String chartId) {
    this.chartId = chartId;
  }

  public ApiLargeGoalsChartIdCreatePostRequest position(Integer position) {
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

  public ApiLargeGoalsChartIdCreatePostRequest goalTitle(String goalTitle) {
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

  public ApiLargeGoalsChartIdCreatePostRequest goalDescription(String goalDescription) {
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

  public ApiLargeGoalsChartIdCreatePostRequest goalType(Integer goalType) {
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

  public ApiLargeGoalsChartIdCreatePostRequest targetYear(Integer targetYear) {
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

  public ApiLargeGoalsChartIdCreatePostRequest targetAmount(BigDecimal targetAmount) {
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
    ApiLargeGoalsChartIdCreatePostRequest apiLargeGoalsChartIdCreatePostRequest = (ApiLargeGoalsChartIdCreatePostRequest) o;
    return Objects.equals(this.chartId, apiLargeGoalsChartIdCreatePostRequest.chartId) &&
        Objects.equals(this.position, apiLargeGoalsChartIdCreatePostRequest.position) &&
        Objects.equals(this.goalTitle, apiLargeGoalsChartIdCreatePostRequest.goalTitle) &&
        Objects.equals(this.goalDescription, apiLargeGoalsChartIdCreatePostRequest.goalDescription) &&
        Objects.equals(this.goalType, apiLargeGoalsChartIdCreatePostRequest.goalType) &&
        Objects.equals(this.targetYear, apiLargeGoalsChartIdCreatePostRequest.targetYear) &&
        Objects.equals(this.targetAmount, apiLargeGoalsChartIdCreatePostRequest.targetAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chartId, position, goalTitle, goalDescription, goalType, targetYear, targetAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiLargeGoalsChartIdCreatePostRequest {\n");
    sb.append("    chartId: ").append(toIndentedString(chartId)).append("\n");
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

