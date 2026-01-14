package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ReservationSchema;
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
 * ApiSupportReservationGet200Response
 */

@JsonTypeName("_api_support_reservation_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportReservationGet200Response {

  private Integer responseStatus;

  private String googleStatus;

  private ReservationSchema reservationSchema;

  public ApiSupportReservationGet200Response responseStatus(Integer responseStatus) {
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

  public ApiSupportReservationGet200Response googleStatus(String googleStatus) {
    this.googleStatus = googleStatus;
    return this;
  }

  /**
   * Get googleStatus
   * @return googleStatus
  */
  
  @Schema(name = "googleStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("googleStatus")
  public String getGoogleStatus() {
    return googleStatus;
  }

  public void setGoogleStatus(String googleStatus) {
    this.googleStatus = googleStatus;
  }

  public ApiSupportReservationGet200Response reservationSchema(ReservationSchema reservationSchema) {
    this.reservationSchema = reservationSchema;
    return this;
  }

  /**
   * Get reservationSchema
   * @return reservationSchema
  */
  @Valid 
  @Schema(name = "reservationSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reservationSchema")
  public ReservationSchema getReservationSchema() {
    return reservationSchema;
  }

  public void setReservationSchema(ReservationSchema reservationSchema) {
    this.reservationSchema = reservationSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportReservationGet200Response apiSupportReservationGet200Response = (ApiSupportReservationGet200Response) o;
    return Objects.equals(this.responseStatus, apiSupportReservationGet200Response.responseStatus) &&
        Objects.equals(this.googleStatus, apiSupportReservationGet200Response.googleStatus) &&
        Objects.equals(this.reservationSchema, apiSupportReservationGet200Response.reservationSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, googleStatus, reservationSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportReservationGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    googleStatus: ").append(toIndentedString(googleStatus)).append("\n");
    sb.append("    reservationSchema: ").append(toIndentedString(reservationSchema)).append("\n");
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

