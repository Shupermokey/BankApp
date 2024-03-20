package com.sobieraj.BankApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.BankApp.Entities.CreditCard;
import com.sobieraj.BankApp.Entities.Transaction;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

	
	
}
