package com.example.Memo.DTOJoins;

import com.example.Memo.Model.CustomUser;
import com.example.Memo.Model.Domain;

import lombok.Data;


public class UserResponseDTO {

	private UserData userData;
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	public DomainData getDomainData() {
		return domainData;
	}
	public void setDomainData(DomainData domainData) {
		this.domainData = domainData;
	}
	private DomainData domainData;
	
}
