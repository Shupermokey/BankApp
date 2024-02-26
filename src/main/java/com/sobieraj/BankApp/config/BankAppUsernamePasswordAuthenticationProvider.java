package com.sobieraj.BankApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Repositories.CustomerRepository;

@Component
public class BankAppUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Customer> customer = customerRepo.findByUsername(username);
		if(customer.size() > 0) {
			if(passwordEncoder.matches(password, customer.get(0).getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username,  password, authorities);
			}
			else {
				throw new BadCredentialsException("Invalid password");
			}
		}
		else {
			throw new BadCredentialsException("No user registered with this detail!");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
