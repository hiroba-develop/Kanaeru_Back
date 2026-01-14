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
 * ApiSmallGoalsMiddleGoalIdCreatePostRequest
 */

@JsonTypeName("_api_small_goals__middle_goal_id__create_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSmallGoalsMiddleGoalIdCreatePostRequest {

  private Integer position;

  private String goalTitle;

  private String goalDescription;

  public ApiSmallGoalsMiddleGoalIdCreatePostRequest position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
  */
  
  @Schema(name = "position", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public ApiSmallGoalsMiddleGoalIdCreatePostRequest goalTitle(String goalTitle) {
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

  public ApiSmallGoalsMiddleGoalIdCreatePostRequest goalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
    return this;
  }

  /**
   * Get goalDescription
   * @return goalDescription
  */
  
  @Schema(name = "goal_description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_description")
  public String getGoalDescription() {
    return goalDescription;
  }

  public void setGoalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSmallGoalsMiddleGoalIdCreatePostRequest apiSmallGoalsMiddleGoalIdCreatePostRequest = (ApiSmallGoalsMiddleGoalIdCreatePostRequest) o;
    return Objects.equals(this.position, apiSmallGoalsMiddleGoalIdCreatePostRequest.position) &&
        Objects.equals(this.goalTitle, apiSmallGoalsMiddleGoalIdCreatePostRequest.goalTitle) &&
        Objects.equals(this.goalDescription, apiSmallGoalsMiddleGoalIdCreatePostRequest.goalDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, goalTitle, goalDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSmallGoalsMiddleGoalIdCreatePostRequest {\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    goalTitle: ").append(toIndentedString(goalTitle)).append("\n");
    sb.append("    goalDescription: ").append(toIndentedString(goalDescription)).append("\n");
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

