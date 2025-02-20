package com.example.Memo.Service;

import com.example.Memo.DTOJoins.CustomerDTO;
import com.example.Memo.DTOJoins.DomainDTO;
import com.example.Memo.DTOJoins.InvoiceDTO;
import com.example.Memo.DTOJoins.InvoiceResponseDTO;
import com.example.Memo.Model.Customer;
import com.example.Memo.Model.Domain;
import com.example.Memo.Model.Invoice;
import com.example.Memo.Repository.InvoiceRepo;
import com.example.Memo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Memo.Repository.CustomerRepo;
import com.example.Memo.Repository.DomainRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class InvoiceService {

    @Autowired
    private CustomerRepo customerRepository;

    @Autowired
    private DomainRepo domainRepository;

    private InvoiceRepo invoiceRepo;

    public InvoiceService(InvoiceRepo invoiceRepo) {

        this.invoiceRepo = invoiceRepo;
    }

    // Retrieve all active (non-deleted) invoices
    public List<InvoiceResponseDTO> getAllInvoices() {
        List<Invoice> obj=invoiceRepo.findAllActiveInvoicesWithCustomerAndDomain();
        return obj.stream()
	            .map(this::invoiceResponseDTO)
	            .collect(Collectors.toList());
    }

    // Retrieve an invoice by its ID if it is not marked as deleted
    public InvoiceResponseDTO getInvoiceById(Integer id) {
        Invoice invoice=invoiceRepo.findActiveInvoicesWithCustomerAndDomain(id);
        if(invoice!=null){
            InvoiceResponseDTO invoiceResponseDTO=invoiceResponseDTO(invoice);
            return invoiceResponseDTO;
        }else{
            return null;
        }
    }

    // // Create a new invoice
    // public Invoice createInvoice(Invoice invoice) {
    //     invoice.setCreatedAt(new Date());
    //     invoice.setUpdatedAt(new Date());
    //     return invoiceRepo.save(invoice);
    // }

//     public Invoice createInvoice(Invoice invoice, Integer customerId, Integer domainId) {
//     Customer customer = customerRepository.findById(customerId)
//             .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
//     Domain domain = domainRepository.findById(domainId)
//             .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + domainId));
//     invoice.setCustomer(customer);
//     invoice.setDomain(domain);
//     invoice.setCreatedAt(new Date());
//     invoice.setUpdatedAt(new Date());
//     return invoiceRepo.save(invoice);
// }
// public Invoice createInvoice(Invoice invoice) {
//     // Now use the associated Customer and Domain objects
//     if (invoice.getCustomer() == null || invoice.getCustomer().getId() == null) {
//         throw new IllegalArgumentException("Customer must be provided with a valid id.");
//     }
//     if (invoice.getDomain() == null || invoice.getDomain().getId() == null) {
//         throw new IllegalArgumentException("Domain must be provided with a valid id.");
//     }

//     System.out.println("Customer ID: " + invoice.getCustomer().getId());
//     System.out.println("Domain ID: " + invoice.getDomain().getId());

//     invoice.setCreatedAt(new Date());
//     invoice.setUpdatedAt(new Date());
//     return invoiceRepo.save(invoice);
// }
public Invoice createInvoice(Invoice invoice) {
    if (invoice.getCustomer() == null || invoice.getCustomer().getId() == null) {
        throw new IllegalArgumentException("Customer must be provided with a valid id.");
    }
    if (invoice.getDomain() == null || invoice.getDomain().getId() == null) {
        throw new IllegalArgumentException("Domain must be provided with a valid id.");
    }

    // Fetch customer and domain from the database
    Customer customer = customerRepository.findById(invoice.getCustomer().getId())
            .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + invoice.getCustomer().getId() + " not found."));
    
    Domain domain = domainRepository.findById(invoice.getDomain().getId())
            .orElseThrow(() -> new IllegalArgumentException("Domain with ID " + invoice.getDomain().getId() + " not found."));

    // Set the fetched entities to ensure they are managed
    invoice.setCustomer(customer);
    invoice.setDomain(domain);

    invoice.setCreatedAt(new Date());
    invoice.setUpdatedAt(new Date());

    return invoiceRepo.save(invoice);
}

    // Update an existing invoice
    public Invoice updateInvoice(Integer id, Invoice updatedInvoice) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        
        // Update the associations if they are provided in updatedInvoice
        // (If you want to allow changing the customer or domain for an invoice.)
        if (updatedInvoice.getCustomer() != null && updatedInvoice.getCustomer().getId() != null) {
            // Optionally, you might want to retrieve the Customer from the database before setting it
            invoice.setCustomer(updatedInvoice.getCustomer());
        }
        if (updatedInvoice.getDomain() != null && updatedInvoice.getDomain().getId() != null) {
            // Optionally, you might want to retrieve the Domain from the database before setting it
            invoice.setDomain(updatedInvoice.getDomain());
        }
        
        invoice.setVoucherNo(updatedInvoice.getVoucherNo());
        invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
        invoice.setTotal(updatedInvoice.getTotal());
        invoice.setTaxPrice(updatedInvoice.getTaxPrice());
        invoice.setGrandTotal(updatedInvoice.getGrandTotal());
        invoice.setAdvanceAmount(updatedInvoice.getAdvanceAmount());
        invoice.setDiscount(updatedInvoice.getDiscount());
        invoice.setPaytype(updatedInvoice.getPaytype());
        invoice.setStatus(updatedInvoice.getStatus());
        invoice.setCarting(updatedInvoice.getCarting());
        invoice.setNote(updatedInvoice.getNote());
        invoice.setDescription(updatedInvoice.getDescription());
        invoice.setUpdatedBy(updatedInvoice.getUpdatedBy());
        invoice.setUpdatedAt(new Date());
        
        return invoiceRepo.save(invoice);
    }
    

    // Soft delete an invoice by marking it as deleted and updating the timestamp
    public void softDeleteInvoice(Integer id) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        invoice.setDeleted(true);
        invoice.setUpdatedAt(new Date());
        invoiceRepo.save(invoice);
    }

    private InvoiceResponseDTO invoiceResponseDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        
        // Map customer data
        CustomerDTO customerData = new CustomerDTO();
        customerData.setId(invoice.getCustomer().getId());
        customerData.setClient_no(invoice.getCustomer().getClientNo());
        customerData.setFname(invoice.getCustomer().getFname());
        customerData.setLname(invoice.getCustomer().getLname());
        customerData.setAddress(invoice.getCustomer().getAddress());
        customerData.setCustomer_name(invoice.getCustomer().getCustomerName());
        customerData.setEmail(invoice.getCustomer().getEmail());
        customerData.setGstinNo(invoice.getCustomer().getGstinNo());
        customerData.setMobile_no(invoice.getCustomer().getContact());
        customerData.setWhatsapp_no(invoice.getCustomer().getWhatsapp_no());
        
        // ... map other customer fields
        
        // Map related data
        DomainDTO relatedData = new DomainDTO();
        if (invoice.getDomain() != null) {
            DomainDTO domainDTO = new DomainDTO();
            domainDTO.setId(invoice.getDomain().getId());
            domainDTO.setAddress(invoice.getDomain().getAddress());
            domainDTO.setBankaccno(invoice.getDomain().getBankAccNo());
            domainDTO.setBankname(invoice.getDomain().getBankname());
            domainDTO.setCustomer_prefix(invoice.getDomain().getCustomerPrefix());
            domainDTO.setEmail(invoice.getDomain().getEmail());
            domainDTO.setGstinno(invoice.getDomain().getGstinno());
            domainDTO.setIfsc(invoice.getDomain().getIfsc());
            domainDTO.setInvoice_prefix(invoice.getDomain().getInvoicePrefix());
            domainDTO.setLandline(invoice.getDomain().getLandline());
            domainDTO.setLogo(invoice.getDomain().getLogo());
            domainDTO.setLogo2(invoice.getDomain().getLogo2());
            domainDTO.setMobile(invoice.getDomain().getMobile());
            domainDTO.setMobile2(invoice.getDomain().getMobile2());
            domainDTO.setName(invoice.getDomain().getName());
            domainDTO.setPan_no(invoice.getDomain().getPanNo());
            domainDTO.setWebsite(invoice.getDomain().getWebsite());
            dto.setDomainDTO(domainDTO);
            
            
            // ... map other domain fields
            
        }
        // ... map other related data
        
        dto.setCustomerDTO(customerData);
        
        InvoiceDTO invoiceDTO=new InvoiceDTO();
        invoiceDTO.setDescription(invoice.getDescription());
        invoiceDTO.setAdvanceAmount(invoice.getAdvanceAmount());
        invoiceDTO.setCarting(invoice.getCarting());
        invoiceDTO.setDiscount(invoice.getDiscount());
        invoiceDTO.setGrandTotal(invoice.getGrandTotal());
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());
        invoiceDTO.setNote(invoice.getNote());
        invoiceDTO.setPaytype(invoice.getPaytype());
        invoiceDTO.setStatus(invoice.getStatus());
        invoiceDTO.setTaxPrice(invoice.getTaxPrice());
        invoiceDTO.setTotal(invoice.getTotal());
        invoiceDTO.setVoucherNo(invoice.getVoucherNo());
        dto.setInvoiceDTO(invoiceDTO);

        return dto;
    }
}
