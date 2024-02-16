package com.sobieraj.BankApp.Entities;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer aid;
	
	@NotBlank(message = "Please enter a valid Username")
	private String username;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email")
	private String email;
	
	@NotEmpty(message = "Please enter a valid password")
	private String password;
	private String fname;
	private String lname;
	
	//private Date dob;
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
