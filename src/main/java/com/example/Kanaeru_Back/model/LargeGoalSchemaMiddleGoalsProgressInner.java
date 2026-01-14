package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LargeGoalSchemaMiddleGoalsProgressInner
 */

@JsonTypeName("LargeGoalSchema_middle_goals_progress_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LargeGoalSchemaMiddleGoalsProgressInner {

  private Integer position;

  private BigDecimal progress;

  public LargeGoalSchemaMiddleGoalsProgressInner position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * 1-8の範囲
   * @return position
  */
  
  @Schema(name = "position", description = "1-8の範囲", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public LargeGoalSchemaMiddleGoalsProgressInner progress(BigDecimal progress) {
    this.progress = progress;
    return this;
  }

  /**
   * 中目標の進捗率をposition順番に取得
   * @return progress
  */
  @Valid 
  @Schema(name = "progress", description = "中目標の進捗率をposition順番に取得", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("progress")
  public BigDecimal getProgress() {
    return progress;
  }

  public void setProgress(BigDecimal progress) {
    this.progress = progress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LargeGoalSchemaMiddleGoalsProgressInner largeGoalSchemaMiddleGoalsProgressInner = (LargeGoalSchemaMiddleGoalsProgressInner) o;
    return Objects.equals(this.position, largeGoalSchemaMiddleGoalsProgressInner.position) &&
        Objects.equals(this.progress, largeGoalSchemaMiddleGoalsProgressInner.progress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, progress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LargeGoalSchemaMiddleGoalsProgressInner {\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
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

