package com.sobieraj.BankApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.BankApp.Entities.Account;

@Repository
public interface BankAccountRepository extends JpaRepository<Account, Long> {
	
	public void deleteAccountByAccountId(Long id);

}
