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
 * ApiSupportAdviceCreatePostRequest
 */

@JsonTypeName("_api_support_advice_create_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportAdviceCreatePostRequest {

  private String userId;

  private String adviceContent;

  public ApiSupportAdviceCreatePostRequest userId(String userId) {
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

  public ApiSupportAdviceCreatePostRequest adviceContent(String adviceContent) {
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
    ApiSupportAdviceCreatePostRequest apiSupportAdviceCreatePostRequest = (ApiSupportAdviceCreatePostRequest) o;
    return Objects.equals(this.userId, apiSupportAdviceCreatePostRequest.userId) &&
        Objects.equals(this.adviceContent, apiSupportAdviceCreatePostRequest.adviceContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, adviceContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportAdviceCreatePostRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

