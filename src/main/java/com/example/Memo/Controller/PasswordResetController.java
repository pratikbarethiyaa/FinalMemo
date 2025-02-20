package com.example.Memo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Memo.DTO.ForgotPasswordReset;
import com.example.Memo.DTO.MessageResponse;
import com.example.Memo.DTO.UpdatePasswordRequest;
import com.example.Memo.Service.PasswordResetService;

@RestController
@RequestMapping("api/password-reset")
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;
	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordReset request) {
		try {
			passwordResetService.handleForgotPassword(request.getEmail());
			return ResponseEntity.ok(new MessageResponse("Temporary password has been sent to your email"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("User not found with this email"));
		}
	}

	@PostMapping("/update")

	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
		try {
			passwordResetService.updatePassword(request.getEmail(), request.getPassword());
			return ResponseEntity.ok(new MessageResponse("Password updated successfully"));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Failed to update password"));
		}
	}
}
