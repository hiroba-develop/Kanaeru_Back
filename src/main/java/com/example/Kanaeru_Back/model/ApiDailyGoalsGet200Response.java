package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiDailyGoalsGet200ResponseDayInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * ApiDailyGoalsGet200Response
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiDailyGoalsGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid ApiDailyGoalsGet200ResponseDayInner> days;

  public ApiDailyGoalsGet200Response responseStatus(Integer responseStatus) {
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

  public ApiDailyGoalsGet200Response days(List<@Valid ApiDailyGoalsGet200ResponseDayInner> days) {
    this.days = days;
    return this;
  }

  public ApiDailyGoalsGet200Response addDaysItem(ApiDailyGoalsGet200ResponseDayInner daysItem) {
    if (this.days == null) {
      this.days = new ArrayList<>();
    }
    this.days.add(daysItem);
    return this;
  }

  /**
   * Get days
   * @return days
  */
  @Valid 
  @Schema(name = "days", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("days")
  public List<@Valid ApiDailyGoalsGet200ResponseDayInner> getDays() {
    return days;
  }

  public void setDays(List<@Valid ApiDailyGoalsGet200ResponseDayInner> days) {
    this.days = days;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDailyGoalsGet200Response apiDailyGoalsGet200Response = (ApiDailyGoalsGet200Response) o;
    return Objects.equals(this.responseStatus, apiDailyGoalsGet200Response.responseStatus) &&
        Objects.equals(this.days, apiDailyGoalsGet200Response.days);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, days);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDailyGoalsGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
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

