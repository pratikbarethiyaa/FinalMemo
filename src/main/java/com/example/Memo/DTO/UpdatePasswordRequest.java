package com.example.Memo.DTO;

public class UpdatePasswordRequest {
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String newPassword) {
		this.Password = newPassword;
	}
	private String Password;
}
