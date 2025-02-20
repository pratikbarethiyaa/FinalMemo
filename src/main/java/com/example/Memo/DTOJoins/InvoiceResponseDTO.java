package com.example.Memo.DTOJoins;

public class InvoiceResponseDTO {
    private CustomerDTO customerDTO;
    private DomainDTO domainDTO;
    private InvoiceDTO invoiceDTO;
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
    public DomainDTO getDomainDTO() {
        return domainDTO;
    }
    public void setDomainDTO(DomainDTO domainDTO) {
        this.domainDTO = domainDTO;
    }
    public InvoiceDTO getInvoiceDTO() {
        return invoiceDTO;
    }
    public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
        this.invoiceDTO = invoiceDTO;
    }

    
}
