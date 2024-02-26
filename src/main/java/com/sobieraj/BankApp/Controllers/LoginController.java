package com.sobieraj.BankApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sobieraj.BankApp.Entities.Customer;
import com.sobieraj.BankApp.Services.LoginServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	LoginServices loginServices;

	
	@GetMapping("/")
	public String loginPage(Model m) {
		return "loginPage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Customer account, HttpServletRequest req) {
		HttpSession session = req.getSession();
		return loginServices.login(account, session);
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.GET)
	public String createAccountPage() {
		return "createAccountPage";
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(Customer account) {
		return loginServices.createAccount(account);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "loginPage";
	}
}
