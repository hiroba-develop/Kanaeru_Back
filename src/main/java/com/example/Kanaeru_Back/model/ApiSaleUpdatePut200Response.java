package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiSaleUpdatePut200Response
 */

@JsonTypeName("_api_sale_update_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSaleUpdatePut200Response {

  private Integer responseStatus;

  private SaleSchema saleSchema;

  public ApiSaleUpdatePut200Response responseStatus(Integer responseStatus) {
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

  public ApiSaleUpdatePut200Response saleSchema(SaleSchema saleSchema) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSaleUpdatePut200Response apiSaleUpdatePut200Response = (ApiSaleUpdatePut200Response) o;
    return Objects.equals(this.responseStatus, apiSaleUpdatePut200Response.responseStatus) &&
        Objects.equals(this.saleSchema, apiSaleUpdatePut200Response.saleSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, saleSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSaleUpdatePut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    saleSchema: ").append(toIndentedString(saleSchema)).append("\n");
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

