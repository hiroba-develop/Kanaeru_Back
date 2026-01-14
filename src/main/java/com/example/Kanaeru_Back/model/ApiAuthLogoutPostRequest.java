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
 * ApiAuthLogoutPostRequest
 */

@JsonTypeName("_api_auth_logout_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthLogoutPostRequest {

  private String token;

  public ApiAuthLogoutPostRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ApiAuthLogoutPostRequest(String token) {
    this.token = token;
  }

  public ApiAuthLogoutPostRequest token(String token) {
    this.token = token;
    return this;
  }

  /**
   * 認証トークン（JWT）
   * @return token
  */
  @NotNull 
  @Schema(name = "token", description = "認証トークン（JWT）", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAuthLogoutPostRequest apiAuthLogoutPostRequest = (ApiAuthLogoutPostRequest) o;
    return Objects.equals(this.token, apiAuthLogoutPostRequest.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthLogoutPostRequest {\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

