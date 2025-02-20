package com.example.Memo.Enums;

public enum UserRole {

	USER("1"),
	ADMIN("2");

	private final String value;

	UserRole(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	public static UserRole fromValue(String value) {
		for (UserRole role : UserRole.values()) {
			if (role.value.equals(value)) {
				return role;
			}
		}
		throw new IllegalArgumentException("Unknown value: " + value);
	}
}