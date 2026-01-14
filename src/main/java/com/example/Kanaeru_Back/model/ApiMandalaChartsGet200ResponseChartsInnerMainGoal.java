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
 * ApiMandalaChartsGet200ResponseChartsInnerMainGoal
 */

@JsonTypeName("_api_mandala_charts_get_200_response_charts_inner_main_goal")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsGet200ResponseChartsInnerMainGoal {

  private String mainGoalId;

  private String goalTitle;

  public ApiMandalaChartsGet200ResponseChartsInnerMainGoal mainGoalId(String mainGoalId) {
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

  public ApiMandalaChartsGet200ResponseChartsInnerMainGoal goalTitle(String goalTitle) {
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
    ApiMandalaChartsGet200ResponseChartsInnerMainGoal apiMandalaChartsGet200ResponseChartsInnerMainGoal = (ApiMandalaChartsGet200ResponseChartsInnerMainGoal) o;
    return Objects.equals(this.mainGoalId, apiMandalaChartsGet200ResponseChartsInnerMainGoal.mainGoalId) &&
        Objects.equals(this.goalTitle, apiMandalaChartsGet200ResponseChartsInnerMainGoal.goalTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mainGoalId, goalTitle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsGet200ResponseChartsInnerMainGoal {\n");
    sb.append("    mainGoalId: ").append(toIndentedString(mainGoalId)).append("\n");
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

