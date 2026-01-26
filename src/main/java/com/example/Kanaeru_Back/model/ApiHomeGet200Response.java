package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.model.LargeGoalSchema;
import com.example.Kanaeru_Back.model.MainGoalSchema;
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
 * ApiHomeGet200Response
 */

@JsonTypeName("_api_home_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiHomeGet200Response {

  private Integer responseStatus;

  private MainGoalSchema mainGoalSchema;

  @Valid
  private List<@Valid LargeGoalSchema> largeGoalSchema;

  private SaleSchema saleSchema;

  private GrossProfitSchema grossProfitSchema;

  private OperatingProfitSchema operatingProfitSchema;

  public ApiHomeGet200Response responseStatus(Integer responseStatus) {
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

  public ApiHomeGet200Response mainGoalSchema(MainGoalSchema mainGoalSchema) {
    this.mainGoalSchema = mainGoalSchema;
    return this;
  }

  /**
   * Get mainGoalSchema
   * @return mainGoalSchema
  */
  @Valid 
  @Schema(name = "mainGoalSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mainGoalSchema")
  public MainGoalSchema getMainGoalSchema() {
    return mainGoalSchema;
  }

  public void setMainGoalSchema(MainGoalSchema mainGoalSchema) {
    this.mainGoalSchema = mainGoalSchema;
  }

  public ApiHomeGet200Response largeGoalSchema(List<@Valid LargeGoalSchema> largeGoalSchema) {
    this.largeGoalSchema = largeGoalSchema;
    return this;
  }

  public ApiHomeGet200Response addLargeGoalSchemaItem(LargeGoalSchema largeGoalSchemaItem) {
    if (this.largeGoalSchema == null) {
      this.largeGoalSchema = new ArrayList<>();
    }
    this.largeGoalSchema.add(largeGoalSchemaItem);
    return this;
  }

  /**
   * Get largeGoalSchema
   * @return largeGoalSchema
  */
  @Valid 
  @Schema(name = "largeGoalSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("largeGoalSchema")
  public List<@Valid LargeGoalSchema> getLargeGoalSchema() {
    return largeGoalSchema;
  }

  public void setLargeGoalSchema(List<@Valid LargeGoalSchema> largeGoalSchema) {
    this.largeGoalSchema = largeGoalSchema;
  }

  public ApiHomeGet200Response saleSchema(SaleSchema saleSchema) {
    this.saleSchema = saleSchema;
    return this;
  }

  /**
   * Get saleSchema
   * @return saleSchema
  */
  @Valid 
  @Schema(name = "saleSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("saleSchema")
  public SaleSchema getSaleSchema() {
    return saleSchema;
  }

  public void setSaleSchema(SaleSchema saleSchema) {
    this.saleSchema = saleSchema;
  }

  public ApiHomeGet200Response grossProfitSchema(GrossProfitSchema grossProfitSchema) {
    this.grossProfitSchema = grossProfitSchema;
    return this;
  }

  /**
   * Get grossProfitSchema
   * @return grossProfitSchema
  */
  @Valid 
  @Schema(name = "grossProfitSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("grossProfitSchema")
  public GrossProfitSchema getGrossProfitSchema() {
    return grossProfitSchema;
  }

  public void setGrossProfitSchema(GrossProfitSchema grossProfitSchema) {
    this.grossProfitSchema = grossProfitSchema;
  }

  public ApiHomeGet200Response operatingProfitSchema(OperatingProfitSchema operatingProfitSchema) {
    this.operatingProfitSchema = operatingProfitSchema;
    return this;
  }

  /**
   * Get operatingProfitSchema
   * @return operatingProfitSchema
  */
  @Valid 
  @Schema(name = "operatingProfitSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operatingProfitSchema")
  public OperatingProfitSchema getOperatingProfitSchema() {
    return operatingProfitSchema;
  }

  public void setOperatingProfitSchema(OperatingProfitSchema operatingProfitSchema) {
    this.operatingProfitSchema = operatingProfitSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiHomeGet200Response apiHomeGet200Response = (ApiHomeGet200Response) o;
    return Objects.equals(this.responseStatus, apiHomeGet200Response.responseStatus) &&
        Objects.equals(this.mainGoalSchema, apiHomeGet200Response.mainGoalSchema) &&
        Objects.equals(this.largeGoalSchema, apiHomeGet200Response.largeGoalSchema) &&
        Objects.equals(this.saleSchema, apiHomeGet200Response.saleSchema) &&
        Objects.equals(this.grossProfitSchema, apiHomeGet200Response.grossProfitSchema) &&
        Objects.equals(this.operatingProfitSchema, apiHomeGet200Response.operatingProfitSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, mainGoalSchema, largeGoalSchema, saleSchema, grossProfitSchema, operatingProfitSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiHomeGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    mainGoalSchema: ").append(toIndentedString(mainGoalSchema)).append("\n");
    sb.append("    largeGoalSchema: ").append(toIndentedString(largeGoalSchema)).append("\n");
    sb.append("    saleSchema: ").append(toIndentedString(saleSchema)).append("\n");
    sb.append("    grossProfitSchema: ").append(toIndentedString(grossProfitSchema)).append("\n");
    sb.append("    operatingProfitSchema: ").append(toIndentedString(operatingProfitSchema)).append("\n");
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

