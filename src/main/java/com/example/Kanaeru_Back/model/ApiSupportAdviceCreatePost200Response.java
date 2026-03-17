package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
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
 * ApiSupportAdviceCreatePost200Response
 */

@JsonTypeName("_api_support_advice_create_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportAdviceCreatePost200Response {

  private String adviceId;

  private Integer responseStatus;

  public ApiSupportAdviceCreatePost200Response adviceId(String adviceId) {
    this.adviceId = adviceId;
    return this;
  }

  /**
   * Get adviceId
   * @return adviceId
  */
  
  @Schema(name = "adviceId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceId")
  public String getAdviceId() {
    return adviceId;
  }

  public void setAdviceId(String adviceId) {
    this.adviceId = adviceId;
  }

  public ApiSupportAdviceCreatePost200Response responseStatus(Integer responseStatus) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportAdviceCreatePost200Response apiSupportAdviceCreatePost200Response = (ApiSupportAdviceCreatePost200Response) o;
    return Objects.equals(this.adviceId, apiSupportAdviceCreatePost200Response.adviceId) &&
        Objects.equals(this.responseStatus, apiSupportAdviceCreatePost200Response.responseStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adviceId, responseStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportAdviceCreatePost200Response {\n");
    sb.append("    adviceId: ").append(toIndentedString(adviceId)).append("\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
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

