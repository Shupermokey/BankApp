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
	

	
	public boolean accountCreated(String username) {
		Optional<Customer> account = customerRepo.findAccountByUsername(username);
		if(account.isPresent()) {
			return true;
		}
		
		return false;
	}
	
	public boolean validateCredentials(String username, String password) {
		Optional<Customer> account = customerRepo.findAccountByUsername(username);
		
		if(account.isPresent()) {
			if(passwordEncoder.matches(password, account.get().getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public String login(Customer account, HttpSession session) {
		if(validateCredentials(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			return "homePage";
		}
		else {
			
			return "loginPage";
		}
	}
	
	public String createAccount(Customer account) {
		System.out.println(account.getUsername());
		try {
			if(accountCreated(account.getUsername())) {
				System.out.println("Account is already created");
				return "createAccountPage";
			}
			else {
				Customer newAccount = new Customer();
				newAccount.setUsername(account.getUsername());
				String pw_hash = passwordEncoder.encode(account.getPassword());
				newAccount.setPassword(pw_hash);
				newAccount.setEmail(account.getEmail());
				newAccount.setFname(account.getFname());
				newAccount.setLname(account.getLname());
				customerRepo.save(newAccount);
				return "loginPage";
			}
		}catch(Exception e) {
			return "createAccountPage";
		}
		
	}

}
