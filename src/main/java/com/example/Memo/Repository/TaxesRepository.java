package com.example.Memo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Memo.Model.Taxes;

@Repository
public interface TaxesRepository extends JpaRepository<Taxes, Integer> {

}

