package com.sobieraj.BankApp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.Account;
import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Repositories.BankAccountRepository;
import com.sobieraj.BankApp.Repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

@Service
public class BankAccountService {
	
	@Autowired
	BankAccountRepository bankAccountRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	public boolean createBankAccount(String username, Account account) {
		Optional<Customer> customer = customerRepo.findCustomerByUsername(username);
		
		//More logic to find if multiple accounts are created
		
		if(customer.isPresent()) {
			account.setCustomer(customer.get());
			bankAccountRepo.save(account);
			customer.get().getAccounts().add(account);
			customerRepo.save(customer.get());
			return true;
		}
		
		return false;
	}
	
	public boolean saveBankAccount(Account account) {
		bankAccountRepo.save(account);
		return true;
	}
	
	
	public List<Account> getCustomerAccounts(String username) {
		Optional<Customer> customer = customerRepo.findCustomerByUsername(username);

		if(customer.isPresent()) {
			return customer.get().getAccounts();
			}
		return null;
		
	}

	public void updateBalance(Long accountNumber, Float transactionAmount, HttpSession session) {
		String username = (String) session.getAttribute("username");
		Optional<Customer> customer = customerRepo.findCustomerByUsername(username);
		Optional<Account> account = bankAccountRepo.findById(accountNumber);
		
		if(account.isPresent() && customer.isPresent()) {
			account.get().setBalance(account.get().getBalance() + transactionAmount);
			bankAccountRepo.save(account.get());
			customerRepo.save(customer.get());
			session.setAttribute("bankAccounts", customer.get().getAccounts());
		}

		
	}

	public void delete(String username, Long accountId) {
		Optional<Account> account = bankAccountRepo.findById(accountId);
		Customer customer = account.get().getCustomer();
		customer.getAccounts().remove(account.get());
		customerRepo.save(customer);
		
	}

}
