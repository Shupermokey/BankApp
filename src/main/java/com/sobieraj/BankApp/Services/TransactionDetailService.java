package com.sobieraj.BankApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobieraj.BankApp.Entities.TransactionDetails;
import com.sobieraj.BankApp.Repositories.TransactionDetailRepository;

@Service
public class TransactionDetailService {
	
	@Autowired
	TransactionDetailRepository tranDetailRepo;
	
	public TransactionDetails findAllTransactionDetailsByTranId(Long id) {

		TransactionDetails tranDetail = tranDetailRepo.findAllByTranId(id);
		if(tranDetail != null) {
			return tranDetail;
		}
		else return null;
		
	}

}
