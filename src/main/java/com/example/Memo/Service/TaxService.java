package com.example.Memo.Service;

import com.example.Memo.Model.Taxes;
import com.example.Memo.Model.InvoiceItem;
import com.example.Memo.Repository.TaxesRepository;
import com.example.Memo.Repository.InvoiceItemRepo;
import com.example.Memo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class TaxService {

    private TaxesRepository taxesRepository;
    private InvoiceItemRepo invoiceItemRepo;

    public TaxService(TaxesRepository taxesRepository, InvoiceItemRepo invoiceItemRepo) {
        this.taxesRepository = taxesRepository;
        this.invoiceItemRepo = invoiceItemRepo;
    }

    // Retrieve all tax records
    public List<Taxes> getAllTaxes() {
        return taxesRepository.findAll();
    }

    // Retrieve a tax record by its ID
    public Taxes getTaxById(Integer id) {
        return taxesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + id));
    }

    // Create a new tax record
    public Taxes createTax(Taxes tax) {
        tax.setCreatedAt(new Date());
        tax.setUpdatedAt(new Date());
        return taxesRepository.save(tax);
    }

    // Update an existing tax record
    public Taxes updateTax(Integer id, Taxes taxDetails) {
        Taxes tax = taxesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + id));
        tax.setTaxName(taxDetails.getTaxName());
        tax.setDescription(taxDetails.getDescription());
        tax.setTaxNumber(taxDetails.getTaxNumber());
        tax.setTaxRate(taxDetails.getTaxRate());
        tax.setEffectiveFrom(taxDetails.getEffectiveFrom());
        tax.setEffectiveTill(taxDetails.getEffectiveTill());
        tax.setDeletedBy(taxDetails.getDeletedBy());  // Make sure getDeletedBy() exists in Taxes
        tax.setDeleted(taxDetails.isDeleted());
        tax.setSgst(taxDetails.getSgst());
        tax.setCgst(taxDetails.getCgst());
        tax.setIgst(taxDetails.getIgst());
        tax.setUpdatedBy(taxDetails.getUpdatedBy());  // Make sure getUpdatedBy() exists in Taxes
        tax.setUpdatedAt(new Date());
        return taxesRepository.save(tax);
    }

    // Soft delete a tax record only if no invoice items are linked to it
    public void deleteTax(Integer id) {
        Taxes tax = taxesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + id));
        List<InvoiceItem> linkedItems = invoiceItemRepo.findByTaxId(id);
        if (linkedItems != null && !linkedItems.isEmpty()) {
            throw new RuntimeException("Cannot delete tax as it is linked to invoice items.");
        }
        tax.setDeleted(true);
        tax.setUpdatedAt(new Date());
        taxesRepository.save(tax);
    }
}
