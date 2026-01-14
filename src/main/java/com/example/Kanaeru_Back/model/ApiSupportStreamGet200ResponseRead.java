package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ApiSupportStreamGet200ResponseRead
 */

@JsonTypeName("_api_support_stream_get_200_response_read")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportStreamGet200ResponseRead {

  private Integer messageSeq;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime readAt;

  public ApiSupportStreamGet200ResponseRead messageSeq(Integer messageSeq) {
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

  public ApiSupportStreamGet200ResponseRead readAt(LocalDateTime readAt) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportStreamGet200ResponseRead apiSupportStreamGet200ResponseRead = (ApiSupportStreamGet200ResponseRead) o;
    return Objects.equals(this.messageSeq, apiSupportStreamGet200ResponseRead.messageSeq) &&
        Objects.equals(this.readAt, apiSupportStreamGet200ResponseRead.readAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageSeq, readAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportStreamGet200ResponseRead {\n");
    sb.append("    messageSeq: ").append(toIndentedString(messageSeq)).append("\n");
    sb.append("    readAt: ").append(toIndentedString(readAt)).append("\n");
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

