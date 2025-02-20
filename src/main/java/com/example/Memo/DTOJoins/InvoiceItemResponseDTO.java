package com.example.Memo.DTOJoins;

public class InvoiceItemResponseDTO {
    private InvoiceDTO invoiceDTO;
    private TaxDTO taxDTO;
    private InvoiceItemDTO invoiceItemDTO;
    public InvoiceDTO getInvoiceDTO() {
        return invoiceDTO;
    }
    public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
        this.invoiceDTO = invoiceDTO;
    }
    public TaxDTO getTaxDTO() {
        return taxDTO;
    }
    public void setTaxDTO(TaxDTO taxDTO) {
        this.taxDTO = taxDTO;
    }
    public InvoiceItemDTO getInvoiceItemDTO() {
        return invoiceItemDTO;
    }
    public void setInvoiceItemDTO(InvoiceItemDTO invoiceItemDTO) {
        this.invoiceItemDTO = invoiceItemDTO;
    }


    

}
