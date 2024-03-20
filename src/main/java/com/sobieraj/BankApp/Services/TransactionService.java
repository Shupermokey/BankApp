package com.sobieraj.BankApp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.Account;
import com.sobieraj.BankApp.Entities.Transaction;
import com.sobieraj.BankApp.Repositories.Transactions;

@Service
public class TransactionService {
	
	@Autowired
	Transactions transRepo;
	
	
	public List<Transaction> findAllTransactionsByAccountNumber(Long id) {
		
		List<Transaction> transactions = transRepo.findAllTransactionByAccountNumber(id);
		
		if(transactions != null) {
			return transactions;
		}
		
		else return null;
		
	}

	
	public boolean deposit(Transaction transaction) {
		transRepo.save(transaction);
		return true;
	}
}
