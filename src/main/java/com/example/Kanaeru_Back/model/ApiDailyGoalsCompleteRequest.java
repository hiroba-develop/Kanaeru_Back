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
 * ApiDailyGoalsCompleteRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiDailyGoalsCompleteRequest {

  private String isCompleted;

  private Integer actualMin;

  public ApiDailyGoalsCompleteRequest isCompleted(String isCompleted) {
    this.isCompleted = isCompleted;
    return this;
  }

  /**
   * 0:未完了 1:完了
   * @return isCompleted
  */
  
  @Schema(name = "is_completed", description = "0:未完了 1:完了", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_completed")
  public String getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(String isCompleted) {
    this.isCompleted = isCompleted;
  }

  public ApiDailyGoalsCompleteRequest actualMin(Integer actualMin) {
    this.actualMin = actualMin;
    return this;
  }

  /**
   * 実績時間（分）、未完了時はnull
   * @return actualMin
  */
  
  @Schema(name = "actual_min", description = "実績時間（分）、未完了時はnull", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("actual_min")
  public Integer getActualMin() {
    return actualMin;
  }

  public void setActualMin(Integer actualMin) {
    this.actualMin = actualMin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDailyGoalsCompleteRequest apiDailyGoalsCompleteRequest = (ApiDailyGoalsCompleteRequest) o;
    return Objects.equals(this.isCompleted, apiDailyGoalsCompleteRequest.isCompleted) &&
        Objects.equals(this.actualMin, apiDailyGoalsCompleteRequest.actualMin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isCompleted, actualMin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDailyGoalsCompleteRequest {\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    actualMin: ").append(toIndentedString(actualMin)).append("\n");
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

