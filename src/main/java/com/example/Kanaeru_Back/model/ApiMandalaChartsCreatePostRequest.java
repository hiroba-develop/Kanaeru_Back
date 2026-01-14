package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiMandalaChartsCreatePostRequestMainGoal;
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
 * ApiMandalaChartsCreatePostRequest
 */

@JsonTypeName("_api_mandala_charts_create_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsCreatePostRequest {

  private String userId;

  private String chartId;

  private ApiMandalaChartsCreatePostRequestMainGoal mainGoal;

  public ApiMandalaChartsCreatePostRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public ApiMandalaChartsCreatePostRequest chartId(String chartId) {
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

  public ApiMandalaChartsCreatePostRequest mainGoal(ApiMandalaChartsCreatePostRequestMainGoal mainGoal) {
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
  public ApiMandalaChartsCreatePostRequestMainGoal getMainGoal() {
    return mainGoal;
  }

  public void setMainGoal(ApiMandalaChartsCreatePostRequestMainGoal mainGoal) {
    this.mainGoal = mainGoal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMandalaChartsCreatePostRequest apiMandalaChartsCreatePostRequest = (ApiMandalaChartsCreatePostRequest) o;
    return Objects.equals(this.userId, apiMandalaChartsCreatePostRequest.userId) &&
        Objects.equals(this.chartId, apiMandalaChartsCreatePostRequest.chartId) &&
        Objects.equals(this.mainGoal, apiMandalaChartsCreatePostRequest.mainGoal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, chartId, mainGoal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsCreatePostRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    chartId: ").append(toIndentedString(chartId)).append("\n");
    sb.append("    mainGoal: ").append(toIndentedString(mainGoal)).append("\n");
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

