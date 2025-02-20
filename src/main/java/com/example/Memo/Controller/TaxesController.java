package com.example.Memo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Memo.Model.Taxes;
import com.example.Memo.Service.TaxService;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxesController {

    @Autowired
    private TaxService taxService;

    @GetMapping
    public List<Taxes> getAllTaxes() {
        return taxService.getAllTaxes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taxes> getTaxById(@PathVariable Integer id) {
        return ResponseEntity.ok(taxService.getTaxById(id));
    }

    @PostMapping
    public ResponseEntity<Taxes> createTax(@RequestBody Taxes tax) {
        return ResponseEntity.ok(taxService.createTax(tax));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Taxes> updateTax(@PathVariable Integer id, @RequestBody Taxes taxDetails) {
        return ResponseEntity.ok(taxService.updateTax(id, taxDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTax(@PathVariable Integer id) {
        taxService.deleteTax(id);
        return ResponseEntity.ok("Tax record marked as deleted");
    }
}
