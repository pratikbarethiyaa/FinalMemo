package com.example.Memo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Memo.Model.ResetPassword;


public interface PasswordResetRepository extends JpaRepository<ResetPassword, Integer> {

	ResetPassword findByEmail(String email);

}
