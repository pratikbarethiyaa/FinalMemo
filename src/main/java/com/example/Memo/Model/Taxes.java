package com.example.Memo.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "taxes")
public class Taxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tax_name", nullable = false)
    private String taxName;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "tax_rate")
    private String taxRate;

    @Column(name = "effective_from")
    @Temporal(TemporalType.DATE)
    private Date effectiveFrom;

    @Column(name = "effective_till")
    @Temporal(TemporalType.DATE)
    private Date effectiveTill;

    @Column(name = "is_deleted", nullable = false,columnDefinition = "TINYINT(1)")
	private Boolean isDeleted = false;
    
    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "sgst", precision = 10, scale = 2)
    private BigDecimal sgst;

    @Column(name = "cgst", precision = 10, scale = 2)
    private BigDecimal cgst;

    @Column(name = "igst", precision = 10, scale = 2)
    private BigDecimal igst;
    
    @Column(name = "created_by")
    private Integer createdBy;
    
    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    // Getters and Setters

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

    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }
    public void setDeletedBy(Integer deletedBy) {
        // You can decide how to handle null values.
        this.deletedBy = (deletedBy != null) ? deletedBy : 0;
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
    
    public Integer getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    
    public Integer getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
