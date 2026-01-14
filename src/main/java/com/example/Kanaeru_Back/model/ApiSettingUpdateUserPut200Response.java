package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.SettingSchema;
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
 * ApiSettingUpdateUserPut200Response
 */

@JsonTypeName("_api_setting_update_user_put_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSettingUpdateUserPut200Response {

  private Integer responseStatus;

  private UserSchema userSchema;

  private SettingSchema settingSchema;

  public ApiSettingUpdateUserPut200Response responseStatus(Integer responseStatus) {
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

  public ApiSettingUpdateUserPut200Response userSchema(UserSchema userSchema) {
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

  public ApiSettingUpdateUserPut200Response settingSchema(SettingSchema settingSchema) {
    this.settingSchema = settingSchema;
    return this;
  }

  /**
   * Get settingSchema
   * @return settingSchema
  */
  @Valid 
  @Schema(name = "settingSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settingSchema")
  public SettingSchema getSettingSchema() {
    return settingSchema;
  }

  public void setSettingSchema(SettingSchema settingSchema) {
    this.settingSchema = settingSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSettingUpdateUserPut200Response apiSettingUpdateUserPut200Response = (ApiSettingUpdateUserPut200Response) o;
    return Objects.equals(this.responseStatus, apiSettingUpdateUserPut200Response.responseStatus) &&
        Objects.equals(this.userSchema, apiSettingUpdateUserPut200Response.userSchema) &&
        Objects.equals(this.settingSchema, apiSettingUpdateUserPut200Response.settingSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userSchema, settingSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSettingUpdateUserPut200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    userSchema: ").append(toIndentedString(userSchema)).append("\n");
    sb.append("    settingSchema: ").append(toIndentedString(settingSchema)).append("\n");
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

