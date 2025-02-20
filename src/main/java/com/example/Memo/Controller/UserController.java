package com.example.Memo.Controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Memo.JWTIMPL.JwtUtil;
import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.credentials;
import com.example.Memo.Service.UserService;
import com.example.Memo.DTOJoins.*;
@RestController
@RequestMapping("/auth")
public class UserController {
	Logger log=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService service;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getUsers(){
		List<UserResponseDTO> users=service.getAllUsers();
		if(users!=null) {

			return ResponseEntity.status(HttpStatus.FOUND).body(users);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO Users exists");
		}
	}


	//Add New user,Registration
	@PostMapping("/register")
	public ResponseEntity<String>addUser(@RequestBody CustomUser user){
		log.info("Request Recveid to add new user in controller with data"+user);
		boolean result=service.addNewUser(user);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).body("new User Registered with id"+user.getId());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add User please enter data properly");
		}
	}

	//api to logging in
	@GetMapping("/login")
	public ResponseEntity<String> validateLogin(@RequestBody credentials data){
		try {
			// Authenticate user using AuthenticationManager
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword())
					);

			// If authentication is successful, generate JWT token
			String token = jwtUtil.generateToken(data.getName()); // Assuming username is used for token generation
			return ResponseEntity.ok(token); // Return the token in the response body

		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Either Username or password is incorrect, please try again.");
		}
	}

}
