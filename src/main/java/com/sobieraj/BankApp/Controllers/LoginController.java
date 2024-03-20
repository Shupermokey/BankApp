package com.sobieraj.BankApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Services.LoginServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	LoginServices loginServices;

	
	@GetMapping("/")
	public String loginPage() {
		return "loginPage";
	}
	
	@PostMapping("/logins")
	public String login(@ModelAttribute("customer") Customer customer, HttpSession session) {

		return loginServices.login(customer, session);
	}
	
	@GetMapping("/homePage")
	public String homePage() {
		return "homePage";
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.GET)
	public String createAccountPage() {
		return "createAccountPage";
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(Customer customer) {
		return loginServices.createAccount(customer);
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "accessDenied";
	}

}
