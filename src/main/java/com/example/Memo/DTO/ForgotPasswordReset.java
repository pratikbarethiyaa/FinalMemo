package com.example.Memo.DTO;

import lombok.Data;


public class ForgotPasswordReset {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
