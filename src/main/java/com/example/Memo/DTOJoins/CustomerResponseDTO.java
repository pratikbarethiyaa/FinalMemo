package com.example.Memo.DTOJoins;

public class CustomerResponseDTO {
	
	private CustomerData customerData;
	public CustomerData getCustomerData() {
		return customerData;
	}
	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}
	public DomainData getDomainData() {
		return domainData;
	}
	public void setDomainData(DomainData domainData) {
		this.domainData = domainData;
	}
	private DomainData domainData;
	
}
