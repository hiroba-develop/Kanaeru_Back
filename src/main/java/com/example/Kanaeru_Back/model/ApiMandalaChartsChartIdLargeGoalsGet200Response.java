package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.LargeGoalSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ApiMandalaChartsChartIdLargeGoalsGet200Response
 */

@JsonTypeName("_api_mandala_charts__chart_id__large_goals_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsChartIdLargeGoalsGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid LargeGoalSchema> largeGoals;

  public ApiMandalaChartsChartIdLargeGoalsGet200Response responseStatus(Integer responseStatus) {
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

  public ApiMandalaChartsChartIdLargeGoalsGet200Response largeGoals(List<@Valid LargeGoalSchema> largeGoals) {
    this.largeGoals = largeGoals;
    return this;
  }

  public ApiMandalaChartsChartIdLargeGoalsGet200Response addLargeGoalsItem(LargeGoalSchema largeGoalsItem) {
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
    ApiMandalaChartsChartIdLargeGoalsGet200Response apiMandalaChartsChartIdLargeGoalsGet200Response = (ApiMandalaChartsChartIdLargeGoalsGet200Response) o;
    return Objects.equals(this.responseStatus, apiMandalaChartsChartIdLargeGoalsGet200Response.responseStatus) &&
        Objects.equals(this.largeGoals, apiMandalaChartsChartIdLargeGoalsGet200Response.largeGoals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, largeGoals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsChartIdLargeGoalsGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
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

