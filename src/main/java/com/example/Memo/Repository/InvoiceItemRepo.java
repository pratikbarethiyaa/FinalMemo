package com.example.Memo.Repository;

import org.eclipse.angus.mail.handlers.image_gif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Memo.Model.Invoice;
import com.example.Memo.Model.InvoiceItem;
import java.util.List;

@Repository
public interface InvoiceItemRepo extends JpaRepository<InvoiceItem, Integer> {
    List<InvoiceItem> findByIsDeletedFalse(); // Fetch only non-deleted items
    List<InvoiceItem> findByTaxId(Integer taxId);

        @Query("SELECT it FROM InvoiceItem it " +
       "LEFT JOIN FETCH it.invoice i " +
       "LEFT JOIN FETCH it.tax t " +
       "WHERE it.isDeleted = false")
List<InvoiceItem> findAllActiveInvoiceItemwwithInvoiceandTax();

@Query("SELECT it FROM InvoiceItem it " +
"LEFT JOIN FETCH it.invoice i " +
"LEFT JOIN FETCH it.tax t " +
"WHERE it.isDeleted = false AND it.id=:id")
InvoiceItem findActiveInvoiceItemwwithInvoiceandTax(@Param("id") Integer id);


}
