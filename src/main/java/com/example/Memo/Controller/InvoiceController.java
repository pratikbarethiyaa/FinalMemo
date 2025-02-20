package com.example.Memo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Memo.DTOJoins.InvoiceResponseDTO;
import com.example.Memo.Model.Invoice;
import com.example.Memo.Service.InvoiceService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Integer id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoiceById(id);

        if(invoice==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO invoice with such id is found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(invoice);
        }
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Integer id, @RequestBody Invoice updatedInvoice) {
        try {
            Invoice updated = invoiceService.updateInvoice(id, updatedInvoice);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteInvoice(@PathVariable Integer id) {
        invoiceService.softDeleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
