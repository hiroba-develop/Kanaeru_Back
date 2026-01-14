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
 * NetAssetsSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class NetAssetsSchema {

  private String userId;

  private Integer year;

  private BigDecimal netAssetTarget;

  private BigDecimal netAssetResult;

  public NetAssetsSchema userId(String userId) {
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

  public NetAssetsSchema year(Integer year) {
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

  public NetAssetsSchema netAssetTarget(BigDecimal netAssetTarget) {
    this.netAssetTarget = netAssetTarget;
    return this;
  }

  /**
   * Get netAssetTarget
   * @return netAssetTarget
  */
  @Valid 
  @Schema(name = "netAssetTarget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netAssetTarget")
  public BigDecimal getNetAssetTarget() {
    return netAssetTarget;
  }

  public void setNetAssetTarget(BigDecimal netAssetTarget) {
    this.netAssetTarget = netAssetTarget;
  }

  public NetAssetsSchema netAssetResult(BigDecimal netAssetResult) {
    this.netAssetResult = netAssetResult;
    return this;
  }

  /**
   * Get netAssetResult
   * @return netAssetResult
  */
  @Valid 
  @Schema(name = "netAssetResult", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netAssetResult")
  public BigDecimal getNetAssetResult() {
    return netAssetResult;
  }

  public void setNetAssetResult(BigDecimal netAssetResult) {
    this.netAssetResult = netAssetResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetAssetsSchema netAssetsSchema = (NetAssetsSchema) o;
    return Objects.equals(this.userId, netAssetsSchema.userId) &&
        Objects.equals(this.year, netAssetsSchema.year) &&
        Objects.equals(this.netAssetTarget, netAssetsSchema.netAssetTarget) &&
        Objects.equals(this.netAssetResult, netAssetsSchema.netAssetResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, year, netAssetTarget, netAssetResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetAssetsSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    netAssetTarget: ").append(toIndentedString(netAssetTarget)).append("\n");
    sb.append("    netAssetResult: ").append(toIndentedString(netAssetResult)).append("\n");
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

