package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
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
 * ApiGrossProfitUpdatePut200Response
 */

@JsonTypeName("_api_grossProfit_update_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiGrossProfitUpdatePut200Response {

  private Integer responseStatus;

  private GrossProfitSchema grossProfitSchema;

  public ApiGrossProfitUpdatePut200Response responseStatus(Integer responseStatus) {
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

  public ApiGrossProfitUpdatePut200Response grossProfitSchema(GrossProfitSchema grossProfitSchema) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiGrossProfitUpdatePut200Response apiGrossProfitUpdatePut200Response = (ApiGrossProfitUpdatePut200Response) o;
    return Objects.equals(this.responseStatus, apiGrossProfitUpdatePut200Response.responseStatus) &&
        Objects.equals(this.grossProfitSchema, apiGrossProfitUpdatePut200Response.grossProfitSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, grossProfitSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiGrossProfitUpdatePut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    grossProfitSchema: ").append(toIndentedString(grossProfitSchema)).append("\n");
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

