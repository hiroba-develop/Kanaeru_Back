package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.NetAssetsSchema;
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
 * ApiNetAssetUpdatePut200Response
 */

@JsonTypeName("_api_netAsset_update_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiNetAssetUpdatePut200Response {

  private Integer responseStatus;

  private NetAssetsSchema netAssetsSchema;

  public ApiNetAssetUpdatePut200Response responseStatus(Integer responseStatus) {
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

  public ApiNetAssetUpdatePut200Response netAssetsSchema(NetAssetsSchema netAssetsSchema) {
    this.netAssetsSchema = netAssetsSchema;
    return this;
  }

  /**
   * Get netAssetsSchema
   * @return netAssetsSchema
  */
  @Valid 
  @Schema(name = "netAssetsSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netAssetsSchema")
  public NetAssetsSchema getNetAssetsSchema() {
    return netAssetsSchema;
  }

  public void setNetAssetsSchema(NetAssetsSchema netAssetsSchema) {
    this.netAssetsSchema = netAssetsSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiNetAssetUpdatePut200Response apiNetAssetUpdatePut200Response = (ApiNetAssetUpdatePut200Response) o;
    return Objects.equals(this.responseStatus, apiNetAssetUpdatePut200Response.responseStatus) &&
        Objects.equals(this.netAssetsSchema, apiNetAssetUpdatePut200Response.netAssetsSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, netAssetsSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiNetAssetUpdatePut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    netAssetsSchema: ").append(toIndentedString(netAssetsSchema)).append("\n");
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

