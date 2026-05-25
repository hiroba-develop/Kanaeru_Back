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
 * ApiSupportReactionCreatePutRequest
 */

@JsonTypeName("_api_support_reaction_create_put_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportReactionCreatePutRequest {

  private Integer messageSeq;

  private Integer reactionFlag;

  public ApiSupportReactionCreatePutRequest messageSeq(Integer messageSeq) {
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

  public ApiSupportReactionCreatePutRequest reactionFlag(Integer reactionFlag) {
    this.reactionFlag = reactionFlag;
    return this;
  }

  /**
   * Get reactionFlag
   * @return reactionFlag
  */
  
  @Schema(name = "reactionFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reactionFlag")
  public Integer getReactionFlag() {
    return reactionFlag;
  }

  public void setReactionFlag(Integer reactionFlag) {
    this.reactionFlag = reactionFlag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportReactionCreatePutRequest apiSupportReactionCreatePutRequest = (ApiSupportReactionCreatePutRequest) o;
    return Objects.equals(this.messageSeq, apiSupportReactionCreatePutRequest.messageSeq) &&
        Objects.equals(this.reactionFlag, apiSupportReactionCreatePutRequest.reactionFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageSeq, reactionFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportReactionCreatePutRequest {\n");
    sb.append("    messageSeq: ").append(toIndentedString(messageSeq)).append("\n");
    sb.append("    reactionFlag: ").append(toIndentedString(reactionFlag)).append("\n");
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

