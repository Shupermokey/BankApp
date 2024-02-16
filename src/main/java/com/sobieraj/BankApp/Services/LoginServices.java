package com.sobieraj.BankApp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.Account;
import com.sobieraj.BankApp.Repositories.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginServices {
	
	@Autowired
	AccountRepository accountRepo;
	

	
	public boolean accountCreated(String username) {
		Optional<Account> account = accountRepo.findAccountByUsername(username);
		if(account.isPresent()) {
			return true;
		}
		
		return false;
	}
	
	public boolean validateCredentials(String username, String password) {
		Optional<Account> account = accountRepo.findAccountByUsername(username);
		
		if(account.isPresent()) {
			if(BCrypt.checkpw(password, account.get().getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public String login(Account account, HttpSession session) {
		if(validateCredentials(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			return "homePage";
		}
		else {
			
			return "loginPage";
		}
	}
	
	public String createAccount(Account account) {
		try {
			if(accountCreated(account.getUsername())) {
				System.out.println("Account is already created");
				return "createAccountPage";
			}
			else {
				Account newAccount = new Account();
				newAccount.setUsername(account.getUsername());
				String pw_hash = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
				newAccount.setPassword(pw_hash);
				newAccount.setEmail(account.getEmail());
				newAccount.setFname(account.getFname());
				newAccount.setLname(account.getLname());
				accountRepo.save(newAccount);
				return "loginPage";
			}
		}catch(Exception e) {
			return "createAccountPage";
		}
		
	}

}
