package com.sobieraj.BankApp.Services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.CreditCard;
import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Entities.Transaction;
import com.sobieraj.BankApp.Repositories.CreditCardRepository;
import com.sobieraj.BankApp.Repositories.CustomerRepository;
import com.sobieraj.BankApp.Repositories.Transactions;

import jakarta.servlet.http.HttpSession;

@Service
public class CreditCardService {
	
	@Autowired
	CreditCardRepository creditCardRepo;
	
	@Autowired
	Transactions transRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	public void save(CreditCard creditCard, HttpSession session) {
		String username = (String)session.getAttribute("username");
		Optional<Customer> customer = customerRepo.findCustomerByUsername(username);
		if(customer.get() != null) {
			creditCard.setAmount(10000);
			creditCard.setCreditCardNumber((int)Math.floor(Math.random()*10000000));
			creditCard.setExpirationDate(LocalDateTime.now().plusYears(3));
			creditCard.setCustomerId(customer.get().getCustomerId());
			customer.get().getCreditCards().add(creditCard);
			creditCardRepo.save(creditCard);
			customerRepo.save(customer.get());
			session.setAttribute("creditCards", customer.get().getCreditCards());
		}
		
		
	}
	
	public boolean getTransactions(CreditCard creditCard, HttpSession session) {
		List<Transaction> creditTransactions = transRepo.findAllTransactionByCcid(creditCard.getCcid());
		if(creditTransactions != null) {
			
			session.setAttribute("creditCardsTransactions", creditTransactions);
			return true;
		}
		return false;

		}
	}

