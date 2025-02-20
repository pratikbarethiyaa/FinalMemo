package com.example.Memo.DTOJoins;

import java.time.LocalDateTime;

import com.example.Memo.Model.Invoice;
import com.example.Memo.Model.Taxes;

public class InvoiceItemDTO {

    private Integer id;
    
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double taxPrice;
    private Double total;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Double getTaxPrice() {
        return taxPrice;
    }
    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    



}
