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
 * UserListSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class UserListSchema {

  private String userId;

  private String email;

  private String name;

  private String userImageUrl;

  private String company;

  private String role;

  private Integer delFlg;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;

  public UserListSchema userId(String userId) {
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

  public UserListSchema email(String email) {
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

  public UserListSchema name(String name) {
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

  public UserListSchema userImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
    return this;
  }

  /**
   * Get userImageUrl
   * @return userImageUrl
  */
  
  @Schema(name = "userImageUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userImageUrl")
  public String getUserImageUrl() {
    return userImageUrl;
  }

  public void setUserImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
  }

  public UserListSchema company(String company) {
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

  public UserListSchema role(String role) {
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

  public UserListSchema delFlg(Integer delFlg) {
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

  public UserListSchema createdAt(LocalDateTime createdAt) {
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

  public UserListSchema updatedAt(LocalDateTime updatedAt) {
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
    UserListSchema userListSchema = (UserListSchema) o;
    return Objects.equals(this.userId, userListSchema.userId) &&
        Objects.equals(this.email, userListSchema.email) &&
        Objects.equals(this.name, userListSchema.name) &&
        Objects.equals(this.userImageUrl, userListSchema.userImageUrl) &&
        Objects.equals(this.company, userListSchema.company) &&
        Objects.equals(this.role, userListSchema.role) &&
        Objects.equals(this.delFlg, userListSchema.delFlg) &&
        Objects.equals(this.createdAt, userListSchema.createdAt) &&
        Objects.equals(this.updatedAt, userListSchema.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, email, name, userImageUrl, company, role, delFlg, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserListSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userImageUrl: ").append(toIndentedString(userImageUrl)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    delFlg: ").append(toIndentedString(delFlg)).append("\n");
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

