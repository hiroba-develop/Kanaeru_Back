package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.DailyGoalSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiDailyGoalsGet200ResponseDayInner
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiDailyGoalsGet200ResponseDayInner {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @Valid
  private List<@Valid DailyGoalSchema> goals;

  public ApiDailyGoalsGet200ResponseDayInner date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public ApiDailyGoalsGet200ResponseDayInner goals(List<@Valid DailyGoalSchema> goals) {
    this.goals = goals;
    return this;
  }

  public ApiDailyGoalsGet200ResponseDayInner addGoalsItem(DailyGoalSchema goalsItem) {
    if (this.goals == null) {
      this.goals = new ArrayList<>();
    }
    this.goals.add(goalsItem);
    return this;
  }

  /**
   * Get goals
   * @return goals
  */
  @Valid 
  @Schema(name = "goals", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goals")
  public List<@Valid DailyGoalSchema> getGoals() {
    return goals;
  }

  public void setGoals(List<@Valid DailyGoalSchema> goals) {
    this.goals = goals;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDailyGoalsGet200ResponseDayInner apiDailyGoalsGet200ResponseDayInner = (ApiDailyGoalsGet200ResponseDayInner) o;
    return Objects.equals(this.date, apiDailyGoalsGet200ResponseDayInner.date) &&
        Objects.equals(this.goals, apiDailyGoalsGet200ResponseDayInner.goals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, goals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDailyGoalsGet200ResponseDayInner {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    goals: ").append(toIndentedString(goals)).append("\n");
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

