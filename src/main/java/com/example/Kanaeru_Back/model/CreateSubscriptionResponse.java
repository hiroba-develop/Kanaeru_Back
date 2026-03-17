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
 * CreateSubscriptionResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateSubscriptionResponse {

  private Integer responseStatus;

  private String clientSecret;

  private String subscriptionId;

  private String customerId;

  private Integer amount;

  public CreateSubscriptionResponse responseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
    return this;
  }

  /**
   * 成功時は1、失敗時は0
   * @return responseStatus
  */
  
  @Schema(name = "responseStatus", description = "成功時は1、失敗時は0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("responseStatus")
  public Integer getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public CreateSubscriptionResponse clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

  /**
   * フロントのStripe Elements confirmPaymentに使用するClientSecret
   * @return clientSecret
  */
  
  @Schema(name = "clientSecret", description = "フロントのStripe Elements confirmPaymentに使用するClientSecret", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientSecret")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public CreateSubscriptionResponse subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * 作成されたStripe SubscriptionのID
   * @return subscriptionId
  */
  
  @Schema(name = "subscriptionId", description = "作成されたStripe SubscriptionのID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subscriptionId")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public CreateSubscriptionResponse customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * 作成または取得されたStripe CustomerのID
   * @return customerId
  */
  
  @Schema(name = "customerId", description = "作成または取得されたStripe CustomerのID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public CreateSubscriptionResponse amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * 請求金額（円）
   * @return amount
  */
  
  @Schema(name = "amount", description = "請求金額（円）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateSubscriptionResponse createSubscriptionResponse = (CreateSubscriptionResponse) o;
    return Objects.equals(this.responseStatus, createSubscriptionResponse.responseStatus) &&
        Objects.equals(this.clientSecret, createSubscriptionResponse.clientSecret) &&
        Objects.equals(this.subscriptionId, createSubscriptionResponse.subscriptionId) &&
        Objects.equals(this.customerId, createSubscriptionResponse.customerId) &&
        Objects.equals(this.amount, createSubscriptionResponse.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, clientSecret, subscriptionId, customerId, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateSubscriptionResponse {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

