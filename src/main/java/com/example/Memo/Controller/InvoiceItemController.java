package com.example.Memo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Memo.DTOJoins.InvoiceItemResponseDTO;
import com.example.Memo.DTOJoins.InvoiceResponseDTO;
import com.example.Memo.Model.InvoiceItem;
import com.example.Memo.Service.InvoiceItemService;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {

    private final InvoiceItemService service;

    public InvoiceItemController(InvoiceItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<InvoiceItemResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
                InvoiceItemResponseDTO invoice = service.getById(id);

        if(invoice==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO invoice with such id is found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(invoice);
        }
    }

    @PostMapping
    public InvoiceItem create(@RequestBody InvoiceItem invoiceItem) {
        return service.save(invoiceItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> update(@PathVariable Integer id, @RequestBody InvoiceItem invoiceItem) {
        try {
            return ResponseEntity.ok(service.update(id, invoiceItem));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Integer id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}