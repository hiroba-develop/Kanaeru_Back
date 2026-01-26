package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
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
 * ApiGetAdminUsersGet200Response
 */

@JsonTypeName("_api_get_adminUsers_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiGetAdminUsersGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid UserListSchema> adminUserListSchema;

  public ApiGetAdminUsersGet200Response responseStatus(Integer responseStatus) {
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

  public ApiGetAdminUsersGet200Response adminUserListSchema(List<@Valid UserListSchema> adminUserListSchema) {
    this.adminUserListSchema = adminUserListSchema;
    return this;
  }

  public ApiGetAdminUsersGet200Response addAdminUserListSchemaItem(UserListSchema adminUserListSchemaItem) {
    if (this.adminUserListSchema == null) {
      this.adminUserListSchema = new ArrayList<>();
    }
    this.adminUserListSchema.add(adminUserListSchemaItem);
    return this;
  }

  /**
   * Get adminUserListSchema
   * @return adminUserListSchema
  */
  @Valid 
  @Schema(name = "adminUserListSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adminUserListSchema")
  public List<@Valid UserListSchema> getAdminUserListSchema() {
    return adminUserListSchema;
  }

  public void setAdminUserListSchema(List<@Valid UserListSchema> adminUserListSchema) {
    this.adminUserListSchema = adminUserListSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiGetAdminUsersGet200Response apiGetAdminUsersGet200Response = (ApiGetAdminUsersGet200Response) o;
    return Objects.equals(this.responseStatus, apiGetAdminUsersGet200Response.responseStatus) &&
        Objects.equals(this.adminUserListSchema, apiGetAdminUsersGet200Response.adminUserListSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, adminUserListSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiGetAdminUsersGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    adminUserListSchema: ").append(toIndentedString(adminUserListSchema)).append("\n");
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

