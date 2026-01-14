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
 * ApiSupportReservationAllGet200Response
 */

@JsonTypeName("_api_support_reservation_all_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportReservationAllGet200Response {

  private Integer responseStatus;

  private ReservationSchema reservationSchema;

  public ApiSupportReservationAllGet200Response responseStatus(Integer responseStatus) {
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

  public ApiSupportReservationAllGet200Response reservationSchema(ReservationSchema reservationSchema) {
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
    ApiSupportReservationAllGet200Response apiSupportReservationAllGet200Response = (ApiSupportReservationAllGet200Response) o;
    return Objects.equals(this.responseStatus, apiSupportReservationAllGet200Response.responseStatus) &&
        Objects.equals(this.reservationSchema, apiSupportReservationAllGet200Response.reservationSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, reservationSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportReservationAllGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
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

