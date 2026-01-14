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
 * ApiAuthLoginPost200Response
 */

@JsonTypeName("_api_auth_login_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthLoginPost200Response {

  private Integer responseStatus;

  private String userId;

  private String name;

  private String role;

  private String token;

  public ApiAuthLoginPost200Response responseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
    return this;
  }

  /**
   * 成功時は1、失敗時は0
   * @return responseStatus
  */
  
  @Schema(name = "responseStatus", description = "成功時は1、失敗時は0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("responseStatus")
  public Integer getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public ApiAuthLoginPost200Response userId(String userId) {
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

  public ApiAuthLoginPost200Response name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiAuthLoginPost200Response role(String role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  
  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public ApiAuthLoginPost200Response token(String token) {
    this.token = token;
    return this;
  }

  /**
   * JWT認証トークン
   * @return token
  */
  
  @Schema(name = "token", description = "JWT認証トークン", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    ApiAuthLoginPost200Response apiAuthLoginPost200Response = (ApiAuthLoginPost200Response) o;
    return Objects.equals(this.responseStatus, apiAuthLoginPost200Response.responseStatus) &&
        Objects.equals(this.userId, apiAuthLoginPost200Response.userId) &&
        Objects.equals(this.name, apiAuthLoginPost200Response.name) &&
        Objects.equals(this.role, apiAuthLoginPost200Response.role) &&
        Objects.equals(this.token, apiAuthLoginPost200Response.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userId, name, role, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthLoginPost200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

