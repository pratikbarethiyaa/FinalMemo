package com.example.Memo.Controller;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.Memo.Model.Customer;
import com.example.Memo.Service.CustomerService;
import com.example.Memo.DTOJoins.*;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	Logger log=LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	public CustomerService service;
	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers(){
		List<CustomerResponseDTO> allCustomers=service.getAllCustomers();
		if(allCustomers!=null) {
			return ResponseEntity.ok(allCustomers);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Integer id){
		CustomerResponseDTO response=service.getCustomerById(id);
		if(response!=null) {
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such customer exists with that id");
		}
		
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer newCustomer){
		log.info("New Customer Entry is"+newCustomer.getFname());

		Customer savedCustomer=service.addCustomer(newCustomer);
		log.info("SavedCustomer is "+savedCustomer.getFname());
		if(savedCustomer.getId()==null) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot create customer"+ new Customer());
		}else {
			return ResponseEntity.status(HttpStatus.CREATED) .body("Cust"
					+ "omer created successfully with ID: "+savedCustomer.getId());
		}
	}

	@PutMapping("/updatedCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable int id,@RequestBody Customer changedCustomer){
		log.info("REquest entered at update customer api with id "+id+"and data as"+changedCustomer);
		Customer updatedCustomer=service.modifyCustomer(id,changedCustomer);
		log.info("Modified customer in controller is"+updatedCustomer);
		if(updatedCustomer==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customer exists with that ID");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer updated with given data"+updatedCustomer);
		}

	}

	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id){
		log.info("Delete Request recevied at controller with id"+id);
		boolean status=service.deleteCustomerById(id);
		if(status) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("The entry removed successfully");
		}else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such entry exists in database");
		}
	}

}
