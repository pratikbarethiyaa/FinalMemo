package com.example.Memo.Service;

import com.example.Memo.DTOJoins.CustomerDTO;
import com.example.Memo.DTOJoins.DomainDTO;
import com.example.Memo.DTOJoins.InvoiceDTO;
import com.example.Memo.DTOJoins.InvoiceItemDTO;
import com.example.Memo.DTOJoins.InvoiceItemResponseDTO;
import com.example.Memo.DTOJoins.InvoiceResponseDTO;
import com.example.Memo.DTOJoins.TaxDTO;
import com.example.Memo.Model.Invoice;
import com.example.Memo.Model.InvoiceItem;
import com.example.Memo.Model.Taxes;
import com.example.Memo.Repository.InvoiceItemRepo;
import com.example.Memo.Repository.InvoiceRepo;
import com.example.Memo.Repository.TaxesRepository;
import com.example.Memo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceItemService {

    private InvoiceItemRepo invoiceItemRepo;
    private InvoiceRepo invoiceRepo;
    private TaxesRepository taxesRepository;

    public InvoiceItemService(InvoiceItemRepo invoiceItemRepo, InvoiceRepo invoiceRepo, TaxesRepository taxesRepository) {
        this.invoiceItemRepo = invoiceItemRepo;
        this.invoiceRepo = invoiceRepo;
        this.taxesRepository = taxesRepository;
    }

    // Retrieve all active (non-deleted) invoice items
    public List<InvoiceItemResponseDTO> getAll() {

        List<InvoiceItem> itlist = invoiceItemRepo.findAllActiveInvoiceItemwwithInvoiceandTax();
            return itlist.stream()
	            .map(this::invoiceItemResponseDTO)
	            .collect(Collectors.toList());    }

    // Retrieve an invoice item by its ID (only if not marked as deleted)
    public InvoiceItemResponseDTO getById(Integer id) {
        InvoiceItem item = invoiceItemRepo.findActiveInvoiceItemwwithInvoiceandTax(id);
        if(item!=null){
            InvoiceItemResponseDTO invoiceItemResponseDTO = invoiceItemResponseDTO(item);
            return invoiceItemResponseDTO;
        }else{
            return null;
        }
    }

    // /**
    //  * Save a new invoice item using the provided invoiceId and taxId.
    //  *
    //  * @param invoiceId the id of the invoice (Long, will be converted to Integer)
    //  * @param taxId     the id of the tax (Long) or null if not applicable
    //  * @param invoiceItem the invoice item to save
    //  * @return the saved invoice item
    //  */
    // public InvoiceItem save(Long invoiceId, Integer taxId, InvoiceItem invoiceItem) {
    //     // Convert invoiceId from Long to Integer before fetching the Invoice
    //     Invoice invoice = invoiceRepo.findById(invoiceId.intValue())
    //             .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + invoiceId));
    //     invoiceItem.setInvoice(invoice);

    //     // If a taxId is provided, find the Taxes entity and link it
    //     if (taxId != null) {
    //         Taxes tax = taxesRepository.findById(taxId)
    //                 .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + taxId));
    //         invoiceItem.setTax(tax);
    //     } else {
    //         invoiceItem.setTax(null);
    //     }
        
    //     invoiceItem.setCreatedAt(LocalDateTime.now());
    //     invoiceItem.setUpdatedAt(LocalDateTime.now());
    //     return invoiceItemRepo.save(invoiceItem);
    // }

    // /**
    //  * Overloaded save method that accepts only an InvoiceItem.
    //  * It extracts the Invoice id and Taxes id (if available) from the InvoiceItem and then calls the above save method.
    //  *
    //  * @param invoiceItem the invoice item to save (must have its Invoice with a non-null id)
    //  * @return the saved invoice item
    //  */
    // public InvoiceItem save(InvoiceItem invoiceItem) {
    //     if (invoiceItem.getInvoice() == null || invoiceItem.getInvoice().getId() == null) {
    //         throw new IllegalArgumentException("Invoice must be provided with a non-null id.");
    //     }
    //     Long invoiceId = invoiceItem.getInvoice().getId().longValue();
    //     Integer taxId = null;
    //     if (invoiceItem.getTax() != null && invoiceItem.getTax().getId() != null) {
    //         taxId = invoiceItem.getTax().getId();
    //     }
    //     return save(invoiceId, taxId, invoiceItem);
    // }

    //
    /**
     * Save a new invoice item using the provided invoiceId and taxId.
     *
     * @param invoiceId   the id of the invoice (Long, will be converted to Integer)
     * @param taxId       the id of the tax (Integer) or null if not applicable
     * @param invoiceItem the invoice item to save
     * @return the saved invoice item
     */
    public InvoiceItem save(Integer invoiceId, Integer taxId, InvoiceItem invoiceItem) {
        // Ensure the invoice exists in the database
        Invoice invoice = invoiceRepo.findById(invoiceId.intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + invoiceId));

        invoiceItem.setInvoice(invoice);

        // If a taxId is provided, ensure the tax entity exists and link it
        if (taxId != null) {
            Taxes tax = taxesRepository.findById(taxId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + taxId));
            invoiceItem.setTax(tax);
        } else {
            invoiceItem.setTax(null);
        }

        invoiceItem.setCreatedAt(LocalDateTime.now());
        invoiceItem.setUpdatedAt(LocalDateTime.now());
        
        return invoiceItemRepo.save(invoiceItem);
    }

    /**
     * Save an InvoiceItem by extracting the Invoice id and Tax id (if available).
     *
     * @param invoiceItem the invoice item to save (must have its Invoice with a non-null id)
     * @return the saved invoice item
     */
    public InvoiceItem save(InvoiceItem invoiceItem) {
        if (invoiceItem.getInvoice() == null || invoiceItem.getInvoice().getId() == null) {
            throw new IllegalArgumentException("Invoice must be provided with a non-null id.");
        }

        // Debugging log: Check if the invoice ID is being received correctly
        // System.out.println("Received Invoice ID: " + invoiceItem.getInvoice().getId());

        Integer invoiceId = invoiceItem.getInvoice().getId().intValue();
        Integer taxId = null;
        if (invoiceItem.getTax() != null && invoiceItem.getTax().getId() != null) {
            taxId = invoiceItem.getTax().getId();
        }
        
        return save(invoiceId, taxId, invoiceItem);
    }

    // Update an existing invoice item
    public InvoiceItem update(Integer id, InvoiceItem updatedItem) {
        InvoiceItem existingItem = invoiceItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceItem not found with id: " + id));

        // Update basic fields
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setQty(updatedItem.getQty());
        existingItem.setUnitPrice(updatedItem.getUnitPrice());
        existingItem.setTaxPrice(updatedItem.getTaxPrice());
        existingItem.setTotal(updatedItem.getTotal());
        existingItem.setUpdatedBy(updatedItem.getUpdatedBy());
        existingItem.setUpdatedAt(LocalDateTime.now());

        // Update the Tax link if provided in updatedItem
        if (updatedItem.getTax() != null && updatedItem.getTax().getId() != null) {
            Taxes tax = taxesRepository.findById(updatedItem.getTax().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + updatedItem.getTax().getId()));
            existingItem.setTax(tax);
        }

        return invoiceItemRepo.save(existingItem);
    }

    // Soft delete an invoice item by marking it as deleted and updating the timestamp
    public void softDelete(Integer id) {
        InvoiceItem item = invoiceItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceItem not found with id: " + id));
        item.setIsDeleted(true);
        item.setUpdatedAt(LocalDateTime.now());
        invoiceItemRepo.save(item);
    }

    private InvoiceItemResponseDTO invoiceItemResponseDTO(InvoiceItem invoiceItem) {
        InvoiceItemResponseDTO dto = new InvoiceItemResponseDTO();
        
        // Map InvoiceItem data
        if(invoiceItem!=null){
        InvoiceItemDTO invoiceItemData = new InvoiceItemDTO();
        invoiceItemData.setDescription(invoiceItem.getDescription());
        invoiceItemData.setId(invoiceItem.getId());
        invoiceItemData.setQty(invoiceItem.getQty());    
        invoiceItemData.setTaxPrice(invoiceItem.getTaxPrice());
        invoiceItemData.setTotal(invoiceItem.getTotal());
        invoiceItemData.setUnitPrice(invoiceItem.getUnitPrice());
        dto.setInvoiceItemDTO(invoiceItemData);
        }
        
        //Map Tax data

        TaxDTO taxDTO = new TaxDTO();
        if(invoiceItem.getTax()!=null){
        Taxes tax = invoiceItem.getTax();
        taxDTO.setCgst(tax.getCgst());
        taxDTO.setSgst(tax.getSgst());
        taxDTO.setIgst(tax.getIgst());
        taxDTO.setDescription(tax.getDescription());
        taxDTO.setEffectiveFrom(tax.getEffectiveFrom());
        taxDTO.setEffectiveTill(tax.getEffectiveTill());
        taxDTO.setId(tax.getId());
        taxDTO.setTaxName(tax.getTaxName());
        taxDTO.setTaxNumber(tax.getTaxNumber());
        taxDTO.setTaxRate(tax.getTaxRate());
        dto.setTaxDTO(taxDTO);
        }
        
        //Map Invoice Data
        InvoiceDTO invoiceDTO=new InvoiceDTO();
        if(invoiceItem.getInvoice()!=null){
        Invoice invoice=invoiceItem.getInvoice();
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
        }
        return dto;
    }








}
