package com.example.Memo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Memo.DTOJoins.CustomerData;
import com.example.Memo.DTOJoins.CustomerResponseDTO;
import com.example.Memo.DTOJoins.DomainData;
import com.example.Memo.Model.Customer;
import com.example.Memo.Model.Domain;
import com.example.Memo.Repository.CustomerRepo;
import com.example.Memo.Repository.DomainRepo;
@Service
public class CustomerService {
	Logger log=LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepo repo;

	@Autowired
	private DomainRepo domainRepo;

	public List<CustomerResponseDTO> getAllCustomers() {
		List<Customer> customers=repo.findAllCustomersWithRelations();
		return customers.stream()
	            .map(this::convertToDTO)
	            .collect(Collectors.toList());
	}

	public Customer addCustomer(Customer newCustomer) {
		log.info("New Customer entered in Service layer"+newCustomer.getClientNo());

		if(newCustomer.getDomainId()!=null) {
			Optional<Domain> domain=domainRepo.findById(newCustomer.getDomainId());
			if(domain.isPresent()) {
				Domain domain2=domain.get();
				newCustomer.setDomain(domain2);
			}
		}
		try {
			Customer latestCustomer = repo.save(newCustomer);
			return latestCustomer;
		}
		catch (Exception e) {
			log.info("The Following exception occured"+e.getMessage());
			return null;
		}

	}

	public Customer modifyCustomer(int id,Customer latestCustomer) {

		Optional<Customer> toUpdateCustomer=repo.findById(id);
		if(toUpdateCustomer.isPresent()) {
			try {


				Customer updatedCustomer= toUpdateCustomer.get();
				if(updatedCustomer.getDomainId()!=null) {
					Optional<Domain> updateDomain=domainRepo.findById(updatedCustomer.getDomainId());
					if(updateDomain.isPresent()) {
						Domain domainnew=updateDomain.get();
						updatedCustomer.setDomain(domainnew);
					}
				}
				updatedCustomer.setAddress(latestCustomer.getAddress());
				updatedCustomer.setClientNo(latestCustomer.getClientNo());
				updatedCustomer.setCustomerName(latestCustomer.getCustomerName());
				updatedCustomer.setEmail(latestCustomer.getEmail());
				updatedCustomer.setDomainId(latestCustomer.getDomainId());
				updatedCustomer.setIsDeleted(latestCustomer.getIsDeleted());
				updatedCustomer.setGstinNo(latestCustomer.getGstinNo());
				updatedCustomer.setCreatedAt(LocalDateTime.now());
				updatedCustomer.setUpdatedAt(LocalDateTime.now());
				updatedCustomer.setFname(latestCustomer.getFname());
				updatedCustomer.setLname(latestCustomer.getLname());
				updatedCustomer.setWhatsapp_no(latestCustomer.getWhatsapp_no());
				updatedCustomer.setContact(latestCustomer.getContact());
				updatedCustomer.setCreated_by(latestCustomer.getCreated_by());
				updatedCustomer.setUpdated_by(latestCustomer.getUpdated_by());






				Customer updatedCustomer2=repo.save(updatedCustomer);
				return updatedCustomer2;
			}
			catch(Exception e) {
				log.info("The following exception occured"+e.getMessage());
				return null;
			}
		}
		else {
			return null;
		}

	}

	public boolean deleteCustomerById(int id) {
		Optional<Customer> removeCustomer=repo.findById(id);

		if(!removeCustomer.isEmpty()) {
			log.info("CUstomer exists at database with id"+id);
			repo.deleteById(id);
			return true;
		}else {
			log.info("NO Customer found with this id");
			return false;
		}

	}

