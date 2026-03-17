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
 * ApiStripeSubscriptionGet200Response
 */

@JsonTypeName("_api_stripe_subscription__get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiStripeSubscriptionGet200Response {

  private Integer responseStatus;

  private SubscriptionSchema subscription;

  public ApiStripeSubscriptionGet200Response responseStatus(Integer responseStatus) {
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

  public ApiStripeSubscriptionGet200Response subscription(SubscriptionSchema subscription) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiStripeSubscriptionGet200Response apiStripeSubscriptionGet200Response = (ApiStripeSubscriptionGet200Response) o;
    return Objects.equals(this.responseStatus, apiStripeSubscriptionGet200Response.responseStatus) &&
        Objects.equals(this.subscription, apiStripeSubscriptionGet200Response.subscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, subscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiStripeSubscriptionGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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

