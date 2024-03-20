package com.sobieraj.BankApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sobieraj.BankApp.Entities.TransactionDetails;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetails, Long> {
	
	TransactionDetails findAllByTranId(Long id);
}
