package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdminCommentSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdminCommentSchema {

  private String userId;

  private Integer year;

  private Integer month;

  private String onepointComment;

  private String goodpointComment;

  private String cautionpointComment;

  private String badpointComment;

  public AdminCommentSchema userId(String userId) {
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

  public AdminCommentSchema year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  
  @Schema(name = "year", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public AdminCommentSchema month(Integer month) {
    this.month = month;
    return this;
  }

  /**
   * Get month
   * @return month
  */
  
  @Schema(name = "month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("month")
  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public AdminCommentSchema onepointComment(String onepointComment) {
    this.onepointComment = onepointComment;
    return this;
  }

  /**
   * Get onepointComment
   * @return onepointComment
  */
  
  @Schema(name = "onepointComment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("onepointComment")
  public String getOnepointComment() {
    return onepointComment;
  }

  public void setOnepointComment(String onepointComment) {
    this.onepointComment = onepointComment;
  }

  public AdminCommentSchema goodpointComment(String goodpointComment) {
    this.goodpointComment = goodpointComment;
    return this;
  }

  /**
   * Get goodpointComment
   * @return goodpointComment
  */
  
  @Schema(name = "goodpointComment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("goodpointComment")
  public String getGoodpointComment() {
    return goodpointComment;
  }

  public void setGoodpointComment(String goodpointComment) {
    this.goodpointComment = goodpointComment;
  }

  public AdminCommentSchema cautionpointComment(String cautionpointComment) {
    this.cautionpointComment = cautionpointComment;
    return this;
  }

  /**
   * Get cautionpointComment
   * @return cautionpointComment
  */
  
  @Schema(name = "cautionpointComment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cautionpointComment")
  public String getCautionpointComment() {
    return cautionpointComment;
  }

  public void setCautionpointComment(String cautionpointComment) {
    this.cautionpointComment = cautionpointComment;
  }

  public AdminCommentSchema badpointComment(String badpointComment) {
    this.badpointComment = badpointComment;
    return this;
  }

  /**
   * Get badpointComment
   * @return badpointComment
  */
  
  @Schema(name = "badpointComment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("badpointComment")
  public String getBadpointComment() {
    return badpointComment;
  }

  public void setBadpointComment(String badpointComment) {
    this.badpointComment = badpointComment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminCommentSchema adminCommentSchema = (AdminCommentSchema) o;
    return Objects.equals(this.userId, adminCommentSchema.userId) &&
        Objects.equals(this.year, adminCommentSchema.year) &&
        Objects.equals(this.month, adminCommentSchema.month) &&
        Objects.equals(this.onepointComment, adminCommentSchema.onepointComment) &&
        Objects.equals(this.goodpointComment, adminCommentSchema.goodpointComment) &&
        Objects.equals(this.cautionpointComment, adminCommentSchema.cautionpointComment) &&
        Objects.equals(this.badpointComment, adminCommentSchema.badpointComment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, year, month, onepointComment, goodpointComment, cautionpointComment, badpointComment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminCommentSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    onepointComment: ").append(toIndentedString(onepointComment)).append("\n");
    sb.append("    goodpointComment: ").append(toIndentedString(goodpointComment)).append("\n");
    sb.append("    cautionpointComment: ").append(toIndentedString(cautionpointComment)).append("\n");
    sb.append("    badpointComment: ").append(toIndentedString(badpointComment)).append("\n");
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

