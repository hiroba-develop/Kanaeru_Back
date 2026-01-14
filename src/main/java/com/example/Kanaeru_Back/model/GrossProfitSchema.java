package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GrossProfitSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GrossProfitSchema {

  private String userId;

  private Integer year;

  private Integer month;

  private BigDecimal grossProfitTarget;

  private BigDecimal grossProfitResult;

  public GrossProfitSchema userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public GrossProfitSchema year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  
  @Schema(name = "year", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public GrossProfitSchema month(Integer month) {
    this.month = month;
    return this;
  }

  /**
   * Get month
   * @return month
  */
  
  @Schema(name = "month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("month")
  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public GrossProfitSchema grossProfitTarget(BigDecimal grossProfitTarget) {
    this.grossProfitTarget = grossProfitTarget;
    return this;
  }

  /**
   * Get grossProfitTarget
   * @return grossProfitTarget
  */
  @Valid 
  @Schema(name = "grossProfitTarget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("grossProfitTarget")
  public BigDecimal getGrossProfitTarget() {
    return grossProfitTarget;
  }

  public void setGrossProfitTarget(BigDecimal grossProfitTarget) {
    this.grossProfitTarget = grossProfitTarget;
  }

  public GrossProfitSchema grossProfitResult(BigDecimal grossProfitResult) {
    this.grossProfitResult = grossProfitResult;
    return this;
  }

  /**
   * Get grossProfitResult
   * @return grossProfitResult
  */
  @Valid 
  @Schema(name = "grossProfitResult", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("grossProfitResult")
  public BigDecimal getGrossProfitResult() {
    return grossProfitResult;
  }

  public void setGrossProfitResult(BigDecimal grossProfitResult) {
    this.grossProfitResult = grossProfitResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrossProfitSchema grossProfitSchema = (GrossProfitSchema) o;
    return Objects.equals(this.userId, grossProfitSchema.userId) &&
        Objects.equals(this.year, grossProfitSchema.year) &&
        Objects.equals(this.month, grossProfitSchema.month) &&
        Objects.equals(this.grossProfitTarget, grossProfitSchema.grossProfitTarget) &&
        Objects.equals(this.grossProfitResult, grossProfitSchema.grossProfitResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, year, month, grossProfitTarget, grossProfitResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrossProfitSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    grossProfitTarget: ").append(toIndentedString(grossProfitTarget)).append("\n");
    sb.append("    grossProfitResult: ").append(toIndentedString(grossProfitResult)).append("\n");
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

