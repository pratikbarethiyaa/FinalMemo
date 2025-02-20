package com.example.Memo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>   {

	List<Customer> findByDomainIsNotNull();
	@Query("SELECT c FROM Customer c " +
	           "LEFT JOIN FETCH c.domain d " +
	           // Add other joins as needed
	           "WHERE c.isDeleted = false")
	    List<Customer> findAllCustomersWithRelations();
	
	@Query("SELECT c FROM Customer c " +
		       "LEFT JOIN FETCH c.domain d " +
		       "WHERE c.isDeleted = false AND c.id = :id")
		Customer findCustomerWithRelations(@Param("id") Integer id);
}

