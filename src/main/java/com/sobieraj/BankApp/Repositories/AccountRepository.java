package com.sobieraj.BankApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.BankApp.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	public Optional<Account> findAccountByUsername(String username);

}
