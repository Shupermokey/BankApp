package com.sobieraj.BankApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.sobieraj.BankApp.Entities.Account;
import com.sobieraj.BankApp.Entities.CreditCard;
import com.sobieraj.BankApp.Entities.Transaction;
import com.sobieraj.BankApp.Entities.TransactionDetails;
import com.sobieraj.BankApp.Services.BankAccountService;
import com.sobieraj.BankApp.Services.CreditCardService;
import com.sobieraj.BankApp.Services.TransactionDetailService;
import com.sobieraj.BankApp.Services.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BankAccountController {
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CreditCardService creditCardService;
	
	@Autowired
	TransactionDetailService tranDetailService;
	
	@GetMapping("/createBankAccount")
	public String createBankAccountPage(Model model) {
		Account account = new Account();
		
		model.addAttribute("bankAccount", account);
		
		return "createBankAccountPage";
		
	}
	
	@PostMapping("/createBankAccount")
	public String createBankAccountPage(@SessionAttribute("username") String username, HttpSession session, @ModelAttribute("bankAccount") Account account) {
		
		if(bankAccountService.saveBankAccount(account)) {
			bankAccountService.createBankAccount(username, account);
			session.setAttribute("bankAccounts", bankAccountService.getCustomerAccounts(username));
			return "redirect:/homePage";
		}
		
		return "createBankAccountPage";
		
	}
	
	@PostMapping("/manageAccount")
	public String manageAccount(HttpSession session, @ModelAttribute("account")Account account, Model model) {
		session.setAttribute("bankAccount", account);
		session.setAttribute("bankTransactions", transactionService.findAllTransactionsByAccountNumber(account.getAccountId()));
		
		return "errorPage";
	}
	
	@GetMapping("/deposit")
	public String depositPage(@RequestParam int accountId, Model model) {
		Transaction tran = new Transaction();
		model.addAttribute("transaction", tran);
		System.out.println("In Deposit with    " + accountId);
		model.addAttribute("accountId", accountId);
		return "deposit";
	}
	
	@PostMapping("/depositAmount")
	public String depositAmount(HttpSession session, Transaction transaction) {
		transactionService.deposit(transaction);
		bankAccountService.updateBalance(transaction.getAccountNumber(), transaction.getTransactionAmount(), session);
		
		return "homePage";
	}
	
	@GetMapping("/deleteAccount/{accountId}")
	public String deleteAccount(@SessionAttribute("username") String username, HttpSession session, @PathVariable Long accountId) {

		bankAccountService.delete(username, accountId);
		session.setAttribute("bankAccounts", bankAccountService.getCustomerAccounts(username));
		
		return "homePage";
	}
	
	@GetMapping("/openCreditCard")
	public String openCreditCard(Model model) {
		CreditCard credit = new CreditCard();
		model.addAttribute("creditCard", credit);
		return "openCreditCardPage";
	}
	
	@PostMapping("/openCreditCard")
	public String openCreditCard(HttpServletRequest request, @ModelAttribute("creditCard") CreditCard creditCard) {
		HttpSession session = request.getSession();
		
		creditCardService.save(creditCard, session);
		
		
		
		return "redirect:/homePage";
	}
	
	@GetMapping("/manageCreditCards")
	public String manageCreditCards(HttpServletRequest request, @ModelAttribute("creditCards") CreditCard creditCard) {
		HttpSession session = request.getSession();
		System.out.println(creditCard);
		if( creditCardService.getTransactions(creditCard, session)) {
			return "manageCreditCard";
		}
		else {
			return "redirect:/homePage";
		}
	}

	@PostMapping("/manageTransaction")
	public String manageTransaction(HttpSession session, @ModelAttribute("transaction")Transaction transaction) {
		TransactionDetails tranDetails = tranDetailService.findAllTransactionDetailsByTranId(transaction.getTranId());
		System.out.println("Here is the info "+tranDetails);
		if(tranDetails!=null) {
			session.setAttribute("transactionDetails", tranDetails);
			return "manageTransaction";
		}
		else {
			return "redirect:/manageAccount";
		}
		
		
	}
	
}
