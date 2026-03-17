package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.SubscriptionSchema;
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
 * ApiSettingUserGet200Response
 */

@JsonTypeName("_api_setting_user_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSettingUserGet200Response {

  private Integer responseStatus;

  private UserSchema userSchema;

  private SettingSchema settingSchema;

  private SubscriptionSchema subscriptionSchema;

  public ApiSettingUserGet200Response responseStatus(Integer responseStatus) {
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

  public ApiSettingUserGet200Response userSchema(UserSchema userSchema) {
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

  public ApiSettingUserGet200Response settingSchema(SettingSchema settingSchema) {
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

  public ApiSettingUserGet200Response subscriptionSchema(SubscriptionSchema subscriptionSchema) {
    this.subscriptionSchema = subscriptionSchema;
    return this;
  }

  /**
   * Get subscriptionSchema
   * @return subscriptionSchema
  */
  @Valid 
  @Schema(name = "subscriptionSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subscriptionSchema")
  public SubscriptionSchema getSubscriptionSchema() {
    return subscriptionSchema;
  }

  public void setSubscriptionSchema(SubscriptionSchema subscriptionSchema) {
    this.subscriptionSchema = subscriptionSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSettingUserGet200Response apiSettingUserGet200Response = (ApiSettingUserGet200Response) o;
    return Objects.equals(this.responseStatus, apiSettingUserGet200Response.responseStatus) &&
        Objects.equals(this.userSchema, apiSettingUserGet200Response.userSchema) &&
        Objects.equals(this.settingSchema, apiSettingUserGet200Response.settingSchema) &&
        Objects.equals(this.subscriptionSchema, apiSettingUserGet200Response.subscriptionSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userSchema, settingSchema, subscriptionSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSettingUserGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    userSchema: ").append(toIndentedString(userSchema)).append("\n");
    sb.append("    settingSchema: ").append(toIndentedString(settingSchema)).append("\n");
    sb.append("    subscriptionSchema: ").append(toIndentedString(subscriptionSchema)).append("\n");
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

