package com.example.Memo.Model;

import java.time.LocalDateTime;


import com.example.Memo.Enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "users")
public class CustomUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER,optional = true)
	@JoinColumn(name="domain_id",nullable = true)
	private Domain domain;

	@Column(name = "name", nullable = false, length = 191)
	private String name;

	@Column(name = "email", nullable = false, length = 191, unique = true) // Assuming email should be unique
	private String email;

	@Column(name = "is_deleted", nullable = false,columnDefinition = "TINYINT(4)")
	private Boolean isDeleted = false;

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "password", nullable = false, length = 191)
	private String password;


	@Column(name = "role", nullable = false, columnDefinition = "ENUM('1', '2') DEFAULT '2'")
	private UserRole role; // Create an enum for user roles

	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name="created_by")
	private Integer created_by;

	@Column(name="updated_by")
	private Integer updated_by;

	@Transient  // This ensures the field isn't mapped to the database
	@JsonProperty("domainId")
	public Integer getDomainId() {
		return domain != null ? domain.getId() : null;
	}

	// This will be used when receiving domain ID in requests
	public void setDomainId(Integer domainId) {
		if (domainId != null) {
			Domain newDomain = new Domain();
			newDomain.setId(domainId);
			this.domain = newDomain;
		} else {
			this.domain = null;
		}
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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



}
