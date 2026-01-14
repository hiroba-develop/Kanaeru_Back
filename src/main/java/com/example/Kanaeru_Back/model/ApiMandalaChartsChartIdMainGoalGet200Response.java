package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiMandalaChartsChartIdMainGoalGet200Response
 */

@JsonTypeName("_api_mandala_charts__chart_id__main_goal_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsChartIdMainGoalGet200Response {

  private Integer responseStatus;

  private String mainGoalId;

  private String chartId;

  private String goalTitle;

  private String startYearMonth;

  public ApiMandalaChartsChartIdMainGoalGet200Response responseStatus(Integer responseStatus) {
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

  public ApiMandalaChartsChartIdMainGoalGet200Response mainGoalId(String mainGoalId) {
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

  public ApiMandalaChartsChartIdMainGoalGet200Response chartId(String chartId) {
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

  public ApiMandalaChartsChartIdMainGoalGet200Response goalTitle(String goalTitle) {
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

  public ApiMandalaChartsChartIdMainGoalGet200Response startYearMonth(String startYearMonth) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMandalaChartsChartIdMainGoalGet200Response apiMandalaChartsChartIdMainGoalGet200Response = (ApiMandalaChartsChartIdMainGoalGet200Response) o;
    return Objects.equals(this.responseStatus, apiMandalaChartsChartIdMainGoalGet200Response.responseStatus) &&
        Objects.equals(this.mainGoalId, apiMandalaChartsChartIdMainGoalGet200Response.mainGoalId) &&
        Objects.equals(this.chartId, apiMandalaChartsChartIdMainGoalGet200Response.chartId) &&
        Objects.equals(this.goalTitle, apiMandalaChartsChartIdMainGoalGet200Response.goalTitle) &&
        Objects.equals(this.startYearMonth, apiMandalaChartsChartIdMainGoalGet200Response.startYearMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, mainGoalId, chartId, goalTitle, startYearMonth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsChartIdMainGoalGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    mainGoalId: ").append(toIndentedString(mainGoalId)).append("\n");
    sb.append("    chartId: ").append(toIndentedString(chartId)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    startYearMonth: ").append(toIndentedString(startYearMonth)).append("\n");
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

