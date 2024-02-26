package com.sobieraj.BankApp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {

	private int customerId;
	
	@Id
	private long accountNumber;
	private String accountType;
	private String branchAddress;
	private String createDate;
}
