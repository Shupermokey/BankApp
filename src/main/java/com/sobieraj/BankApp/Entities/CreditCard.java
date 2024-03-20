package com.sobieraj.BankApp.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer ccid;
	
	private Integer customerId;
	private int amount;
	private String billingAddress;
	private int creditCardNumber;
	private int owed;
	private LocalDateTime expirationDate;
	
	@OneToMany
	List<Transaction> transactions = new ArrayList<>();
	
	

}
