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
 * AdviceSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdviceSchema {

  private String userId;

  private String adminId;

  private String adminName;

  private String adviceId;

  private String adviceContent;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;

  public AdviceSchema userId(String userId) {
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

  public AdviceSchema adminId(String adminId) {
    this.adminId = adminId;
    return this;
  }

  /**
   * Get adminId
   * @return adminId
  */
  
  @Schema(name = "adminId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adminId")
  public String getAdminId() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }

  public AdviceSchema adminName(String adminName) {
    this.adminName = adminName;
    return this;
  }

  /**
   * Get adminName
   * @return adminName
  */
  
  @Schema(name = "adminName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adminName")
  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public AdviceSchema adviceId(String adviceId) {
    this.adviceId = adviceId;
    return this;
  }

  /**
   * Get adviceId
   * @return adviceId
  */
  
  @Schema(name = "adviceId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceId")
  public String getAdviceId() {
    return adviceId;
  }

  public void setAdviceId(String adviceId) {
    this.adviceId = adviceId;
  }

  public AdviceSchema adviceContent(String adviceContent) {
    this.adviceContent = adviceContent;
    return this;
  }

  /**
   * Get adviceContent
   * @return adviceContent
  */
  
  @Schema(name = "adviceContent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceContent")
  public String getAdviceContent() {
    return adviceContent;
  }

  public void setAdviceContent(String adviceContent) {
    this.adviceContent = adviceContent;
  }

  public AdviceSchema createdAt(LocalDateTime createdAt) {
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

  public AdviceSchema updatedAt(LocalDateTime updatedAt) {
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
    AdviceSchema adviceSchema = (AdviceSchema) o;
    return Objects.equals(this.userId, adviceSchema.userId) &&
        Objects.equals(this.adminId, adviceSchema.adminId) &&
        Objects.equals(this.adminName, adviceSchema.adminName) &&
        Objects.equals(this.adviceId, adviceSchema.adviceId) &&
        Objects.equals(this.adviceContent, adviceSchema.adviceContent) &&
        Objects.equals(this.createdAt, adviceSchema.createdAt) &&
        Objects.equals(this.updatedAt, adviceSchema.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, adminId, adminName, adviceId, adviceContent, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdviceSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    adminId: ").append(toIndentedString(adminId)).append("\n");
    sb.append("    adminName: ").append(toIndentedString(adminName)).append("\n");
    sb.append("    adviceId: ").append(toIndentedString(adviceId)).append("\n");
    sb.append("    adviceContent: ").append(toIndentedString(adviceContent)).append("\n");
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

