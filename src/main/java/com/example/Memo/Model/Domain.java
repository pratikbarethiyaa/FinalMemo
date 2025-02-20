package com.example.Memo.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "domains")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "pan_no")
	private String panNo;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String address;

	private String gstinno;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String logo;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String logo2;

	private String email;
	private String website;
	private String mobile;
	private String mobile2;
	private String landline;

	@Column(name = "bankaccno")
	private String bankAccNo;

	private String bankname;
	private String ifsc;

	@Column(name = "customer_prefix")
	private String customerPrefix;

	@Column(name = "invoice_prefix")
	private String invoicePrefix;

	@Column(name = "is_deleted")
	private Boolean isDeleted = false;

	@Column(name = "deleted_by")
	private Integer deletedBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name="created_by")
	private Integer created_by;

	@Column(name="updated_by")
	private Integer updated_by;

	// Getters and Setters

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGstinno() {
		return gstinno;
	}

	public void setGstinno(String gstinno) {
		this.gstinno = gstinno;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo2() {
		return logo2;
	}

	public void setLogo2(String logo2) {
		this.logo2 = logo2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public String getInvoicePrefix() {
		return invoicePrefix;
	}

	public void setInvoicePrefix(String invoicePrefix) {
		this.invoicePrefix = invoicePrefix;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}




}
