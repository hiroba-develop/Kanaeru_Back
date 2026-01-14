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
 * ApiAuthResetPasswordPostRequest
 */

@JsonTypeName("_api_auth_resetPassword_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthResetPasswordPostRequest {

  private String token;

  private String newPasswordHash;

  public ApiAuthResetPasswordPostRequest token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  
  @Schema(name = "token", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public ApiAuthResetPasswordPostRequest newPasswordHash(String newPasswordHash) {
    this.newPasswordHash = newPasswordHash;
    return this;
  }

  /**
   * Get newPasswordHash
   * @return newPasswordHash
  */
  
  @Schema(name = "newPasswordHash", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("newPasswordHash")
  public String getNewPasswordHash() {
    return newPasswordHash;
  }

  public void setNewPasswordHash(String newPasswordHash) {
    this.newPasswordHash = newPasswordHash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAuthResetPasswordPostRequest apiAuthResetPasswordPostRequest = (ApiAuthResetPasswordPostRequest) o;
    return Objects.equals(this.token, apiAuthResetPasswordPostRequest.token) &&
        Objects.equals(this.newPasswordHash, apiAuthResetPasswordPostRequest.newPasswordHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, newPasswordHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthResetPasswordPostRequest {\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    newPasswordHash: ").append(toIndentedString(newPasswordHash)).append("\n");
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

