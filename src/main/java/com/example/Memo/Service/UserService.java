package com.example.Memo.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Memo.DTOJoins.DomainData;
import com.example.Memo.DTOJoins.UserData;
import com.example.Memo.DTOJoins.UserResponseDTO;
import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.Domain;
import com.example.Memo.Repository.DomainRepo;
import com.example.Memo.Repository.UserRepo;


@Service
public class UserService implements UserDetailsService {


	Logger log=LoggerFactory.getLogger(UserService.class);
	private UserRepo repo;
	private PasswordEncoder encoder;
	private DomainRepo domainRepo;
	public UserService(@Lazy   UserRepo repo, @Lazy  PasswordEncoder encoder, @Lazy DomainRepo domainRepo) {
		this.repo=
				repo;
		this.encoder=encoder;
		this.domainRepo=domainRepo;
	}


	public List<UserResponseDTO> getAllUsers() {

		// TODO Auto-generated method stub
		log.info("All users request recevied at service");
		try {

			List<CustomUser> users= repo.findAllCustomUsersWithRelations();
			return users.stream()
		            .map(this::convertToDTO)
		            .collect(Collectors.toList());
		}
		catch(Exception e) {
			log.info("The Following exception occured at User Service while fetching all Users"+e.getMessage());
			return null;
		}

	}



	public boolean addNewUser(CustomUser user) {
		// TODO Auto-generated method stub
		String passwordString = user.getPassword();
		String securedPassword = encoder.encode(passwordString); // Hash the password
		if(user.getDomainId()!=null) {
			Optional<Domain> domain=domainRepo.findById(user.getDomainId());

			if(domain.isPresent()) {
				Domain addDomain=domain.get();
				user.setDomain(addDomain);
			}
		}


		user.setPassword(securedPassword); // Set the hashed password
		// Set additional fields for user creation
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		try {
			log.info("Trying to save the user at service layer");
			repo.save(user);
			return true;
		}
		catch(Exception e) {
			log.info("The following exception occured during saving the user in database"+e.getMessage());
			return false;
		}



	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			CustomUser user=repo.findByName(username);
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
		}
		catch(UsernameNotFoundException e) {
			throw new UsernameNotFoundException("No user found with this username");
		}


	}
	
	
	
	private UserResponseDTO convertToDTO(CustomUser user) {
		UserResponseDTO dto = new UserResponseDTO();
        
		UserData userObject=new UserData();
		userObject.setName(user.getName());
		userObject.setEmail(user.getEmail());
		userObject.setId(user.getId());
		userObject.setPassword(user.getPassword());
		userObject.setRole(user.getRole());
        
        // ... map other customer fields
        
        // Map related data
        if (user.getDomain() != null) {
            DomainData domainDTO = new DomainData();
            domainDTO.setId(user.getDomain().getId());
            domainDTO.setAddress(user.getDomain().getAddress());
            domainDTO.setBankaccno(user.getDomain().getBankAccNo());
            domainDTO.setBankname(user.getDomain().getBankname());
            domainDTO.setCustomer_prefix(user.getDomain().getCustomerPrefix());
            domainDTO.setEmail(user.getDomain().getEmail());
            domainDTO.setGstinno(user.getDomain().getGstinno());
            domainDTO.setIfsc(user.getDomain().getIfsc());
            domainDTO.setInvoice_prefix(user.getDomain().getInvoicePrefix());
            domainDTO.setLandline(user.getDomain().getLandline());
            domainDTO.setLogo(user.getDomain().getLogo());
            domainDTO.setLogo2(user.getDomain().getLogo2());
            domainDTO.setMobile(user.getDomain().getMobile());
            domainDTO.setMobile2(user.getDomain().getMobile2());
            domainDTO.setName(user.getDomain().getName());
            domainDTO.setPan_no(user.getDomain().getPanNo());
            domainDTO.setWebsite(user.getDomain().getWebsite());
            dto.setDomainData(domainDTO);
            
            
            // ... map other domain fields
            
        }
        // ... map other related data
        
        dto.setUserData(userObject);
        
        
        return dto;
    }



}
