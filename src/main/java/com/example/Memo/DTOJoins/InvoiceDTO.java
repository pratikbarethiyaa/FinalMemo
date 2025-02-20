package com.example.Memo.DTOJoins;

import java.util.Date;

import com.example.Memo.Model.Customer;
import com.example.Memo.Model.Domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TemporalType;

public class InvoiceDTO {

    private Integer id;
    private String voucherNo;
    private Date invoiceDate;

    private String total;
    private String taxPrice;
    private String grandTotal;
    private String advanceAmount;
    private String discount;
    private String paytype;

    private com.example.Memo.Enums.status status = com.example.Memo.Enums.status.Pending;

    private String carting;
    private String note;
    private String description; // JSON string storing product details

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getVoucherNo() {
        return voucherNo;
    }
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public String getTaxPrice() {
        return taxPrice;
    }
    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }
    public String getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
    public String getAdvanceAmount() {
        return advanceAmount;
    }
    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getPaytype() {
        return paytype;
    }
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public com.example.Memo.Enums.status getStatus() {
        return status;
    }
    public void setStatus(com.example.Memo.Enums.status status) {
        this.status = status;
    }
    public String getCarting() {
        return carting;
    }
    public void setCarting(String carting) {
        this.carting = carting;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    




}
