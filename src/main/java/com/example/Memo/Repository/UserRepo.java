package com.example.Memo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.Customer;

public interface UserRepo extends JpaRepository<CustomUser, Integer> {

	CustomUser findByEmail(String email);
	CustomUser findByName(String name);
	List<CustomUser> findByDomainIsNotNull();
	@Query("SELECT U FROM CustomUser U " +
	           "LEFT JOIN FETCH U.domain d " +
	           // Add other joins as needed
	           "WHERE U.isDeleted = false")
	    List<CustomUser> findAllCustomUsersWithRelations();


}
