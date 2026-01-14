package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.AdminCommentSchema;
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
 * ApiHomeGet200Response
 */

@JsonTypeName("_api_home_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiHomeGet200Response {

  private Integer responseStatus;

  private AdminCommentSchema adminCommentSchema;

  private Object quarterlyGoalSchema;

  private Object quarterlyGoalTaskSchema;

  public ApiHomeGet200Response responseStatus(Integer responseStatus) {
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

  public ApiHomeGet200Response adminCommentSchema(AdminCommentSchema adminCommentSchema) {
    this.adminCommentSchema = adminCommentSchema;
    return this;
  }

  /**
   * Get adminCommentSchema
   * @return adminCommentSchema
  */
  @Valid 
  @Schema(name = "adminCommentSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adminCommentSchema")
  public AdminCommentSchema getAdminCommentSchema() {
    return adminCommentSchema;
  }

  public void setAdminCommentSchema(AdminCommentSchema adminCommentSchema) {
    this.adminCommentSchema = adminCommentSchema;
  }

  public ApiHomeGet200Response quarterlyGoalSchema(Object quarterlyGoalSchema) {
    this.quarterlyGoalSchema = quarterlyGoalSchema;
    return this;
  }

  /**
   * Get quarterlyGoalSchema
   * @return quarterlyGoalSchema
  */
  
  @Schema(name = "quarterlyGoalSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("quarterlyGoalSchema")
  public Object getQuarterlyGoalSchema() {
    return quarterlyGoalSchema;
  }

  public void setQuarterlyGoalSchema(Object quarterlyGoalSchema) {
    this.quarterlyGoalSchema = quarterlyGoalSchema;
  }

  public ApiHomeGet200Response quarterlyGoalTaskSchema(Object quarterlyGoalTaskSchema) {
    this.quarterlyGoalTaskSchema = quarterlyGoalTaskSchema;
    return this;
  }

  /**
   * Get quarterlyGoalTaskSchema
   * @return quarterlyGoalTaskSchema
  */
  
  @Schema(name = "quarterlyGoalTaskSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("quarterlyGoalTaskSchema")
  public Object getQuarterlyGoalTaskSchema() {
    return quarterlyGoalTaskSchema;
  }

  public void setQuarterlyGoalTaskSchema(Object quarterlyGoalTaskSchema) {
    this.quarterlyGoalTaskSchema = quarterlyGoalTaskSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiHomeGet200Response apiHomeGet200Response = (ApiHomeGet200Response) o;
    return Objects.equals(this.responseStatus, apiHomeGet200Response.responseStatus) &&
        Objects.equals(this.adminCommentSchema, apiHomeGet200Response.adminCommentSchema) &&
        Objects.equals(this.quarterlyGoalSchema, apiHomeGet200Response.quarterlyGoalSchema) &&
        Objects.equals(this.quarterlyGoalTaskSchema, apiHomeGet200Response.quarterlyGoalTaskSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, adminCommentSchema, quarterlyGoalSchema, quarterlyGoalTaskSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiHomeGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    adminCommentSchema: ").append(toIndentedString(adminCommentSchema)).append("\n");
    sb.append("    quarterlyGoalSchema: ").append(toIndentedString(quarterlyGoalSchema)).append("\n");
    sb.append("    quarterlyGoalTaskSchema: ").append(toIndentedString(quarterlyGoalTaskSchema)).append("\n");
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

