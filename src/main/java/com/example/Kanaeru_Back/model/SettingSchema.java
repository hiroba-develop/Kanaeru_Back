package com.example.Kanaeru_Back.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SettingSchema
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SettingSchema {

  private String userId;

  private String adminId;

  private String companySize;

  private String industry;

  private BigDecimal capital;

  private String financialKnowledge;

  private Integer fiscalYearStartYear;

  private Integer fiscalYearStartMonth;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate lastAdminCommentDate;

  public SettingSchema userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public SettingSchema adminId(String adminId) {
    this.adminId = adminId;
    return this;
  }

  /**
   * Get adminId
   * @return adminId
  */
  
  @Schema(name = "adminId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adminId")
  public String getAdminId() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }

  public SettingSchema companySize(String companySize) {
    this.companySize = companySize;
    return this;
  }

  /**
   * Get companySize
   * @return companySize
  */
  
  @Schema(name = "companySize", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("companySize")
  public String getCompanySize() {
    return companySize;
  }

  public void setCompanySize(String companySize) {
    this.companySize = companySize;
  }

  public SettingSchema industry(String industry) {
    this.industry = industry;
    return this;
  }

  /**
   * Get industry
   * @return industry
  */
  
  @Schema(name = "industry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("industry")
  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public SettingSchema capital(BigDecimal capital) {
    this.capital = capital;
    return this;
  }

  /**
   * Get capital
   * @return capital
  */
  @Valid 
  @Schema(name = "capital", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("capital")
  public BigDecimal getCapital() {
    return capital;
  }

  public void setCapital(BigDecimal capital) {
    this.capital = capital;
  }

  public SettingSchema financialKnowledge(String financialKnowledge) {
    this.financialKnowledge = financialKnowledge;
    return this;
  }

  /**
   * Get financialKnowledge
   * @return financialKnowledge
  */
  
  @Schema(name = "financialKnowledge", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("financialKnowledge")
  public String getFinancialKnowledge() {
    return financialKnowledge;
  }

  public void setFinancialKnowledge(String financialKnowledge) {
    this.financialKnowledge = financialKnowledge;
  }

  public SettingSchema fiscalYearStartYear(Integer fiscalYearStartYear) {
    this.fiscalYearStartYear = fiscalYearStartYear;
    return this;
  }

  /**
   * Get fiscalYearStartYear
   * @return fiscalYearStartYear
  */
  
  @Schema(name = "fiscalYearStartYear", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fiscalYearStartYear")
  public Integer getFiscalYearStartYear() {
    return fiscalYearStartYear;
  }

  public void setFiscalYearStartYear(Integer fiscalYearStartYear) {
    this.fiscalYearStartYear = fiscalYearStartYear;
  }

  public SettingSchema fiscalYearStartMonth(Integer fiscalYearStartMonth) {
    this.fiscalYearStartMonth = fiscalYearStartMonth;
    return this;
  }

  /**
   * Get fiscalYearStartMonth
   * @return fiscalYearStartMonth
  */
  
  @Schema(name = "fiscalYearStartMonth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fiscalYearStartMonth")
  public Integer getFiscalYearStartMonth() {
    return fiscalYearStartMonth;
  }

  public void setFiscalYearStartMonth(Integer fiscalYearStartMonth) {
    this.fiscalYearStartMonth = fiscalYearStartMonth;
  }

  public SettingSchema lastAdminCommentDate(LocalDate lastAdminCommentDate) {
    this.lastAdminCommentDate = lastAdminCommentDate;
    return this;
  }

  /**
   * Get lastAdminCommentDate
   * @return lastAdminCommentDate
  */
  @Valid 
  @Schema(name = "lastAdminCommentDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastAdminCommentDate")
  public LocalDate getLastAdminCommentDate() {
    return lastAdminCommentDate;
  }

  public void setLastAdminCommentDate(LocalDate lastAdminCommentDate) {
    this.lastAdminCommentDate = lastAdminCommentDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettingSchema settingSchema = (SettingSchema) o;
    return Objects.equals(this.userId, settingSchema.userId) &&
        Objects.equals(this.adminId, settingSchema.adminId) &&
        Objects.equals(this.companySize, settingSchema.companySize) &&
        Objects.equals(this.industry, settingSchema.industry) &&
        Objects.equals(this.capital, settingSchema.capital) &&
        Objects.equals(this.financialKnowledge, settingSchema.financialKnowledge) &&
        Objects.equals(this.fiscalYearStartYear, settingSchema.fiscalYearStartYear) &&
        Objects.equals(this.fiscalYearStartMonth, settingSchema.fiscalYearStartMonth) &&
        Objects.equals(this.lastAdminCommentDate, settingSchema.lastAdminCommentDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, adminId, companySize, industry, capital, financialKnowledge, fiscalYearStartYear, fiscalYearStartMonth, lastAdminCommentDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettingSchema {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    adminId: ").append(toIndentedString(adminId)).append("\n");
    sb.append("    companySize: ").append(toIndentedString(companySize)).append("\n");
    sb.append("    industry: ").append(toIndentedString(industry)).append("\n");
    sb.append("    capital: ").append(toIndentedString(capital)).append("\n");
    sb.append("    financialKnowledge: ").append(toIndentedString(financialKnowledge)).append("\n");
    sb.append("    fiscalYearStartYear: ").append(toIndentedString(fiscalYearStartYear)).append("\n");
    sb.append("    fiscalYearStartMonth: ").append(toIndentedString(fiscalYearStartMonth)).append("\n");
    sb.append("    lastAdminCommentDate: ").append(toIndentedString(lastAdminCommentDate)).append("\n");
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

