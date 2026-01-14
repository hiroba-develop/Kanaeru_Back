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
 * ApiYearlyBudgetActualGet200Response
 */

@JsonTypeName("_api_yearlyBudgetActual_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiYearlyBudgetActualGet200Response {

  private Integer responseStatus;

  private String saleId;

  private String userId;

  private Integer year;

  private Integer month;

  private BigDecimal saleTarget;

  private String profitId;

  private BigDecimal profitTarget;

  private String netAssetId;

  private BigDecimal netAssetTarget;

  private BigDecimal netAssetResult;

  public ApiYearlyBudgetActualGet200Response responseStatus(Integer responseStatus) {
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

  public ApiYearlyBudgetActualGet200Response saleId(String saleId) {
    this.saleId = saleId;
    return this;
  }

  /**
   * Get saleId
   * @return saleId
  */
  
  @Schema(name = "saleId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("saleId")
  public String getSaleId() {
    return saleId;
  }

  public void setSaleId(String saleId) {
    this.saleId = saleId;
  }

  public ApiYearlyBudgetActualGet200Response userId(String userId) {
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

  public ApiYearlyBudgetActualGet200Response year(Integer year) {
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

  public ApiYearlyBudgetActualGet200Response month(Integer month) {
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

  public ApiYearlyBudgetActualGet200Response saleTarget(BigDecimal saleTarget) {
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

  public ApiYearlyBudgetActualGet200Response profitId(String profitId) {
    this.profitId = profitId;
    return this;
  }

  /**
   * Get profitId
   * @return profitId
  */
  
  @Schema(name = "profitId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profitId")
  public String getProfitId() {
    return profitId;
  }

  public void setProfitId(String profitId) {
    this.profitId = profitId;
  }

  public ApiYearlyBudgetActualGet200Response profitTarget(BigDecimal profitTarget) {
    this.profitTarget = profitTarget;
    return this;
  }

  /**
   * Get profitTarget
   * @return profitTarget
  */
  @Valid 
  @Schema(name = "profitTarget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profitTarget")
  public BigDecimal getProfitTarget() {
    return profitTarget;
  }

  public void setProfitTarget(BigDecimal profitTarget) {
    this.profitTarget = profitTarget;
  }

  public ApiYearlyBudgetActualGet200Response netAssetId(String netAssetId) {
    this.netAssetId = netAssetId;
    return this;
  }

  /**
   * Get netAssetId
   * @return netAssetId
  */
  
  @Schema(name = "netAssetId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netAssetId")
  public String getNetAssetId() {
    return netAssetId;
  }

  public void setNetAssetId(String netAssetId) {
    this.netAssetId = netAssetId;
  }

  public ApiYearlyBudgetActualGet200Response netAssetTarget(BigDecimal netAssetTarget) {
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

  public ApiYearlyBudgetActualGet200Response netAssetResult(BigDecimal netAssetResult) {
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
    ApiYearlyBudgetActualGet200Response apiYearlyBudgetActualGet200Response = (ApiYearlyBudgetActualGet200Response) o;
    return Objects.equals(this.responseStatus, apiYearlyBudgetActualGet200Response.responseStatus) &&
        Objects.equals(this.saleId, apiYearlyBudgetActualGet200Response.saleId) &&
        Objects.equals(this.userId, apiYearlyBudgetActualGet200Response.userId) &&
        Objects.equals(this.year, apiYearlyBudgetActualGet200Response.year) &&
        Objects.equals(this.month, apiYearlyBudgetActualGet200Response.month) &&
        Objects.equals(this.saleTarget, apiYearlyBudgetActualGet200Response.saleTarget) &&
        Objects.equals(this.profitId, apiYearlyBudgetActualGet200Response.profitId) &&
        Objects.equals(this.profitTarget, apiYearlyBudgetActualGet200Response.profitTarget) &&
        Objects.equals(this.netAssetId, apiYearlyBudgetActualGet200Response.netAssetId) &&
        Objects.equals(this.netAssetTarget, apiYearlyBudgetActualGet200Response.netAssetTarget) &&
        Objects.equals(this.netAssetResult, apiYearlyBudgetActualGet200Response.netAssetResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, saleId, userId, year, month, saleTarget, profitId, profitTarget, netAssetId, netAssetTarget, netAssetResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiYearlyBudgetActualGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    saleId: ").append(toIndentedString(saleId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    saleTarget: ").append(toIndentedString(saleTarget)).append("\n");
    sb.append("    profitId: ").append(toIndentedString(profitId)).append("\n");
    sb.append("    profitTarget: ").append(toIndentedString(profitTarget)).append("\n");
    sb.append("    netAssetId: ").append(toIndentedString(netAssetId)).append("\n");
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

