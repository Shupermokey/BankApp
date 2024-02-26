package com.sobieraj.BankApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Repositories.CustomerRepository;

@RestController
public class LoginRestController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer = null;
		ResponseEntity response = null;
		
		try {
			String hashedPass = passwordEncoder.encode(customer.getPassword());
			customer.setPassword(hashedPass);
			savedCustomer = customerRepo.save(customer);
			if(savedCustomer.getCustomerId() > 0) {
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
		}catch(Exception ex) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}

}
