package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiSupportStreamGet200ResponseRead;
import com.example.Kanaeru_Back.model.ApiSupportStreamGet200ResponseValue;
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
 * ApiSupportStreamGet200Response
 */

@JsonTypeName("_api_support_stream_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportStreamGet200Response {

  private ApiSupportStreamGet200ResponseValue value;

  private ApiSupportStreamGet200ResponseRead read;

  public ApiSupportStreamGet200Response value(ApiSupportStreamGet200ResponseValue value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  @Valid 
  @Schema(name = "value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public ApiSupportStreamGet200ResponseValue getValue() {
    return value;
  }

  public void setValue(ApiSupportStreamGet200ResponseValue value) {
    this.value = value;
  }

  public ApiSupportStreamGet200Response read(ApiSupportStreamGet200ResponseRead read) {
    this.read = read;
    return this;
  }

  /**
   * Get read
   * @return read
  */
  @Valid 
  @Schema(name = "read", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("read")
  public ApiSupportStreamGet200ResponseRead getRead() {
    return read;
  }

  public void setRead(ApiSupportStreamGet200ResponseRead read) {
    this.read = read;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportStreamGet200Response apiSupportStreamGet200Response = (ApiSupportStreamGet200Response) o;
    return Objects.equals(this.value, apiSupportStreamGet200Response.value) &&
        Objects.equals(this.read, apiSupportStreamGet200Response.read);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, read);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportStreamGet200Response {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    read: ").append(toIndentedString(read)).append("\n");
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

