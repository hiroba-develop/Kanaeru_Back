package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.UserListSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiGetUsersGet200Response
 */

@JsonTypeName("_api_get_users_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiGetUsersGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid UserListSchema> userListSchema;

  @Valid
  private List<@Valid SettingSchema> settingListSchema;

  public ApiGetUsersGet200Response responseStatus(Integer responseStatus) {
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

  public ApiGetUsersGet200Response userListSchema(List<@Valid UserListSchema> userListSchema) {
    this.userListSchema = userListSchema;
    return this;
  }

  public ApiGetUsersGet200Response addUserListSchemaItem(UserListSchema userListSchemaItem) {
    if (this.userListSchema == null) {
      this.userListSchema = new ArrayList<>();
    }
    this.userListSchema.add(userListSchemaItem);
    return this;
  }

  /**
   * Get userListSchema
   * @return userListSchema
  */
  @Valid 
  @Schema(name = "userListSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userListSchema")
  public List<@Valid UserListSchema> getUserListSchema() {
    return userListSchema;
  }

  public void setUserListSchema(List<@Valid UserListSchema> userListSchema) {
    this.userListSchema = userListSchema;
  }

  public ApiGetUsersGet200Response settingListSchema(List<@Valid SettingSchema> settingListSchema) {
    this.settingListSchema = settingListSchema;
    return this;
  }

  public ApiGetUsersGet200Response addSettingListSchemaItem(SettingSchema settingListSchemaItem) {
    if (this.settingListSchema == null) {
      this.settingListSchema = new ArrayList<>();
    }
    this.settingListSchema.add(settingListSchemaItem);
    return this;
  }

  /**
   * Get settingListSchema
   * @return settingListSchema
  */
  @Valid 
  @Schema(name = "settingListSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settingListSchema")
  public List<@Valid SettingSchema> getSettingListSchema() {
    return settingListSchema;
  }

  public void setSettingListSchema(List<@Valid SettingSchema> settingListSchema) {
    this.settingListSchema = settingListSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiGetUsersGet200Response apiGetUsersGet200Response = (ApiGetUsersGet200Response) o;
    return Objects.equals(this.responseStatus, apiGetUsersGet200Response.responseStatus) &&
        Objects.equals(this.userListSchema, apiGetUsersGet200Response.userListSchema) &&
        Objects.equals(this.settingListSchema, apiGetUsersGet200Response.settingListSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userListSchema, settingListSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiGetUsersGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    userListSchema: ").append(toIndentedString(userListSchema)).append("\n");
    sb.append("    settingListSchema: ").append(toIndentedString(settingListSchema)).append("\n");
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

