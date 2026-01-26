package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MainGoalSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MainGoalSchema {

  private String mainGoalId;

  private String chartId;

  private String goalTitle;

  public MainGoalSchema mainGoalId(String mainGoalId) {
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

  public MainGoalSchema chartId(String chartId) {
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

  public MainGoalSchema goalTitle(String goalTitle) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainGoalSchema mainGoalSchema = (MainGoalSchema) o;
    return Objects.equals(this.mainGoalId, mainGoalSchema.mainGoalId) &&
        Objects.equals(this.chartId, mainGoalSchema.chartId) &&
        Objects.equals(this.goalTitle, mainGoalSchema.goalTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mainGoalId, chartId, goalTitle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainGoalSchema {\n");
    sb.append("    mainGoalId: ").append(toIndentedString(mainGoalId)).append("\n");
    sb.append("    chartId: ").append(toIndentedString(chartId)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
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

