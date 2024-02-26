package com.sobieraj.BankApp.Entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountTransactions {
	@Id
	private String transactionId;
	private long accountNumber;
	private int customerId;
	private Date transactionDate;
	private String transactionSummary;
	private String transactionType;
	private int transactionAmount;
	
	

}
