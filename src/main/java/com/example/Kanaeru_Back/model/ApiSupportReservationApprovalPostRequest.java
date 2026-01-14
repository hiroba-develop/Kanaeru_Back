package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
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
 * ApiSupportReservationApprovalPostRequest
 */

@JsonTypeName("_api_support_reservation_approval_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportReservationApprovalPostRequest {

  private String reservationId;

  private String userId;

  public ApiSupportReservationApprovalPostRequest reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

  /**
   * Get reservationId
   * @return reservationId
  */
  
  @Schema(name = "reservationId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reservationId")
  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public ApiSupportReservationApprovalPostRequest userId(String userId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportReservationApprovalPostRequest apiSupportReservationApprovalPostRequest = (ApiSupportReservationApprovalPostRequest) o;
    return Objects.equals(this.reservationId, apiSupportReservationApprovalPostRequest.reservationId) &&
        Objects.equals(this.userId, apiSupportReservationApprovalPostRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportReservationApprovalPostRequest {\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

