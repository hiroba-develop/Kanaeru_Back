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
 * ApiMandalaChartsChartIdUpdatePutRequest
 */

@JsonTypeName("_api_mandala_charts__chart_id__update_put_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsChartIdUpdatePutRequest {

  private String startYearMonth;

  private String endYearMonth;

  private Boolean isActive;

  public ApiMandalaChartsChartIdUpdatePutRequest startYearMonth(String startYearMonth) {
    this.startYearMonth = startYearMonth;
    return this;
  }

  /**
   * Get startYearMonth
   * @return startYearMonth
  */
  
  @Schema(name = "start_year_month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start_year_month")
  public String getStartYearMonth() {
    return startYearMonth;
  }

  public void setStartYearMonth(String startYearMonth) {
    this.startYearMonth = startYearMonth;
  }

  public ApiMandalaChartsChartIdUpdatePutRequest endYearMonth(String endYearMonth) {
    this.endYearMonth = endYearMonth;
    return this;
  }

  /**
   * Get endYearMonth
   * @return endYearMonth
  */
  
  @Schema(name = "end_year_month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end_year_month")
  public String getEndYearMonth() {
    return endYearMonth;
  }

  public void setEndYearMonth(String endYearMonth) {
    this.endYearMonth = endYearMonth;
  }

  public ApiMandalaChartsChartIdUpdatePutRequest isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
  */
  
  @Schema(name = "is_active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_active")
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMandalaChartsChartIdUpdatePutRequest apiMandalaChartsChartIdUpdatePutRequest = (ApiMandalaChartsChartIdUpdatePutRequest) o;
    return Objects.equals(this.startYearMonth, apiMandalaChartsChartIdUpdatePutRequest.startYearMonth) &&
        Objects.equals(this.endYearMonth, apiMandalaChartsChartIdUpdatePutRequest.endYearMonth) &&
        Objects.equals(this.isActive, apiMandalaChartsChartIdUpdatePutRequest.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startYearMonth, endYearMonth, isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsChartIdUpdatePutRequest {\n");
    sb.append("    startYearMonth: ").append(toIndentedString(startYearMonth)).append("\n");
    sb.append("    endYearMonth: ").append(toIndentedString(endYearMonth)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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

