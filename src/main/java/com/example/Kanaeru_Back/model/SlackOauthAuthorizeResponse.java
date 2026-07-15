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
 * SlackOauthAuthorizeResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SlackOauthAuthorizeResponse {

  private String authorizeUrl;

  public SlackOauthAuthorizeResponse authorizeUrl(String authorizeUrl) {
    this.authorizeUrl = authorizeUrl;
    return this;
  }

  /**
   * フロントエンドがwindow.location.hrefで遷移させるSlack認可URL
   * @return authorizeUrl
  */
  
  @Schema(name = "authorizeUrl", description = "フロントエンドがwindow.location.hrefで遷移させるSlack認可URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorizeUrl")
  public String getAuthorizeUrl() {
    return authorizeUrl;
  }

  public void setAuthorizeUrl(String authorizeUrl) {
    this.authorizeUrl = authorizeUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SlackOauthAuthorizeResponse slackOauthAuthorizeResponse = (SlackOauthAuthorizeResponse) o;
    return Objects.equals(this.authorizeUrl, slackOauthAuthorizeResponse.authorizeUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizeUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SlackOauthAuthorizeResponse {\n");
    sb.append("    authorizeUrl: ").append(toIndentedString(authorizeUrl)).append("\n");
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

