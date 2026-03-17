package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * SubscriptionSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SubscriptionSchema {

  private String id;

  private String status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime currentPeriodStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime currentPeriodEnd;

  private Boolean cancelAtPeriodEnd;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime canceledAt;

  private Integer amount;

  private String billingCycle;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  public SubscriptionSchema id(String id) {
    this.id = id;
    return this;
  }

  /**
   * StripeサブスクリプションID
   * @return id
  */
  
  @Schema(name = "id", description = "StripeサブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubscriptionSchema status(String status) {
    this.status = status;
    return this;
  }

  /**
   * サブスクリプションの状態（active / past_due / canceled など）
   * @return status
  */
  
  @Schema(name = "status", description = "サブスクリプションの状態（active / past_due / canceled など）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public SubscriptionSchema currentPeriodStart(LocalDateTime currentPeriodStart) {
    this.currentPeriodStart = currentPeriodStart;
    return this;
  }

  /**
   * 現在の請求期間の開始日時
   * @return currentPeriodStart
  */
  @Valid 
  @Schema(name = "currentPeriodStart", description = "現在の請求期間の開始日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPeriodStart")
  public LocalDateTime getCurrentPeriodStart() {
    return currentPeriodStart;
  }

  public void setCurrentPeriodStart(LocalDateTime currentPeriodStart) {
    this.currentPeriodStart = currentPeriodStart;
  }

  public SubscriptionSchema currentPeriodEnd(LocalDateTime currentPeriodEnd) {
    this.currentPeriodEnd = currentPeriodEnd;
    return this;
  }

  /**
   * 現在の請求期間の終了日時
   * @return currentPeriodEnd
  */
  @Valid 
  @Schema(name = "currentPeriodEnd", description = "現在の請求期間の終了日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPeriodEnd")
  public LocalDateTime getCurrentPeriodEnd() {
    return currentPeriodEnd;
  }

  public void setCurrentPeriodEnd(LocalDateTime currentPeriodEnd) {
    this.currentPeriodEnd = currentPeriodEnd;
  }

  public SubscriptionSchema cancelAtPeriodEnd(Boolean cancelAtPeriodEnd) {
    this.cancelAtPeriodEnd = cancelAtPeriodEnd;
    return this;
  }

  /**
   * 期間終了時に解約予定かどうか
   * @return cancelAtPeriodEnd
  */
  
  @Schema(name = "cancelAtPeriodEnd", description = "期間終了時に解約予定かどうか", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cancelAtPeriodEnd")
  public Boolean getCancelAtPeriodEnd() {
    return cancelAtPeriodEnd;
  }

  public void setCancelAtPeriodEnd(Boolean cancelAtPeriodEnd) {
    this.cancelAtPeriodEnd = cancelAtPeriodEnd;
  }

  public SubscriptionSchema canceledAt(LocalDateTime canceledAt) {
    this.canceledAt = canceledAt;
    return this;
  }

  /**
   * 解約予約日時（cancelAtPeriodEndがtrueの場合に設定）
   * @return canceledAt
  */
  @Valid 
  @Schema(name = "canceledAt", description = "解約予約日時（cancelAtPeriodEndがtrueの場合に設定）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("canceledAt")
  public LocalDateTime getCanceledAt() {
    return canceledAt;
  }

  public void setCanceledAt(LocalDateTime canceledAt) {
    this.canceledAt = canceledAt;
  }

  public SubscriptionSchema amount(Integer amount) {
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

  public SubscriptionSchema billingCycle(String billingCycle) {
    this.billingCycle = billingCycle;
    return this;
  }

  /**
   * 請求サイクル（monthly / yearly など）
   * @return billingCycle
  */
  
  @Schema(name = "billingCycle", description = "請求サイクル（monthly / yearly など）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("billingCycle")
  public String getBillingCycle() {
    return billingCycle;
  }

  public void setBillingCycle(String billingCycle) {
    this.billingCycle = billingCycle;
  }

  public SubscriptionSchema createdAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * サブスクリプション作成日時
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", description = "サブスクリプション作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionSchema subscriptionSchema = (SubscriptionSchema) o;
    return Objects.equals(this.id, subscriptionSchema.id) &&
        Objects.equals(this.status, subscriptionSchema.status) &&
        Objects.equals(this.currentPeriodStart, subscriptionSchema.currentPeriodStart) &&
        Objects.equals(this.currentPeriodEnd, subscriptionSchema.currentPeriodEnd) &&
        Objects.equals(this.cancelAtPeriodEnd, subscriptionSchema.cancelAtPeriodEnd) &&
        Objects.equals(this.canceledAt, subscriptionSchema.canceledAt) &&
        Objects.equals(this.amount, subscriptionSchema.amount) &&
        Objects.equals(this.billingCycle, subscriptionSchema.billingCycle) &&
        Objects.equals(this.createdAt, subscriptionSchema.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, currentPeriodStart, currentPeriodEnd, cancelAtPeriodEnd, canceledAt, amount, billingCycle, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionSchema {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    currentPeriodStart: ").append(toIndentedString(currentPeriodStart)).append("\n");
    sb.append("    currentPeriodEnd: ").append(toIndentedString(currentPeriodEnd)).append("\n");
    sb.append("    cancelAtPeriodEnd: ").append(toIndentedString(cancelAtPeriodEnd)).append("\n");
    sb.append("    canceledAt: ").append(toIndentedString(canceledAt)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    billingCycle: ").append(toIndentedString(billingCycle)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

