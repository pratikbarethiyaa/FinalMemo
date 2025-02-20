package com.example.Memo.DTOJoins;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;

public class TaxDTO {
    private Integer id;
    private String taxName;
    private String description;
    private String taxNumber;
    private String taxRate;
    private Date effectiveFrom;
    private Date effectiveTill;
    private BigDecimal sgst;
    private BigDecimal cgst;
    private BigDecimal igst;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTaxName() {
        return taxName;
    }
    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTaxNumber() {
        return taxNumber;
    }
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
    public String getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }
    public Date getEffectiveFrom() {
        return effectiveFrom;
    }
    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    public Date getEffectiveTill() {
        return effectiveTill;
    }
    public void setEffectiveTill(Date effectiveTill) {
        this.effectiveTill = effectiveTill;
    }
    public BigDecimal getSgst() {
        return sgst;
    }
    public void setSgst(BigDecimal sgst) {
        this.sgst = sgst;
    }
    public BigDecimal getCgst() {
        return cgst;
    }
    public void setCgst(BigDecimal cgst) {
        this.cgst = cgst;
    }
    public BigDecimal getIgst() {
        return igst;
    }
    public void setIgst(BigDecimal igst) {
        this.igst = igst;
    }

    
    

}
