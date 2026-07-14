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
 * ApiDailyGoalsCreatePost200Response
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiDailyGoalsCreatePost200Response {

  private Integer responseStatus;

  private String dailyGoalId;

  public ApiDailyGoalsCreatePost200Response responseStatus(Integer responseStatus) {
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

  public ApiDailyGoalsCreatePost200Response dailyGoalId(String dailyGoalId) {
    this.dailyGoalId = dailyGoalId;
    return this;
  }

  /**
   * Get dailyGoalId
   * @return dailyGoalId
  */
  
  @Schema(name = "daily_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("daily_goal_id")
  public String getDailyGoalId() {
    return dailyGoalId;
  }

  public void setDailyGoalId(String dailyGoalId) {
    this.dailyGoalId = dailyGoalId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDailyGoalsCreatePost200Response apiDailyGoalsCreatePost200Response = (ApiDailyGoalsCreatePost200Response) o;
    return Objects.equals(this.responseStatus, apiDailyGoalsCreatePost200Response.responseStatus) &&
        Objects.equals(this.dailyGoalId, apiDailyGoalsCreatePost200Response.dailyGoalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, dailyGoalId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDailyGoalsCreatePost200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    dailyGoalId: ").append(toIndentedString(dailyGoalId)).append("\n");
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

