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
 * ApiSlackEventsPost200Response
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSlackEventsPost200Response {

  private String challenge;

  public ApiSlackEventsPost200Response challenge(String challenge) {
    this.challenge = challenge;
    return this;
  }

  /**
   * URL verification challenge（url_verification時のみ）
   * @return challenge
  */
  
  @Schema(name = "challenge", description = "URL verification challenge（url_verification時のみ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("challenge")
  public String getChallenge() {
    return challenge;
  }

  public void setChallenge(String challenge) {
    this.challenge = challenge;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSlackEventsPost200Response apiSlackEventsPost200Response = (ApiSlackEventsPost200Response) o;
    return Objects.equals(this.challenge, apiSlackEventsPost200Response.challenge);
  }

  @Override
  public int hashCode() {
    return Objects.hash(challenge);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSlackEventsPost200Response {\n");
    sb.append("    challenge: ").append(toIndentedString(challenge)).append("\n");
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

