package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.AdviceSchema;
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
 * ApiSupportAdviceGet200Response
 */

@JsonTypeName("_api_support_advice_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportAdviceGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid AdviceSchema> adviceSchema;

  public ApiSupportAdviceGet200Response responseStatus(Integer responseStatus) {
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

  public ApiSupportAdviceGet200Response adviceSchema(List<@Valid AdviceSchema> adviceSchema) {
    this.adviceSchema = adviceSchema;
    return this;
  }

  public ApiSupportAdviceGet200Response addAdviceSchemaItem(AdviceSchema adviceSchemaItem) {
    if (this.adviceSchema == null) {
      this.adviceSchema = new ArrayList<>();
    }
    this.adviceSchema.add(adviceSchemaItem);
    return this;
  }

  /**
   * Get adviceSchema
   * @return adviceSchema
  */
  @Valid 
  @Schema(name = "adviceSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceSchema")
  public List<@Valid AdviceSchema> getAdviceSchema() {
    return adviceSchema;
  }

  public void setAdviceSchema(List<@Valid AdviceSchema> adviceSchema) {
    this.adviceSchema = adviceSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportAdviceGet200Response apiSupportAdviceGet200Response = (ApiSupportAdviceGet200Response) o;
    return Objects.equals(this.responseStatus, apiSupportAdviceGet200Response.responseStatus) &&
        Objects.equals(this.adviceSchema, apiSupportAdviceGet200Response.adviceSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, adviceSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportAdviceGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    adviceSchema: ").append(toIndentedString(adviceSchema)).append("\n");
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

