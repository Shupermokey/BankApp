package com.sobieraj.BankApp.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	private String type;
	private float balance;
	
	@ManyToOne
    @JoinColumn(name = "customer_customer_id")
	private Customer customer;
	
	@OneToMany
	List<Transaction> transactions = new ArrayList<>();
	
	
	
	
	

}
