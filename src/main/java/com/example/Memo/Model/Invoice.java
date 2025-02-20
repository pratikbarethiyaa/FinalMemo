package com.example.Memo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Replace primitive domain_id with a ManyToOne association
    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;

    // Replace primitive customer_id with a ManyToOne association
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name="voucher_no")
    private String voucherNo;

    @Column(name="invoice_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    private String total;
    @Column(name="tax_price")
    private String taxPrice;
    @Column(name="grand_total")
    private String grandTotal;
    @Column(name="advance_amount")
    private String advanceAmount;
    private String discount;
    private String paytype;

    @Enumerated(EnumType.STRING)
    private com.example.Memo.Enums.status status = com.example.Memo.Enums.status.Pending;

    private String carting;
    private String note;

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("description")
    private String description; // JSON string storing product details

    @Column(name="created_by")
    private Integer createdBy;
    @Column(name="updated_by")
    private Integer updatedBy;

    @Column(name = "is_deleted", nullable = false,columnDefinition = "TINYINT(1)")
	private Boolean isDeleted = false;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    // Optionally, if you want to link InvoiceItems with the invoice
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InvoiceItem> items;
    // Constructors
    public Invoice() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Domain getDomain() {
        return domain;
    }
    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public List<InvoiceItem> getItems() {
        return items;
    }
    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }
}
