package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DailyGoalSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class DailyGoalSchema {

  private String dailyGoalId;

  private String userId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate goalDate;

  private String title;

  private String isCompleted;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime completedAt;

  private String source;

  private String memo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  private String categoryGoalId;

  private Integer plannedMin;

  private Integer actualMin;

  private Integer sortOrder;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate carriedFrom;

  public DailyGoalSchema dailyGoalId(String dailyGoalId) {
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

  public DailyGoalSchema userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "user_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user_id")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public DailyGoalSchema goalDate(LocalDate goalDate) {
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

  public DailyGoalSchema title(String title) {
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

  public DailyGoalSchema isCompleted(String isCompleted) {
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

  public DailyGoalSchema completedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
    return this;
  }

  /**
   * Get completedAt
   * @return completedAt
  */
  @Valid 
  @Schema(name = "completed_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("completed_at")
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public DailyGoalSchema source(String source) {
    this.source = source;
    return this;
  }

  /**
   * 1:手動 2:Slack
   * @return source
  */
  
  @Schema(name = "source", description = "1:手動 2:Slack", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public DailyGoalSchema memo(String memo) {
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

  public DailyGoalSchema dueDate(LocalDate dueDate) {
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

  public DailyGoalSchema categoryGoalId(String categoryGoalId) {
    this.categoryGoalId = categoryGoalId;
    return this;
  }

  /**
   * FK → LARGE_GOALS
   * @return categoryGoalId
  */
  
  @Schema(name = "category_goal_id", description = "FK → LARGE_GOALS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category_goal_id")
  public String getCategoryGoalId() {
    return categoryGoalId;
  }

  public void setCategoryGoalId(String categoryGoalId) {
    this.categoryGoalId = categoryGoalId;
  }

  public DailyGoalSchema plannedMin(Integer plannedMin) {
    this.plannedMin = plannedMin;
    return this;
  }

  /**
   * 予定時間（分）
   * @return plannedMin
  */
  
  @Schema(name = "planned_min", description = "予定時間（分）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("planned_min")
  public Integer getPlannedMin() {
    return plannedMin;
  }

  public void setPlannedMin(Integer plannedMin) {
    this.plannedMin = plannedMin;
  }

  public DailyGoalSchema actualMin(Integer actualMin) {
    this.actualMin = actualMin;
    return this;
  }

  /**
   * 実績時間（分）
   * @return actualMin
  */
  
  @Schema(name = "actual_min", description = "実績時間（分）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("actual_min")
  public Integer getActualMin() {
    return actualMin;
  }

  public void setActualMin(Integer actualMin) {
    this.actualMin = actualMin;
  }

  public DailyGoalSchema sortOrder(Integer sortOrder) {
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

  public DailyGoalSchema carriedFrom(LocalDate carriedFrom) {
    this.carriedFrom = carriedFrom;
    return this;
  }

  /**
   * 引継ぎ元日付
   * @return carriedFrom
  */
  @Valid 
  @Schema(name = "carried_from", description = "引継ぎ元日付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("carried_from")
  public LocalDate getCarriedFrom() {
    return carriedFrom;
  }

  public void setCarriedFrom(LocalDate carriedFrom) {
    this.carriedFrom = carriedFrom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DailyGoalSchema dailyGoalSchema = (DailyGoalSchema) o;
    return Objects.equals(this.dailyGoalId, dailyGoalSchema.dailyGoalId) &&
        Objects.equals(this.userId, dailyGoalSchema.userId) &&
        Objects.equals(this.goalDate, dailyGoalSchema.goalDate) &&
        Objects.equals(this.title, dailyGoalSchema.title) &&
        Objects.equals(this.isCompleted, dailyGoalSchema.isCompleted) &&
        Objects.equals(this.completedAt, dailyGoalSchema.completedAt) &&
        Objects.equals(this.source, dailyGoalSchema.source) &&
        Objects.equals(this.memo, dailyGoalSchema.memo) &&
        Objects.equals(this.dueDate, dailyGoalSchema.dueDate) &&
        Objects.equals(this.categoryGoalId, dailyGoalSchema.categoryGoalId) &&
        Objects.equals(this.plannedMin, dailyGoalSchema.plannedMin) &&
        Objects.equals(this.actualMin, dailyGoalSchema.actualMin) &&
        Objects.equals(this.sortOrder, dailyGoalSchema.sortOrder) &&
        Objects.equals(this.carriedFrom, dailyGoalSchema.carriedFrom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dailyGoalId, userId, goalDate, title, isCompleted, completedAt, source, memo, dueDate, categoryGoalId, plannedMin, actualMin, sortOrder, carriedFrom);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DailyGoalSchema {\n");
    sb.append("    dailyGoalId: ").append(toIndentedString(dailyGoalId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    goalDate: ").append(toIndentedString(goalDate)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    memo: ").append(toIndentedString(memo)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    categoryGoalId: ").append(toIndentedString(categoryGoalId)).append("\n");
    sb.append("    plannedMin: ").append(toIndentedString(plannedMin)).append("\n");
    sb.append("    actualMin: ").append(toIndentedString(actualMin)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    carriedFrom: ").append(toIndentedString(carriedFrom)).append("\n");
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

