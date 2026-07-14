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
 * GetSlackUserMapping200Response
 */

@JsonTypeName("getSlackUserMapping_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GetSlackUserMapping200Response {

  private Integer responseStatus;

  private String slackUserId;

  public GetSlackUserMapping200Response responseStatus(Integer responseStatus) {
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

  public GetSlackUserMapping200Response slackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
    return this;
  }

  /**
   * Slack の Member ID（例: U012AB3CD）
   * @return slackUserId
  */
  
  @Schema(name = "slackUserId", description = "Slack の Member ID（例: U012AB3CD）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("slackUserId")
  public String getSlackUserId() {
    return slackUserId;
  }

  public void setSlackUserId(String slackUserId) {
    this.slackUserId = slackUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetSlackUserMapping200Response getSlackUserMapping200Response = (GetSlackUserMapping200Response) o;
    return Objects.equals(this.responseStatus, getSlackUserMapping200Response.responseStatus) &&
        Objects.equals(this.slackUserId, getSlackUserMapping200Response.slackUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, slackUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetSlackUserMapping200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    slackUserId: ").append(toIndentedString(slackUserId)).append("\n");
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

