package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.model.LargePLLinkedItemSchema;
import com.example.Kanaeru_Back.model.MiddlePLLinkedItemSchema;
import com.example.Kanaeru_Back.model.OperatingProfitSchema;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ApiYearlyBudgetActualGet200Response
 */

@JsonTypeName("_api_yearlyBudgetActual_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiYearlyBudgetActualGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid SaleSchema> saleSchema;

  @Valid
  private List<@Valid GrossProfitSchema> grossProfitSchema;

  @Valid
  private List<@Valid OperatingProfitSchema> operatingProfitSchema;

  @Valid
  private List<@Valid LargePLLinkedItemSchema> largePLLinkedItemSchema;

  @Valid
  private List<@Valid MiddlePLLinkedItemSchema> middlePLLinkedItemSchema;

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

  public ApiYearlyBudgetActualGet200Response saleSchema(List<@Valid SaleSchema> saleSchema) {
    this.saleSchema = saleSchema;
    return this;
  }

  public ApiYearlyBudgetActualGet200Response addSaleSchemaItem(SaleSchema saleSchemaItem) {
    if (this.saleSchema == null) {
      this.saleSchema = new ArrayList<>();
    }
    this.saleSchema.add(saleSchemaItem);
    return this;
  }

  /**
   * Get saleSchema
   * @return saleSchema
  */
  @Valid 
  @Schema(name = "saleSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("saleSchema")
  public List<@Valid SaleSchema> getSaleSchema() {
    return saleSchema;
  }

  public void setSaleSchema(List<@Valid SaleSchema> saleSchema) {
    this.saleSchema = saleSchema;
  }

  public ApiYearlyBudgetActualGet200Response grossProfitSchema(List<@Valid GrossProfitSchema> grossProfitSchema) {
    this.grossProfitSchema = grossProfitSchema;
    return this;
  }

  public ApiYearlyBudgetActualGet200Response addGrossProfitSchemaItem(GrossProfitSchema grossProfitSchemaItem) {
    if (this.grossProfitSchema == null) {
      this.grossProfitSchema = new ArrayList<>();
    }
    this.grossProfitSchema.add(grossProfitSchemaItem);
    return this;
  }

  /**
   * Get grossProfitSchema
   * @return grossProfitSchema
  */
  @Valid 
  @Schema(name = "grossProfitSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("grossProfitSchema")
  public List<@Valid GrossProfitSchema> getGrossProfitSchema() {
    return grossProfitSchema;
  }

  public void setGrossProfitSchema(List<@Valid GrossProfitSchema> grossProfitSchema) {
    this.grossProfitSchema = grossProfitSchema;
  }

  public ApiYearlyBudgetActualGet200Response operatingProfitSchema(List<@Valid OperatingProfitSchema> operatingProfitSchema) {
    this.operatingProfitSchema = operatingProfitSchema;
    return this;
  }

  public ApiYearlyBudgetActualGet200Response addOperatingProfitSchemaItem(OperatingProfitSchema operatingProfitSchemaItem) {
    if (this.operatingProfitSchema == null) {
      this.operatingProfitSchema = new ArrayList<>();
    }
    this.operatingProfitSchema.add(operatingProfitSchemaItem);
    return this;
  }

  /**
   * Get operatingProfitSchema
   * @return operatingProfitSchema
  */
  @Valid 
  @Schema(name = "operatingProfitSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operatingProfitSchema")
  public List<@Valid OperatingProfitSchema> getOperatingProfitSchema() {
    return operatingProfitSchema;
  }

  public void setOperatingProfitSchema(List<@Valid OperatingProfitSchema> operatingProfitSchema) {
    this.operatingProfitSchema = operatingProfitSchema;
  }

  public ApiYearlyBudgetActualGet200Response largePLLinkedItemSchema(List<@Valid LargePLLinkedItemSchema> largePLLinkedItemSchema) {
    this.largePLLinkedItemSchema = largePLLinkedItemSchema;
    return this;
  }

  public ApiYearlyBudgetActualGet200Response addLargePLLinkedItemSchemaItem(LargePLLinkedItemSchema largePLLinkedItemSchemaItem) {
    if (this.largePLLinkedItemSchema == null) {
      this.largePLLinkedItemSchema = new ArrayList<>();
    }
    this.largePLLinkedItemSchema.add(largePLLinkedItemSchemaItem);
    return this;
  }

  /**
   * Get largePLLinkedItemSchema
   * @return largePLLinkedItemSchema
  */
  @Valid 
  @Schema(name = "largePLLinkedItemSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("largePLLinkedItemSchema")
  public List<@Valid LargePLLinkedItemSchema> getLargePLLinkedItemSchema() {
    return largePLLinkedItemSchema;
  }

  public void setLargePLLinkedItemSchema(List<@Valid LargePLLinkedItemSchema> largePLLinkedItemSchema) {
    this.largePLLinkedItemSchema = largePLLinkedItemSchema;
  }

  public ApiYearlyBudgetActualGet200Response middlePLLinkedItemSchema(List<@Valid MiddlePLLinkedItemSchema> middlePLLinkedItemSchema) {
    this.middlePLLinkedItemSchema = middlePLLinkedItemSchema;
    return this;
  }

  public ApiYearlyBudgetActualGet200Response addMiddlePLLinkedItemSchemaItem(MiddlePLLinkedItemSchema middlePLLinkedItemSchemaItem) {
    if (this.middlePLLinkedItemSchema == null) {
      this.middlePLLinkedItemSchema = new ArrayList<>();
    }
    this.middlePLLinkedItemSchema.add(middlePLLinkedItemSchemaItem);
    return this;
  }

  /**
   * Get middlePLLinkedItemSchema
   * @return middlePLLinkedItemSchema
  */
  @Valid 
  @Schema(name = "middlePLLinkedItemSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("middlePLLinkedItemSchema")
  public List<@Valid MiddlePLLinkedItemSchema> getMiddlePLLinkedItemSchema() {
    return middlePLLinkedItemSchema;
  }

  public void setMiddlePLLinkedItemSchema(List<@Valid MiddlePLLinkedItemSchema> middlePLLinkedItemSchema) {
    this.middlePLLinkedItemSchema = middlePLLinkedItemSchema;
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
        Objects.equals(this.saleSchema, apiYearlyBudgetActualGet200Response.saleSchema) &&
        Objects.equals(this.grossProfitSchema, apiYearlyBudgetActualGet200Response.grossProfitSchema) &&
        Objects.equals(this.operatingProfitSchema, apiYearlyBudgetActualGet200Response.operatingProfitSchema) &&
        Objects.equals(this.largePLLinkedItemSchema, apiYearlyBudgetActualGet200Response.largePLLinkedItemSchema) &&
        Objects.equals(this.middlePLLinkedItemSchema, apiYearlyBudgetActualGet200Response.middlePLLinkedItemSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, saleSchema, grossProfitSchema, operatingProfitSchema, largePLLinkedItemSchema, middlePLLinkedItemSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiYearlyBudgetActualGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    saleSchema: ").append(toIndentedString(saleSchema)).append("\n");
    sb.append("    grossProfitSchema: ").append(toIndentedString(grossProfitSchema)).append("\n");
    sb.append("    operatingProfitSchema: ").append(toIndentedString(operatingProfitSchema)).append("\n");
    sb.append("    largePLLinkedItemSchema: ").append(toIndentedString(largePLLinkedItemSchema)).append("\n");
    sb.append("    middlePLLinkedItemSchema: ").append(toIndentedString(middlePLLinkedItemSchema)).append("\n");
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

