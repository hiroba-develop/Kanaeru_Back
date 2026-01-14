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
 * ApiAuthUpdatePasswordPutRequest
 */

@JsonTypeName("_api_auth_updatePassword_put_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthUpdatePasswordPutRequest {

  private String currentPasswordHash;

  private String newPasswordHash;

  public ApiAuthUpdatePasswordPutRequest currentPasswordHash(String currentPasswordHash) {
    this.currentPasswordHash = currentPasswordHash;
    return this;
  }

  /**
   * Get currentPasswordHash
   * @return currentPasswordHash
  */
  
  @Schema(name = "currentPasswordHash", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPasswordHash")
  public String getCurrentPasswordHash() {
    return currentPasswordHash;
  }

  public void setCurrentPasswordHash(String currentPasswordHash) {
    this.currentPasswordHash = currentPasswordHash;
  }

  public ApiAuthUpdatePasswordPutRequest newPasswordHash(String newPasswordHash) {
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
    ApiAuthUpdatePasswordPutRequest apiAuthUpdatePasswordPutRequest = (ApiAuthUpdatePasswordPutRequest) o;
    return Objects.equals(this.currentPasswordHash, apiAuthUpdatePasswordPutRequest.currentPasswordHash) &&
        Objects.equals(this.newPasswordHash, apiAuthUpdatePasswordPutRequest.newPasswordHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPasswordHash, newPasswordHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthUpdatePasswordPutRequest {\n");
    sb.append("    currentPasswordHash: ").append(toIndentedString(currentPasswordHash)).append("\n");
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

