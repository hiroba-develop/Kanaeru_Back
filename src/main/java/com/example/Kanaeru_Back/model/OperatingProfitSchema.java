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
 * OperatingProfitSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OperatingProfitSchema {

  private String userId;

  private Integer year;

  private Integer month;

  private BigDecimal operatingProfitTarget;

  private BigDecimal operatingProfitResult;

  public OperatingProfitSchema userId(String userId) {
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

  public OperatingProfitSchema year(Integer year) {
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

  public OperatingProfitSchema month(Integer month) {
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

  public OperatingProfitSchema operatingProfitTarget(BigDecimal operatingProfitTarget) {
    this.operatingProfitTarget = operatingProfitTarget;
    return this;
  }

  /**
   * Get operatingProfitTarget
   * @return operatingProfitTarget
  */
  @Valid 
  @Schema(name = "operatingProfitTarget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operatingProfitTarget")
  public BigDecimal getOperatingProfitTarget() {
    return operatingProfitTarget;
  }

  public void setOperatingProfitTarget(BigDecimal operatingProfitTarget) {
    this.operatingProfitTarget = operatingProfitTarget;
  }

  public OperatingProfitSchema operatingProfitResult(BigDecimal operatingProfitResult) {
    this.operatingProfitResult = operatingProfitResult;
    return this;
  }

  /**
   * Get operatingProfitResult
   * @return operatingProfitResult
  */
  @Valid 
  @Schema(name = "operatingProfitResult", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operatingProfitResult")
  public BigDecimal getOperatingProfitResult() {
    return operatingProfitResult;
  }

  public void setOperatingProfitResult(BigDecimal operatingProfitResult) {
    this.operatingProfitResult = operatingProfitResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperatingProfitSchema operatingProfitSchema = (OperatingProfitSchema) o;
    return Objects.equals(this.userId, operatingProfitSchema.userId) &&
        Objects.equals(this.year, operatingProfitSchema.year) &&
        Objects.equals(this.month, operatingProfitSchema.month) &&
        Objects.equals(this.operatingProfitTarget, operatingProfitSchema.operatingProfitTarget) &&
        Objects.equals(this.operatingProfitResult, operatingProfitSchema.operatingProfitResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, year, month, operatingProfitTarget, operatingProfitResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperatingProfitSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    operatingProfitTarget: ").append(toIndentedString(operatingProfitTarget)).append("\n");
    sb.append("    operatingProfitResult: ").append(toIndentedString(operatingProfitResult)).append("\n");
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

