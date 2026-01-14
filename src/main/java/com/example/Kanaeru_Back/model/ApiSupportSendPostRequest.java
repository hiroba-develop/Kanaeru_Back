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
 * ApiSupportSendPostRequest
 */

@JsonTypeName("_api_support_send_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportSendPostRequest {

  private String senderId;

  private String recipientId;

  private String content;

  private Integer messageSeq;

  public ApiSupportSendPostRequest senderId(String senderId) {
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

  public ApiSupportSendPostRequest recipientId(String recipientId) {
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

  public ApiSupportSendPostRequest content(String content) {
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

  public ApiSupportSendPostRequest messageSeq(Integer messageSeq) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportSendPostRequest apiSupportSendPostRequest = (ApiSupportSendPostRequest) o;
    return Objects.equals(this.senderId, apiSupportSendPostRequest.senderId) &&
        Objects.equals(this.recipientId, apiSupportSendPostRequest.recipientId) &&
        Objects.equals(this.content, apiSupportSendPostRequest.content) &&
        Objects.equals(this.messageSeq, apiSupportSendPostRequest.messageSeq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(senderId, recipientId, content, messageSeq);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportSendPostRequest {\n");
    sb.append("    senderId: ").append(toIndentedString(senderId)).append("\n");
    sb.append("    recipientId: ").append(toIndentedString(recipientId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    messageSeq: ").append(toIndentedString(messageSeq)).append("\n");
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

