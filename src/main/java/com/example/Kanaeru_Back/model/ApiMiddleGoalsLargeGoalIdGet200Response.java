package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner;
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
 * ApiMiddleGoalsLargeGoalIdGet200Response
 */

@JsonTypeName("_api_middle_goals__large_goal_id__get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMiddleGoalsLargeGoalIdGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner> middleGoals;

  public ApiMiddleGoalsLargeGoalIdGet200Response responseStatus(Integer responseStatus) {
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

  public ApiMiddleGoalsLargeGoalIdGet200Response middleGoals(List<@Valid ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner> middleGoals) {
    this.middleGoals = middleGoals;
    return this;
  }

  public ApiMiddleGoalsLargeGoalIdGet200Response addMiddleGoalsItem(ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner middleGoalsItem) {
    if (this.middleGoals == null) {
      this.middleGoals = new ArrayList<>();
    }
    this.middleGoals.add(middleGoalsItem);
    return this;
  }

  /**
   * Get middleGoals
   * @return middleGoals
  */
  @Valid 
  @Schema(name = "middle_goals", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middle_goals")
  public List<@Valid ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner> getMiddleGoals() {
    return middleGoals;
  }

  public void setMiddleGoals(List<@Valid ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner> middleGoals) {
    this.middleGoals = middleGoals;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMiddleGoalsLargeGoalIdGet200Response apiMiddleGoalsLargeGoalIdGet200Response = (ApiMiddleGoalsLargeGoalIdGet200Response) o;
    return Objects.equals(this.responseStatus, apiMiddleGoalsLargeGoalIdGet200Response.responseStatus) &&
        Objects.equals(this.middleGoals, apiMiddleGoalsLargeGoalIdGet200Response.middleGoals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, middleGoals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMiddleGoalsLargeGoalIdGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    middleGoals: ").append(toIndentedString(middleGoals)).append("\n");
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

