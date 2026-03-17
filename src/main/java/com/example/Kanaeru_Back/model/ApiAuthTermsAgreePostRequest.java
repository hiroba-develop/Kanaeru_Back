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
 * ApiAuthTermsAgreePostRequest
 */

@JsonTypeName("_api_auth_termsAgree_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthTermsAgreePostRequest {

  private String userId;

  private String termsAgreedAt;

  public ApiAuthTermsAgreePostRequest userId(String userId) {
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

  public ApiAuthTermsAgreePostRequest termsAgreedAt(String termsAgreedAt) {
    this.termsAgreedAt = termsAgreedAt;
    return this;
  }

  /**
   * Get termsAgreedAt
   * @return termsAgreedAt
  */
  
  @Schema(name = "termsAgreedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("termsAgreedAt")
  public String getTermsAgreedAt() {
    return termsAgreedAt;
  }

  public void setTermsAgreedAt(String termsAgreedAt) {
    this.termsAgreedAt = termsAgreedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAuthTermsAgreePostRequest apiAuthTermsAgreePostRequest = (ApiAuthTermsAgreePostRequest) o;
    return Objects.equals(this.userId, apiAuthTermsAgreePostRequest.userId) &&
        Objects.equals(this.termsAgreedAt, apiAuthTermsAgreePostRequest.termsAgreedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, termsAgreedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthTermsAgreePostRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    termsAgreedAt: ").append(toIndentedString(termsAgreedAt)).append("\n");
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

