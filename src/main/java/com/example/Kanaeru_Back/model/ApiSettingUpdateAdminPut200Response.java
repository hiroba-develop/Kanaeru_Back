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
 * ApiSettingUpdateAdminPut200Response
 */

@JsonTypeName("_api_setting_update_admin_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSettingUpdateAdminPut200Response {

  private Integer responseStatus;

  private UserSchema userSchema;

  public ApiSettingUpdateAdminPut200Response responseStatus(Integer responseStatus) {
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

  public ApiSettingUpdateAdminPut200Response userSchema(UserSchema userSchema) {
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
    ApiSettingUpdateAdminPut200Response apiSettingUpdateAdminPut200Response = (ApiSettingUpdateAdminPut200Response) o;
    return Objects.equals(this.responseStatus, apiSettingUpdateAdminPut200Response.responseStatus) &&
        Objects.equals(this.userSchema, apiSettingUpdateAdminPut200Response.userSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSettingUpdateAdminPut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
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

