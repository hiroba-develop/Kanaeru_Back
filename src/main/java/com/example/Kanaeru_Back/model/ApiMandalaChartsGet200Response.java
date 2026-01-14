package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200ResponseChartsInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ApiMandalaChartsGet200Response
 */

@JsonTypeName("_api_mandala_charts_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApiMandalaChartsGet200Response {

  private Integer responseStatus;

  @Valid
  private List<@Valid ApiMandalaChartsGet200ResponseChartsInner> charts;

  public ApiMandalaChartsGet200Response responseStatus(Integer responseStatus) {
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

  public ApiMandalaChartsGet200Response charts(List<@Valid ApiMandalaChartsGet200ResponseChartsInner> charts) {
    this.charts = charts;
    return this;
  }

  public ApiMandalaChartsGet200Response addChartsItem(ApiMandalaChartsGet200ResponseChartsInner chartsItem) {
    if (this.charts == null) {
      this.charts = new ArrayList<>();
    }
    this.charts.add(chartsItem);
    return this;
  }

  /**
   * Get charts
   * @return charts
  */
  @Valid 
  @Schema(name = "charts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("charts")
  public List<@Valid ApiMandalaChartsGet200ResponseChartsInner> getCharts() {
    return charts;
  }

  public void setCharts(List<@Valid ApiMandalaChartsGet200ResponseChartsInner> charts) {
    this.charts = charts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMandalaChartsGet200Response apiMandalaChartsGet200Response = (ApiMandalaChartsGet200Response) o;
    return Objects.equals(this.responseStatus, apiMandalaChartsGet200Response.responseStatus) &&
        Objects.equals(this.charts, apiMandalaChartsGet200Response.charts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, charts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMandalaChartsGet200Response {\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    charts: ").append(toIndentedString(charts)).append("\n");
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

