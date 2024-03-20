package com.sobieraj.BankApp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Repositories.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginServices {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	TransactionService transactionService;

	
	public boolean accountCreated(String username) {
		Optional<Customer> account = customerRepo.findCustomerByUsername(username);
		if(account.isPresent()) {
			return true;
		}
		
		return false;
	}
	
	public boolean validateCredentials(String username, String password) {
		Optional<Customer> account = customerRepo.findCustomerByUsername(username);
		
		if(account.isPresent()) {
			if(passwordEncoder.matches(password, account.get().getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	//Set the username, bank accounts, and credit cards to the session
	public String login(Customer account, HttpSession session) {
		Optional<Customer> customer = customerRepo.findCustomerByUsername(account.getUsername());
		if(validateCredentials(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			session.setAttribute("bankAccounts", bankAccountService.getCustomerAccounts(account.getUsername()));
			session.setAttribute("creditCards", customer.get().getCreditCards());
			return "homePage";
		}
		else {
			return "loginPage";
		}
	}
	
	public String createAccount(Customer customer) {
		try {
			if(accountCreated(customer.getUsername())) {
				System.out.println("Account is already created");
				return "createAccountPage";
			}
			else {
				Customer newAccount = new Customer();
				newAccount.setUsername(customer.getUsername());
				String pw_hash = passwordEncoder.encode(customer.getPassword());
				newAccount.setPassword(pw_hash);
				newAccount.setEmail(customer.getEmail());
				customerRepo.save(newAccount);
				return "loginPage";
			}
		}catch(Exception e) {
			return "createAccountPage";
		}
		
	}

}
