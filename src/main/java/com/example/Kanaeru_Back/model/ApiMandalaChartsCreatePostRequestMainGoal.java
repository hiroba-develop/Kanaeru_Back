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
 * ApiMandalaChartsCreatePostRequestMainGoal
 */

@JsonTypeName("_api_mandala_charts_create_post_request_main_goal")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsCreatePostRequestMainGoal {

  private String goalTitle;

  private String startYearMonth;

  public ApiMandalaChartsCreatePostRequestMainGoal goalTitle(String goalTitle) {
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

  public ApiMandalaChartsCreatePostRequestMainGoal startYearMonth(String startYearMonth) {
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
    ApiMandalaChartsCreatePostRequestMainGoal apiMandalaChartsCreatePostRequestMainGoal = (ApiMandalaChartsCreatePostRequestMainGoal) o;
    return Objects.equals(this.goalTitle, apiMandalaChartsCreatePostRequestMainGoal.goalTitle) &&
        Objects.equals(this.startYearMonth, apiMandalaChartsCreatePostRequestMainGoal.startYearMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(goalTitle, startYearMonth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsCreatePostRequestMainGoal {\n");
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

