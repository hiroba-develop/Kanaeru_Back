package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiDailyGoalsUpdatePutRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiDailyGoalsUpdatePutRequest {

  private String title;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate goalDate;

  private String memo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  private String categoryGoalId;

  private Integer plannedMin;

  private Integer sortOrder;

  public ApiDailyGoalsUpdatePutRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ApiDailyGoalsUpdatePutRequest goalDate(LocalDate goalDate) {
    this.goalDate = goalDate;
    return this;
  }

  /**
   * Get goalDate
   * @return goalDate
  */
  @Valid 
  @Schema(name = "goal_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goal_date")
  public LocalDate getGoalDate() {
    return goalDate;
  }

  public void setGoalDate(LocalDate goalDate) {
    this.goalDate = goalDate;
  }

  public ApiDailyGoalsUpdatePutRequest memo(String memo) {
    this.memo = memo;
    return this;
  }

  /**
   * Get memo
   * @return memo
  */
  
  @Schema(name = "memo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("memo")
  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public ApiDailyGoalsUpdatePutRequest dueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
  */
  @Valid 
  @Schema(name = "due_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("due_date")
  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public ApiDailyGoalsUpdatePutRequest categoryGoalId(String categoryGoalId) {
    this.categoryGoalId = categoryGoalId;
    return this;
  }

  /**
   * Get categoryGoalId
   * @return categoryGoalId
  */
  
  @Schema(name = "category_goal_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category_goal_id")
  public String getCategoryGoalId() {
    return categoryGoalId;
  }

  public void setCategoryGoalId(String categoryGoalId) {
    this.categoryGoalId = categoryGoalId;
  }

  public ApiDailyGoalsUpdatePutRequest plannedMin(Integer plannedMin) {
    this.plannedMin = plannedMin;
    return this;
  }

  /**
   * Get plannedMin
   * @return plannedMin
  */
  
  @Schema(name = "planned_min", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("planned_min")
  public Integer getPlannedMin() {
    return plannedMin;
  }

  public void setPlannedMin(Integer plannedMin) {
    this.plannedMin = plannedMin;
  }

  public ApiDailyGoalsUpdatePutRequest sortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  /**
   * Get sortOrder
   * @return sortOrder
  */
  
  @Schema(name = "sort_order", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sort_order")
  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDailyGoalsUpdatePutRequest apiDailyGoalsUpdatePutRequest = (ApiDailyGoalsUpdatePutRequest) o;
    return Objects.equals(this.title, apiDailyGoalsUpdatePutRequest.title) &&
        Objects.equals(this.goalDate, apiDailyGoalsUpdatePutRequest.goalDate) &&
        Objects.equals(this.memo, apiDailyGoalsUpdatePutRequest.memo) &&
        Objects.equals(this.dueDate, apiDailyGoalsUpdatePutRequest.dueDate) &&
        Objects.equals(this.categoryGoalId, apiDailyGoalsUpdatePutRequest.categoryGoalId) &&
        Objects.equals(this.plannedMin, apiDailyGoalsUpdatePutRequest.plannedMin) &&
        Objects.equals(this.sortOrder, apiDailyGoalsUpdatePutRequest.sortOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, goalDate, memo, dueDate, categoryGoalId, plannedMin, sortOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDailyGoalsUpdatePutRequest {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    goalDate: ").append(toIndentedString(goalDate)).append("\n");
    sb.append("    memo: ").append(toIndentedString(memo)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    categoryGoalId: ").append(toIndentedString(categoryGoalId)).append("\n");
    sb.append("    plannedMin: ").append(toIndentedString(plannedMin)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
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

