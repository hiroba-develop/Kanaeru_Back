package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ApiSupportUnreadStatusGet200Response
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiSupportUnreadStatusGet200Response {

  private Integer responseStatus;

  private Boolean hasUnread;

  @Valid
  private List<String> unreadUserIds;

  public ApiSupportUnreadStatusGet200Response responseStatus(Integer responseStatus) {
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

  public ApiSupportUnreadStatusGet200Response hasUnread(Boolean hasUnread) {
    this.hasUnread = hasUnread;
    return this;
  }

  /**
   * 一般ユーザー用（未読あり/なし）
   * @return hasUnread
  */
  
  @Schema(name = "hasUnread", description = "一般ユーザー用（未読あり/なし）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hasUnread")
  public Boolean getHasUnread() {
    return hasUnread;
  }

  public void setHasUnread(Boolean hasUnread) {
    this.hasUnread = hasUnread;
  }

  public ApiSupportUnreadStatusGet200Response unreadUserIds(List<String> unreadUserIds) {
    this.unreadUserIds = unreadUserIds;
    return this;
  }

  public ApiSupportUnreadStatusGet200Response addUnreadUserIdsItem(String unreadUserIdsItem) {
    if (this.unreadUserIds == null) {
      this.unreadUserIds = new ArrayList<>();
    }
    this.unreadUserIds.add(unreadUserIdsItem);
    return this;
  }

  /**
   * 管理者用（未読のある送信者IDリスト）
   * @return unreadUserIds
  */
  
  @Schema(name = "unreadUserIds", description = "管理者用（未読のある送信者IDリスト）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unreadUserIds")
  public List<String> getUnreadUserIds() {
    return unreadUserIds;
  }

  public void setUnreadUserIds(List<String> unreadUserIds) {
    this.unreadUserIds = unreadUserIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiSupportUnreadStatusGet200Response apiSupportUnreadStatusGet200Response = (ApiSupportUnreadStatusGet200Response) o;
    return Objects.equals(this.responseStatus, apiSupportUnreadStatusGet200Response.responseStatus) &&
        Objects.equals(this.hasUnread, apiSupportUnreadStatusGet200Response.hasUnread) &&
        Objects.equals(this.unreadUserIds, apiSupportUnreadStatusGet200Response.unreadUserIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, hasUnread, unreadUserIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiSupportUnreadStatusGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    hasUnread: ").append(toIndentedString(hasUnread)).append("\n");
    sb.append("    unreadUserIds: ").append(toIndentedString(unreadUserIds)).append("\n");
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

