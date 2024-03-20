package com.sobieraj.BankApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sobieraj.BankApp.Entities.Transaction;

public interface Transactions extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllTransactionByAccountNumber(Long accountNumber);
	List<Transaction> findAllTransactionByCcid(Integer id);

	Transaction findTransactionByTranId(Long id);
	
	
}
