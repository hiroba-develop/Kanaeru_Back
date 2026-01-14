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
 * DmMessagesSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class DmMessagesSchema {

  private Integer messageSeq;

  private String senderId;

  private String senderName;

  private String recipientId;

  private String recipientName;

  private String content;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime readAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;

  public DmMessagesSchema messageSeq(Integer messageSeq) {
    this.messageSeq = messageSeq;
    return this;
  }

  /**
   * Get messageSeq
   * @return messageSeq
  */
  
  @Schema(name = "messageSeq", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("messageSeq")
  public Integer getMessageSeq() {
    return messageSeq;
  }

  public void setMessageSeq(Integer messageSeq) {
    this.messageSeq = messageSeq;
  }

  public DmMessagesSchema senderId(String senderId) {
    this.senderId = senderId;
    return this;
  }

  /**
   * Get senderId
   * @return senderId
  */
  
  @Schema(name = "senderId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("senderId")
  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public DmMessagesSchema senderName(String senderName) {
    this.senderName = senderName;
    return this;
  }

  /**
   * Get senderName
   * @return senderName
  */
  
  @Schema(name = "senderName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("senderName")
  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public DmMessagesSchema recipientId(String recipientId) {
    this.recipientId = recipientId;
    return this;
  }

  /**
   * Get recipientId
   * @return recipientId
  */
  
  @Schema(name = "recipientId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipientId")
  public String getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(String recipientId) {
    this.recipientId = recipientId;
  }

  public DmMessagesSchema recipientName(String recipientName) {
    this.recipientName = recipientName;
    return this;
  }

  /**
   * Get recipientName
   * @return recipientName
  */
  
  @Schema(name = "recipientName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recipientName")
  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public DmMessagesSchema content(String content) {
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

  public DmMessagesSchema readAt(LocalDateTime readAt) {
    this.readAt = readAt;
    return this;
  }

  /**
   * Get readAt
   * @return readAt
  */
  @Valid 
  @Schema(name = "readAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("readAt")
  public LocalDateTime getReadAt() {
    return readAt;
  }

  public void setReadAt(LocalDateTime readAt) {
    this.readAt = readAt;
  }

  public DmMessagesSchema createdAt(LocalDateTime createdAt) {
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

  public DmMessagesSchema updatedAt(LocalDateTime updatedAt) {
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
    DmMessagesSchema dmMessagesSchema = (DmMessagesSchema) o;
    return Objects.equals(this.messageSeq, dmMessagesSchema.messageSeq) &&
        Objects.equals(this.senderId, dmMessagesSchema.senderId) &&
        Objects.equals(this.senderName, dmMessagesSchema.senderName) &&
        Objects.equals(this.recipientId, dmMessagesSchema.recipientId) &&
        Objects.equals(this.recipientName, dmMessagesSchema.recipientName) &&
        Objects.equals(this.content, dmMessagesSchema.content) &&
        Objects.equals(this.readAt, dmMessagesSchema.readAt) &&
        Objects.equals(this.createdAt, dmMessagesSchema.createdAt) &&
        Objects.equals(this.updatedAt, dmMessagesSchema.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageSeq, senderId, senderName, recipientId, recipientName, content, readAt, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DmMessagesSchema {\n");
    sb.append("    messageSeq: ").append(toIndentedString(messageSeq)).append("\n");
    sb.append("    senderId: ").append(toIndentedString(senderId)).append("\n");
    sb.append("    senderName: ").append(toIndentedString(senderName)).append("\n");
    sb.append("    recipientId: ").append(toIndentedString(recipientId)).append("\n");
    sb.append("    recipientName: ").append(toIndentedString(recipientName)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    readAt: ").append(toIndentedString(readAt)).append("\n");
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

