package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.AvailabilitySchema;
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
 * ApiAvailabilityGet200Response
 */

@JsonTypeName("_api_availability_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiAvailabilityGet200Response {

  private Integer responseStatus;

  private String userId;

  private AvailabilitySchema availabilitySchema;

  public ApiAvailabilityGet200Response responseStatus(Integer responseStatus) {
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

  public ApiAvailabilityGet200Response userId(String userId) {
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

  public ApiAvailabilityGet200Response availabilitySchema(AvailabilitySchema availabilitySchema) {
    this.availabilitySchema = availabilitySchema;
    return this;
  }

  /**
   * Get availabilitySchema
   * @return availabilitySchema
  */
  @Valid 
  @Schema(name = "availabilitySchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("availabilitySchema")
  public AvailabilitySchema getAvailabilitySchema() {
    return availabilitySchema;
  }

  public void setAvailabilitySchema(AvailabilitySchema availabilitySchema) {
    this.availabilitySchema = availabilitySchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAvailabilityGet200Response apiAvailabilityGet200Response = (ApiAvailabilityGet200Response) o;
    return Objects.equals(this.responseStatus, apiAvailabilityGet200Response.responseStatus) &&
        Objects.equals(this.userId, apiAvailabilityGet200Response.userId) &&
        Objects.equals(this.availabilitySchema, apiAvailabilityGet200Response.availabilitySchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, userId, availabilitySchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAvailabilityGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    availabilitySchema: ").append(toIndentedString(availabilitySchema)).append("\n");
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

