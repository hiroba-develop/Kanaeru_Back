package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200ResponseChartsInnerMainGoal;
import com.example.Kanaeru_Back.model.LargeGoalSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiMandalaChartsGet200ResponseChartsInner
 */

@JsonTypeName("_api_mandala_charts_get_200_response_charts_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsGet200ResponseChartsInner {

  private String chartId;

  private String startYearMonth;

  private String endYearMonth;

  private Boolean isActive;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  private ApiMandalaChartsGet200ResponseChartsInnerMainGoal mainGoal;

  @Valid
  private List<@Valid LargeGoalSchema> largeGoals;

  public ApiMandalaChartsGet200ResponseChartsInner chartId(String chartId) {
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

  public ApiMandalaChartsGet200ResponseChartsInner startYearMonth(String startYearMonth) {
    this.startYearMonth = startYearMonth;
    return this;
  }

  /**
   * Get startYearMonth
   * @return startYearMonth
  */
  
  @Schema(name = "start_year_month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start_year_month")
  public String getStartYearMonth() {
    return startYearMonth;
  }

  public void setStartYearMonth(String startYearMonth) {
    this.startYearMonth = startYearMonth;
  }

  public ApiMandalaChartsGet200ResponseChartsInner endYearMonth(String endYearMonth) {
    this.endYearMonth = endYearMonth;
    return this;
  }

  /**
   * Get endYearMonth
   * @return endYearMonth
  */
  
  @Schema(name = "end_year_month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end_year_month")
  public String getEndYearMonth() {
    return endYearMonth;
  }

  public void setEndYearMonth(String endYearMonth) {
    this.endYearMonth = endYearMonth;
  }

  public ApiMandalaChartsGet200ResponseChartsInner isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
  */
  
  @Schema(name = "is_active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_active")
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public ApiMandalaChartsGet200ResponseChartsInner createdAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "created_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ApiMandalaChartsGet200ResponseChartsInner mainGoal(ApiMandalaChartsGet200ResponseChartsInnerMainGoal mainGoal) {
    this.mainGoal = mainGoal;
    return this;
  }

  /**
   * Get mainGoal
   * @return mainGoal
  */
  @Valid 
  @Schema(name = "main_goal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("main_goal")
  public ApiMandalaChartsGet200ResponseChartsInnerMainGoal getMainGoal() {
    return mainGoal;
  }

  public void setMainGoal(ApiMandalaChartsGet200ResponseChartsInnerMainGoal mainGoal) {
    this.mainGoal = mainGoal;
  }

  public ApiMandalaChartsGet200ResponseChartsInner largeGoals(List<@Valid LargeGoalSchema> largeGoals) {
    this.largeGoals = largeGoals;
    return this;
  }

  public ApiMandalaChartsGet200ResponseChartsInner addLargeGoalsItem(LargeGoalSchema largeGoalsItem) {
    if (this.largeGoals == null) {
      this.largeGoals = new ArrayList<>();
    }
    this.largeGoals.add(largeGoalsItem);
    return this;
  }

  /**
   * Get largeGoals
   * @return largeGoals
  */
  @Valid 
  @Schema(name = "large_goals", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("large_goals")
  public List<@Valid LargeGoalSchema> getLargeGoals() {
    return largeGoals;
  }

  public void setLargeGoals(List<@Valid LargeGoalSchema> largeGoals) {
    this.largeGoals = largeGoals;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMandalaChartsGet200ResponseChartsInner apiMandalaChartsGet200ResponseChartsInner = (ApiMandalaChartsGet200ResponseChartsInner) o;
    return Objects.equals(this.chartId, apiMandalaChartsGet200ResponseChartsInner.chartId) &&
        Objects.equals(this.startYearMonth, apiMandalaChartsGet200ResponseChartsInner.startYearMonth) &&
        Objects.equals(this.endYearMonth, apiMandalaChartsGet200ResponseChartsInner.endYearMonth) &&
        Objects.equals(this.isActive, apiMandalaChartsGet200ResponseChartsInner.isActive) &&
        Objects.equals(this.createdAt, apiMandalaChartsGet200ResponseChartsInner.createdAt) &&
        Objects.equals(this.mainGoal, apiMandalaChartsGet200ResponseChartsInner.mainGoal) &&
        Objects.equals(this.largeGoals, apiMandalaChartsGet200ResponseChartsInner.largeGoals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chartId, startYearMonth, endYearMonth, isActive, createdAt, mainGoal, largeGoals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsGet200ResponseChartsInner {\n");
    sb.append("    chartId: ").append(toIndentedString(chartId)).append("\n");
    sb.append("    startYearMonth: ").append(toIndentedString(startYearMonth)).append("\n");
    sb.append("    endYearMonth: ").append(toIndentedString(endYearMonth)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    mainGoal: ").append(toIndentedString(mainGoal)).append("\n");
    sb.append("    largeGoals: ").append(toIndentedString(largeGoals)).append("\n");
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

