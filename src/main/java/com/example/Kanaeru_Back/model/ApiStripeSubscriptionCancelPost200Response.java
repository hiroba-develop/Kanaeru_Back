package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.SubscriptionSchema;
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
 * ApiStripeSubscriptionCancelPost200Response
 */

@JsonTypeName("_api_stripe_subscription_cancel_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiStripeSubscriptionCancelPost200Response {

  private Integer responseStatus;

  private SubscriptionSchema subscription;

  private String message;

  public ApiStripeSubscriptionCancelPost200Response responseStatus(Integer responseStatus) {
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

  public ApiStripeSubscriptionCancelPost200Response subscription(SubscriptionSchema subscription) {
    this.subscription = subscription;
    return this;
  }

  /**
   * Get subscription
   * @return subscription
  */
  @Valid 
  @Schema(name = "subscription", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subscription")
  public SubscriptionSchema getSubscription() {
    return subscription;
  }

  public void setSubscription(SubscriptionSchema subscription) {
    this.subscription = subscription;
  }

  public ApiStripeSubscriptionCancelPost200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiStripeSubscriptionCancelPost200Response apiStripeSubscriptionCancelPost200Response = (ApiStripeSubscriptionCancelPost200Response) o;
    return Objects.equals(this.responseStatus, apiStripeSubscriptionCancelPost200Response.responseStatus) &&
        Objects.equals(this.subscription, apiStripeSubscriptionCancelPost200Response.subscription) &&
        Objects.equals(this.message, apiStripeSubscriptionCancelPost200Response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, subscription, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiStripeSubscriptionCancelPost200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

