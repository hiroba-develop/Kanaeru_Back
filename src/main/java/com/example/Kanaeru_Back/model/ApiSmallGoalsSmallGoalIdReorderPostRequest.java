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
 * ApiSmallGoalsSmallGoalIdReorderPostRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSmallGoalsSmallGoalIdReorderPostRequest {

  private String smallGoalId;

  private Integer position;

  public ApiSmallGoalsSmallGoalIdReorderPostRequest smallGoalId(String smallGoalId) {
    this.smallGoalId = smallGoalId;
    return this;
  }

  /**
   * Get smallGoalId
   * @return smallGoalId
  */
  
  @Schema(name = "small_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("small_goal_id")
  public String getSmallGoalId() {
    return smallGoalId;
  }

  public void setSmallGoalId(String smallGoalId) {
    this.smallGoalId = smallGoalId;
  }

  public ApiSmallGoalsSmallGoalIdReorderPostRequest position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * 新しい位置(1-10の範囲)
   * @return position
  */
  
  @Schema(name = "position", description = "新しい位置(1-10の範囲)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSmallGoalsSmallGoalIdReorderPostRequest apiSmallGoalsSmallGoalIdReorderPostRequest = (ApiSmallGoalsSmallGoalIdReorderPostRequest) o;
    return Objects.equals(this.smallGoalId, apiSmallGoalsSmallGoalIdReorderPostRequest.smallGoalId) &&
        Objects.equals(this.position, apiSmallGoalsSmallGoalIdReorderPostRequest.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(smallGoalId, position);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSmallGoalsSmallGoalIdReorderPostRequest {\n");
    sb.append("    smallGoalId: ").append(toIndentedString(smallGoalId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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

