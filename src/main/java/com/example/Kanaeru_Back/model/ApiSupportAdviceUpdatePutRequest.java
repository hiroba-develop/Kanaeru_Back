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
 * ApiSupportAdviceUpdatePutRequest
 */

@JsonTypeName("_api_support_advice_update_put_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportAdviceUpdatePutRequest {

  private String adviceId;

  private String adviceContent;

  public ApiSupportAdviceUpdatePutRequest adviceId(String adviceId) {
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

  public ApiSupportAdviceUpdatePutRequest adviceContent(String adviceContent) {
    this.adviceContent = adviceContent;
    return this;
  }

  /**
   * Get adviceContent
   * @return adviceContent
  */
  
  @Schema(name = "adviceContent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceContent")
  public String getAdviceContent() {
    return adviceContent;
  }

  public void setAdviceContent(String adviceContent) {
    this.adviceContent = adviceContent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportAdviceUpdatePutRequest apiSupportAdviceUpdatePutRequest = (ApiSupportAdviceUpdatePutRequest) o;
    return Objects.equals(this.adviceId, apiSupportAdviceUpdatePutRequest.adviceId) &&
        Objects.equals(this.adviceContent, apiSupportAdviceUpdatePutRequest.adviceContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adviceId, adviceContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportAdviceUpdatePutRequest {\n");
    sb.append("    adviceId: ").append(toIndentedString(adviceId)).append("\n");
    sb.append("    adviceContent: ").append(toIndentedString(adviceContent)).append("\n");
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

