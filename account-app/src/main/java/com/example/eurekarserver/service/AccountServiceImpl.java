package com.example.eurekarserver.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.eurekarserver.exception.AccountNotFoundException;
import com.example.eurekarserver.exception.MinimumBalanceException;
import com.example.eurekarserver.model.Account;
import com.example.eurekarserver.repo.AccountRepository;

import lombok.AllArgsConstructor;

@Component(value = "accountService")
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;

	@Override
	public Account insertAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.insertAccount(account);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountRepository.getAllAccount();
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByAccountNumber(accountNumber);
	}

	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.updateAccountByAccountNumber(accountNumber, account);
	}

	@Override
	public void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		accountRepository.deleteAccountByAccountNumber(accountNumber);
	}

	@Override
	public Account getAccountByEmail(String email) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByEmail(email);
	}

	@Override
	public Account depositAmmount(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.depositAmmount(accountNumber, account);
	}

	@Override
	public Account withdrawalAmmount(String accountNumber, Account account)
			throws AccountNotFoundException, MinimumBalanceException {
		// TODO Auto-generated method stub
		return accountRepository.withdrawalAmmount(accountNumber, account);
	}
	

}
