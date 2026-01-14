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
 * UserSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class UserSchema {

  private String userId;

  private String email;

  private String passwordHash;

  private String name;

  private String company;

  private String role;

  private Integer delFlg;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;

  private Integer businessStartHour;

  private Integer businessEndHour;

  private String webhookUrl;

  public UserSchema userId(String userId) {
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

  public UserSchema email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserSchema passwordHash(String passwordHash) {
    this.passwordHash = passwordHash;
    return this;
  }

  /**
   * Get passwordHash
   * @return passwordHash
  */
  
  @Schema(name = "passwordHash", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("passwordHash")
  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public UserSchema name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserSchema company(String company) {
    this.company = company;
    return this;
  }

  /**
   * Get company
   * @return company
  */
  
  @Schema(name = "company", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public UserSchema role(String role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  
  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public UserSchema delFlg(Integer delFlg) {
    this.delFlg = delFlg;
    return this;
  }

  /**
   * Get delFlg
   * @return delFlg
  */
  
  @Schema(name = "delFlg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("delFlg")
  public Integer getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(Integer delFlg) {
    this.delFlg = delFlg;
  }

  public UserSchema createdAt(LocalDateTime createdAt) {
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

  public UserSchema updatedAt(LocalDateTime updatedAt) {
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

  public UserSchema businessStartHour(Integer businessStartHour) {
    this.businessStartHour = businessStartHour;
    return this;
  }

  /**
   * Get businessStartHour
   * @return businessStartHour
  */
  
  @Schema(name = "businessStartHour", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("businessStartHour")
  public Integer getBusinessStartHour() {
    return businessStartHour;
  }

  public void setBusinessStartHour(Integer businessStartHour) {
    this.businessStartHour = businessStartHour;
  }

  public UserSchema businessEndHour(Integer businessEndHour) {
    this.businessEndHour = businessEndHour;
    return this;
  }

  /**
   * Get businessEndHour
   * @return businessEndHour
  */
  
  @Schema(name = "businessEndHour", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("businessEndHour")
  public Integer getBusinessEndHour() {
    return businessEndHour;
  }

  public void setBusinessEndHour(Integer businessEndHour) {
    this.businessEndHour = businessEndHour;
  }

  public UserSchema webhookUrl(String webhookUrl) {
    this.webhookUrl = webhookUrl;
    return this;
  }

  /**
   * Get webhookUrl
   * @return webhookUrl
  */
  
  @Schema(name = "webhookUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("webhookUrl")
  public String getWebhookUrl() {
    return webhookUrl;
  }

  public void setWebhookUrl(String webhookUrl) {
    this.webhookUrl = webhookUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSchema userSchema = (UserSchema) o;
    return Objects.equals(this.userId, userSchema.userId) &&
        Objects.equals(this.email, userSchema.email) &&
        Objects.equals(this.passwordHash, userSchema.passwordHash) &&
        Objects.equals(this.name, userSchema.name) &&
        Objects.equals(this.company, userSchema.company) &&
        Objects.equals(this.role, userSchema.role) &&
        Objects.equals(this.delFlg, userSchema.delFlg) &&
        Objects.equals(this.createdAt, userSchema.createdAt) &&
        Objects.equals(this.updatedAt, userSchema.updatedAt) &&
        Objects.equals(this.businessStartHour, userSchema.businessStartHour) &&
        Objects.equals(this.businessEndHour, userSchema.businessEndHour) &&
        Objects.equals(this.webhookUrl, userSchema.webhookUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, email, passwordHash, name, company, role, delFlg, createdAt, updatedAt, businessStartHour, businessEndHour, webhookUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    passwordHash: ").append(toIndentedString(passwordHash)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    delFlg: ").append(toIndentedString(delFlg)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    businessStartHour: ").append(toIndentedString(businessStartHour)).append("\n");
    sb.append("    businessEndHour: ").append(toIndentedString(businessEndHour)).append("\n");
    sb.append("    webhookUrl: ").append(toIndentedString(webhookUrl)).append("\n");
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

