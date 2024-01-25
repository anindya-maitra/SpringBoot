package com.example.eurekarserver.repo;

import java.util.List;

import com.example.eurekarserver.exception.AccountNotFoundException;
import com.example.eurekarserver.model.Account;

public interface AccountRepository {
	
	Account insertAccount(Account account);
	
	List<Account> getAllAccount();
	
	Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
	
	Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException;
	
	void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
	
	Account getAccountByEmail(String email) throws AccountNotFoundException;
}
