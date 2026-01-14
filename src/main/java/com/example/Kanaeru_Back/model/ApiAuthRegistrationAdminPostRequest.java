package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.UserSchema;
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
 * ApiAuthRegistrationAdminPostRequest
 */

@JsonTypeName("_api_auth_registration_admin_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAuthRegistrationAdminPostRequest {

  private UserSchema userSchema;

  public ApiAuthRegistrationAdminPostRequest userSchema(UserSchema userSchema) {
    this.userSchema = userSchema;
    return this;
  }

  /**
   * Get userSchema
   * @return userSchema
  */
  @Valid 
  @Schema(name = "userSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userSchema")
  public UserSchema getUserSchema() {
    return userSchema;
  }

  public void setUserSchema(UserSchema userSchema) {
    this.userSchema = userSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAuthRegistrationAdminPostRequest apiAuthRegistrationAdminPostRequest = (ApiAuthRegistrationAdminPostRequest) o;
    return Objects.equals(this.userSchema, apiAuthRegistrationAdminPostRequest.userSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAuthRegistrationAdminPostRequest {\n");
    sb.append("    userSchema: ").append(toIndentedString(userSchema)).append("\n");
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

