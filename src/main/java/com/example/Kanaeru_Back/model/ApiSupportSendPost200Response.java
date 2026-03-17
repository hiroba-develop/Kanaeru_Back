package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.DmMessagesSchema;
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
 * ApiSupportSendPost200Response
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportSendPost200Response {

  private Integer responseStatus;

  private Integer lastMessageSeq;

  private DmMessagesSchema dmMessageSchema;

  public ApiSupportSendPost200Response responseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
    return this;
  }

  /**
   * Get responseStatus
   * @return responseStatus
  */
  
  @Schema(name = "responseStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("responseStatus")
  public Integer getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public ApiSupportSendPost200Response lastMessageSeq(Integer lastMessageSeq) {
    this.lastMessageSeq = lastMessageSeq;
    return this;
  }

  /**
   * Get lastMessageSeq
   * @return lastMessageSeq
  */
  
  @Schema(name = "lastMessageSeq", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastMessageSeq")
  public Integer getLastMessageSeq() {
    return lastMessageSeq;
  }

  public void setLastMessageSeq(Integer lastMessageSeq) {
    this.lastMessageSeq = lastMessageSeq;
  }

  public ApiSupportSendPost200Response dmMessageSchema(DmMessagesSchema dmMessageSchema) {
    this.dmMessageSchema = dmMessageSchema;
    return this;
  }

  /**
   * Get dmMessageSchema
   * @return dmMessageSchema
  */
  @Valid 
  @Schema(name = "dmMessageSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dmMessageSchema")
  public DmMessagesSchema getDmMessageSchema() {
    return dmMessageSchema;
  }

  public void setDmMessageSchema(DmMessagesSchema dmMessageSchema) {
    this.dmMessageSchema = dmMessageSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportSendPost200Response apiSupportSendPost200Response = (ApiSupportSendPost200Response) o;
    return Objects.equals(this.responseStatus, apiSupportSendPost200Response.responseStatus) &&
        Objects.equals(this.lastMessageSeq, apiSupportSendPost200Response.lastMessageSeq) &&
        Objects.equals(this.dmMessageSchema, apiSupportSendPost200Response.dmMessageSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, lastMessageSeq, dmMessageSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportSendPost200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    lastMessageSeq: ").append(toIndentedString(lastMessageSeq)).append("\n");
    sb.append("    dmMessageSchema: ").append(toIndentedString(dmMessageSchema)).append("\n");
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

