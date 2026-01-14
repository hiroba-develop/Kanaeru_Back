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
 * ApiMiddleGoalsLargeGoalIdCreatePost200Response
 */

@JsonTypeName("_api_middle_goals__large_goal_id__create_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMiddleGoalsLargeGoalIdCreatePost200Response {

  private Integer responseStatus;

  private String middleGoalId;

  private String message;

  public ApiMiddleGoalsLargeGoalIdCreatePost200Response responseStatus(Integer responseStatus) {
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

  public ApiMiddleGoalsLargeGoalIdCreatePost200Response middleGoalId(String middleGoalId) {
    this.middleGoalId = middleGoalId;
    return this;
  }

  /**
   * Get middleGoalId
   * @return middleGoalId
  */
  
  @Schema(name = "middle_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middle_goal_id")
  public String getMiddleGoalId() {
    return middleGoalId;
  }

  public void setMiddleGoalId(String middleGoalId) {
    this.middleGoalId = middleGoalId;
  }

  public ApiMiddleGoalsLargeGoalIdCreatePost200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMiddleGoalsLargeGoalIdCreatePost200Response apiMiddleGoalsLargeGoalIdCreatePost200Response = (ApiMiddleGoalsLargeGoalIdCreatePost200Response) o;
    return Objects.equals(this.responseStatus, apiMiddleGoalsLargeGoalIdCreatePost200Response.responseStatus) &&
        Objects.equals(this.middleGoalId, apiMiddleGoalsLargeGoalIdCreatePost200Response.middleGoalId) &&
        Objects.equals(this.message, apiMiddleGoalsLargeGoalIdCreatePost200Response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, middleGoalId, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMiddleGoalsLargeGoalIdCreatePost200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    middleGoalId: ").append(toIndentedString(middleGoalId)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

