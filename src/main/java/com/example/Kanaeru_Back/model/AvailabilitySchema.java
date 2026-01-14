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
 * AvailabilitySchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AvailabilitySchema {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startAt;

  private Boolean range15min;

  private Boolean renge30min;

  private Boolean renge45min;

  private Boolean renge60min;

  public AvailabilitySchema startAt(LocalDateTime startAt) {
    this.startAt = startAt;
    return this;
  }

  /**
   * Get startAt
   * @return startAt
  */
  @Valid 
  @Schema(name = "startAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startAt")
  public LocalDateTime getStartAt() {
    return startAt;
  }

  public void setStartAt(LocalDateTime startAt) {
    this.startAt = startAt;
  }

  public AvailabilitySchema range15min(Boolean range15min) {
    this.range15min = range15min;
    return this;
  }

  /**
   * Get range15min
   * @return range15min
  */
  
  @Schema(name = "range15min", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("range15min")
  public Boolean getRange15min() {
    return range15min;
  }

  public void setRange15min(Boolean range15min) {
    this.range15min = range15min;
  }

  public AvailabilitySchema renge30min(Boolean renge30min) {
    this.renge30min = renge30min;
    return this;
  }

  /**
   * Get renge30min
   * @return renge30min
  */
  
  @Schema(name = "renge30min", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("renge30min")
  public Boolean getRenge30min() {
    return renge30min;
  }

  public void setRenge30min(Boolean renge30min) {
    this.renge30min = renge30min;
  }

  public AvailabilitySchema renge45min(Boolean renge45min) {
    this.renge45min = renge45min;
    return this;
  }

  /**
   * Get renge45min
   * @return renge45min
  */
  
  @Schema(name = "renge45min", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("renge45min")
  public Boolean getRenge45min() {
    return renge45min;
  }

  public void setRenge45min(Boolean renge45min) {
    this.renge45min = renge45min;
  }

  public AvailabilitySchema renge60min(Boolean renge60min) {
    this.renge60min = renge60min;
    return this;
  }

  /**
   * Get renge60min
   * @return renge60min
  */
  
  @Schema(name = "renge60min", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("renge60min")
  public Boolean getRenge60min() {
    return renge60min;
  }

  public void setRenge60min(Boolean renge60min) {
    this.renge60min = renge60min;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailabilitySchema availabilitySchema = (AvailabilitySchema) o;
    return Objects.equals(this.startAt, availabilitySchema.startAt) &&
        Objects.equals(this.range15min, availabilitySchema.range15min) &&
        Objects.equals(this.renge30min, availabilitySchema.renge30min) &&
        Objects.equals(this.renge45min, availabilitySchema.renge45min) &&
        Objects.equals(this.renge60min, availabilitySchema.renge60min);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startAt, range15min, renge30min, renge45min, renge60min);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailabilitySchema {\n");
    sb.append("    startAt: ").append(toIndentedString(startAt)).append("\n");
    sb.append("    range15min: ").append(toIndentedString(range15min)).append("\n");
    sb.append("    renge30min: ").append(toIndentedString(renge30min)).append("\n");
    sb.append("    renge45min: ").append(toIndentedString(renge45min)).append("\n");
    sb.append("    renge60min: ").append(toIndentedString(renge60min)).append("\n");
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