	 private CustomerResponseDTO convertToDTO(Customer customer) {
	        CustomerResponseDTO dto = new CustomerResponseDTO();
	        
	        // Map customer data
	        CustomerData customerData = new CustomerData();
	        customerData.setId(customer.getId());
	        customerData.setClient_no(customer.getClientNo());
	        customerData.setFname(customer.getFname());
	        customerData.setLname(customer.getLname());
	        customerData.setAddress(customer.getAddress());
	        customerData.setCustomer_name(customer.getCustomerName());
	        customerData.setEmail(customer.getEmail());
	        customerData.setGstinNo(customer.getGstinNo());
	        customerData.setMobile_no(customer.getContact());
	        customerData.setWhatsapp_no(customer.getWhatsapp_no());
	        
	        // ... map other customer fields
	        
	        // Map related data
	        DomainData relatedData = new DomainData();
	        if (customer.getDomain() != null) {
	            DomainData domainDTO = new DomainData();
	            domainDTO.setId(customer.getDomain().getId());
	            domainDTO.setAddress(customer.getDomain().getAddress());
	            domainDTO.setBankaccno(customer.getDomain().getBankAccNo());
	            domainDTO.setBankname(customer.getDomain().getBankname());
	            domainDTO.setCustomer_prefix(customer.getDomain().getCustomerPrefix());
	            domainDTO.setEmail(customer.getDomain().getEmail());
	            domainDTO.setGstinno(customer.getDomain().getGstinno());
	            domainDTO.setIfsc(customer.getDomain().getIfsc());
	            domainDTO.setInvoice_prefix(customer.getDomain().getInvoicePrefix());
	            domainDTO.setLandline(customer.getDomain().getLandline());
	            domainDTO.setLogo(customer.getDomain().getLogo());
	            domainDTO.setLogo2(customer.getDomain().getLogo2());
	            domainDTO.setMobile(customer.getDomain().getMobile());
	            domainDTO.setMobile2(customer.getDomain().getMobile2());
	            domainDTO.setName(customer.getDomain().getName());
	            domainDTO.setPan_no(customer.getDomain().getPanNo());
	            domainDTO.setWebsite(customer.getDomain().getWebsite());
	            dto.setDomainData(domainDTO);
	            
	            
	            // ... map other domain fields
	            
	        }
	        // ... map other related data
	        
	        dto.setCustomerData(customerData);
	        
	        
	        return dto;
	    }

	public CustomerResponseDTO getCustomerById(Integer id) {
		
		Customer customer=repo.findCustomerWithRelations(id);
		if(customer!=null) {
			CustomerResponseDTO dto=new CustomerResponseDTO();
			
			CustomerData customerData=new CustomerData();
			customerData.setId(customer.getId());
	        customerData.setClient_no(customer.getClientNo());
	        customerData.setFname(customer.getFname());
	        customerData.setLname(customer.getLname());
	        customerData.setAddress(customer.getAddress());
	        customerData.setCustomer_name(customer.getCustomerName());
	        customerData.setEmail(customer.getEmail());
	        customerData.setGstinNo(customer.getGstinNo());
	        customerData.setMobile_no(customer.getContact());
	        customerData.setWhatsapp_no(customer.getWhatsapp_no());
	        dto.setCustomerData(customerData);
	        
	        if(customer.getDomain()!=null) {
	        	DomainData domainDTO= new DomainData();
	        	domainDTO.setId(customer.getDomain().getId());
	            domainDTO.setAddress(customer.getDomain().getAddress());
	            domainDTO.setBankaccno(customer.getDomain().getBankAccNo());
	            domainDTO.setBankname(customer.getDomain().getBankname());
	            domainDTO.setCustomer_prefix(customer.getDomain().getCustomerPrefix());
	            domainDTO.setEmail(customer.getDomain().getEmail());
	            domainDTO.setGstinno(customer.getDomain().getGstinno());
	            domainDTO.setIfsc(customer.getDomain().getIfsc());
	            domainDTO.setInvoice_prefix(customer.getDomain().getInvoicePrefix());
	            domainDTO.setLandline(customer.getDomain().getLandline());
	            domainDTO.setLogo(customer.getDomain().getLogo());
	            domainDTO.setLogo2(customer.getDomain().getLogo2());
	            domainDTO.setMobile(customer.getDomain().getMobile());
	            domainDTO.setMobile2(customer.getDomain().getMobile2());
	            domainDTO.setName(customer.getDomain().getName());
	            domainDTO.setPan_no(customer.getDomain().getPanNo());
	            domainDTO.setWebsite(customer.getDomain().getWebsite());
	            dto.setDomainData(domainDTO);
	        }
	        
			return dto;
			
		           
		}else {
			return null;
		}
	}

}
