package com.example.Memo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Memo.Model.Invoice;
import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

    // Fetch invoices that are not soft deleted
    @Query("SELECT i FROM Invoice i WHERE i.isDeleted = false")
    List<Invoice> findAllActiveInvoices();

    @Query("SELECT i FROM Invoice i " +
       "LEFT JOIN FETCH i.customer c " +
       "LEFT JOIN FETCH i.domain d " +
       "WHERE i.isDeleted = false")
List<Invoice> findAllActiveInvoicesWithCustomerAndDomain();

@Query("SELECT i FROM Invoice i " +
"LEFT JOIN FETCH i.customer c " +
"LEFT JOIN FETCH i.domain d " +
"WHERE i.isDeleted = false AND i.id=:id ")
Invoice findActiveInvoicesWithCustomerAndDomain(@Param("id")Integer id);
}
