package com.sobieraj.BankApp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.BankApp.Entities.CreditCard;
import com.sobieraj.BankApp.Entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Optional<Customer> findCustomerByUsername(String username);
	public List<Customer> findByUsername(String username);
	
}
