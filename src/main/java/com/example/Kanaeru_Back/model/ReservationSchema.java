package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ReservationSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReservationSchema {

  private String reservationId;

  private String userId;

  private String userName;

  private String selectedUserId;

  private String selectedUserName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endAt;

  private String content;

  private Integer approvalFlg;

  private String calendarId;

  private String calendarEventId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;

  public ReservationSchema reservationId(String reservationId) {
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

  public ReservationSchema userId(String userId) {
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

  public ReservationSchema userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  */
  
  @Schema(name = "userName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userName")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ReservationSchema selectedUserId(String selectedUserId) {
    this.selectedUserId = selectedUserId;
    return this;
  }

  /**
   * Get selectedUserId
   * @return selectedUserId
  */
  
  @Schema(name = "selectedUserId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("selectedUserId")
  public String getSelectedUserId() {
    return selectedUserId;
  }

  public void setSelectedUserId(String selectedUserId) {
    this.selectedUserId = selectedUserId;
  }

  public ReservationSchema selectedUserName(String selectedUserName) {
    this.selectedUserName = selectedUserName;
    return this;
  }

  /**
   * Get selectedUserName
   * @return selectedUserName
  */
  
  @Schema(name = "selectedUserName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("selectedUserName")
  public String getSelectedUserName() {
    return selectedUserName;
  }

  public void setSelectedUserName(String selectedUserName) {
    this.selectedUserName = selectedUserName;
  }

  public ReservationSchema startAt(LocalDateTime startAt) {
    this.startAt = startAt;
    return this;
  }

  /**
   * Get startAt
   * @return startAt
  */
  @Valid 
  @Schema(name = "startAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startAt")
  public LocalDateTime getStartAt() {
    return startAt;
  }

  public void setStartAt(LocalDateTime startAt) {
    this.startAt = startAt;
  }

  public ReservationSchema endAt(LocalDateTime endAt) {
    this.endAt = endAt;
    return this;
  }

  /**
   * Get endAt
   * @return endAt
  */
  @Valid 
  @Schema(name = "endAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endAt")
  public LocalDateTime getEndAt() {
    return endAt;
  }

  public void setEndAt(LocalDateTime endAt) {
    this.endAt = endAt;
  }

  public ReservationSchema content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  
  @Schema(name = "content", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ReservationSchema approvalFlg(Integer approvalFlg) {
    this.approvalFlg = approvalFlg;
    return this;
  }

  /**
   * Get approvalFlg
   * @return approvalFlg
  */
  
  @Schema(name = "approvalFlg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvalFlg")
  public Integer getApprovalFlg() {
    return approvalFlg;
  }

  public void setApprovalFlg(Integer approvalFlg) {
    this.approvalFlg = approvalFlg;
  }

  public ReservationSchema calendarId(String calendarId) {
    this.calendarId = calendarId;
    return this;
  }

  /**
   * Get calendarId
   * @return calendarId
  */
  
  @Schema(name = "calendarId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calendarId")
  public String getCalendarId() {
    return calendarId;
  }

  public void setCalendarId(String calendarId) {
    this.calendarId = calendarId;
  }

  public ReservationSchema calendarEventId(String calendarEventId) {
    this.calendarEventId = calendarEventId;
    return this;
  }

  /**
   * Get calendarEventId
   * @return calendarEventId
  */
  
  @Schema(name = "calendarEventId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calendarEventId")
  public String getCalendarEventId() {
    return calendarEventId;
  }

  public void setCalendarEventId(String calendarEventId) {
    this.calendarEventId = calendarEventId;
  }

  public ReservationSchema createdAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ReservationSchema updatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @Valid 
  @Schema(name = "updatedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationSchema reservationSchema = (ReservationSchema) o;
    return Objects.equals(this.reservationId, reservationSchema.reservationId) &&
        Objects.equals(this.userId, reservationSchema.userId) &&
        Objects.equals(this.userName, reservationSchema.userName) &&
        Objects.equals(this.selectedUserId, reservationSchema.selectedUserId) &&
        Objects.equals(this.selectedUserName, reservationSchema.selectedUserName) &&
        Objects.equals(this.startAt, reservationSchema.startAt) &&
        Objects.equals(this.endAt, reservationSchema.endAt) &&
        Objects.equals(this.content, reservationSchema.content) &&
        Objects.equals(this.approvalFlg, reservationSchema.approvalFlg) &&
        Objects.equals(this.calendarId, reservationSchema.calendarId) &&
        Objects.equals(this.calendarEventId, reservationSchema.calendarEventId) &&
        Objects.equals(this.createdAt, reservationSchema.createdAt) &&
        Objects.equals(this.updatedAt, reservationSchema.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId, userId, userName, selectedUserId, selectedUserName, startAt, endAt, content, approvalFlg, calendarId, calendarEventId, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationSchema {\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    selectedUserId: ").append(toIndentedString(selectedUserId)).append("\n");
    sb.append("    selectedUserName: ").append(toIndentedString(selectedUserName)).append("\n");
    sb.append("    startAt: ").append(toIndentedString(startAt)).append("\n");
    sb.append("    endAt: ").append(toIndentedString(endAt)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    approvalFlg: ").append(toIndentedString(approvalFlg)).append("\n");
    sb.append("    calendarId: ").append(toIndentedString(calendarId)).append("\n");
    sb.append("    calendarEventId: ").append(toIndentedString(calendarEventId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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

