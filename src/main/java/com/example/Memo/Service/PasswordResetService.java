package com.example.Memo.Service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.ResetPassword;
import com.example.Memo.Repository.PasswordResetRepository;
import com.example.Memo.Repository.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class PasswordResetService {

	Logger log=LoggerFactory.getLogger(PasswordResetService.class);
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordResetRepository passwordResetRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;


	public void handleForgotPassword(String email) {
		// 1. Check if user exists
		try {
			CustomUser user =userRepo.findByEmail(email);
			String randomPassword = generateRandomPassword();

			// 3. Create password reset entry
			ResetPassword passwordReset = new ResetPassword();
			passwordReset.setEmail(email);
			passwordReset.setToken(passwordEncoder.encode(randomPassword));
			passwordReset.setCreatedAt(LocalDateTime.now());
			passwordReset.setUpdatedAt(LocalDateTime.now());
			passwordResetRepository.save(passwordReset);

			// 4. Update user's password in user table

			// 5. Send original (unencoded) password to user's email
			sendTemporaryPassword(email, randomPassword);
			user.setPassword(passwordEncoder.encode(randomPassword));
			userRepo.save(user);

		}catch(Exception e) {
			log.info("No user found with this email ");
			throw e;
		}



	}

	public void updatePassword(String email, String newPassword) {
		// Update in user table
		try {
			CustomUser user =userRepo.findByEmail(email);
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepo.save(user);

			// Update in password_reset table
			try {
				ResetPassword passwordReset = passwordResetRepository.findByEmail(email);
				passwordReset.setToken(passwordEncoder.encode(newPassword));
				passwordReset.setUpdatedAt(LocalDateTime.now());
				passwordResetRepository.save(passwordReset);
			}catch (Exception e) {
				log.info("No Password Reset found with this email"+e);
				throw e;
			}
		}catch(Exception e) {
			log.info("The follwoing exception occured during password update in passored reset service"+e);
			throw e;
		}
	}



	private String generateRandomPassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	private void sendTemporaryPassword(String email, String temporaryPassword) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Your Temporary Password");
		message.setText("Here is your temporary password: " + temporaryPassword + 
				"\nYou can use this to login and then optionally change it to a new password.");
		mailSender.send(message);
	}



}
