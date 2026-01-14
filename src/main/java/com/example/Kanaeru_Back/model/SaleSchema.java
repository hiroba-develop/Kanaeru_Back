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
 * SaleSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SaleSchema {

  private String userId;

  private Integer year;

  private Integer month;

  private BigDecimal saleTarget;

  private BigDecimal saleResult;

  public SaleSchema userId(String userId) {
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

  public SaleSchema year(Integer year) {
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

  public SaleSchema month(Integer month) {
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

  public SaleSchema saleTarget(BigDecimal saleTarget) {
    this.saleTarget = saleTarget;
    return this;
  }

  /**
   * Get saleTarget
   * @return saleTarget
  */
  @Valid 
  @Schema(name = "saleTarget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("saleTarget")
  public BigDecimal getSaleTarget() {
    return saleTarget;
  }

  public void setSaleTarget(BigDecimal saleTarget) {
    this.saleTarget = saleTarget;
  }

  public SaleSchema saleResult(BigDecimal saleResult) {
    this.saleResult = saleResult;
    return this;
  }

  /**
   * Get saleResult
   * @return saleResult
  */
  @Valid 
  @Schema(name = "saleResult", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("saleResult")
  public BigDecimal getSaleResult() {
    return saleResult;
  }

  public void setSaleResult(BigDecimal saleResult) {
    this.saleResult = saleResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaleSchema saleSchema = (SaleSchema) o;
    return Objects.equals(this.userId, saleSchema.userId) &&
        Objects.equals(this.year, saleSchema.year) &&
        Objects.equals(this.month, saleSchema.month) &&
        Objects.equals(this.saleTarget, saleSchema.saleTarget) &&
        Objects.equals(this.saleResult, saleSchema.saleResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, year, month, saleTarget, saleResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    saleTarget: ").append(toIndentedString(saleTarget)).append("\n");
    sb.append("    saleResult: ").append(toIndentedString(saleResult)).append("\n");
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

