package com.example.eurekarserver;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eurekarserver.exception.ui.ErrorResponse;
import com.example.eurekarserver.exception.AccountNotFoundException;
import com.example.eurekarserver.model.Account;
import com.example.eurekarserver.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountApi {
	
	private AccountService accountService;
	
	@ExceptionHandler
	public com.example.eurekarserver.exception.ui.ErrorResponse handleException(AccountNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}

	public AccountApi(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account)
	{
		return accountService.insertAccount(account);
	}
	@GetMapping
	public List<Account> listAccounts()
	{
		return accountService.getAllAccount();
	}
	@GetMapping("/{accountNumber}")
	public Account getAccountByAccountNumber(@PathVariable("accountNumber")String accountNumber) throws AccountNotFoundException {
		return accountService.getAccountByAccountNumber(accountNumber);
	}
	
	@PutMapping("/{accountNumber}")
	public Account updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account)
			throws AccountNotFoundException {
		return accountService.updateAccountByAccountNumber(accountNumber, account);
		
	}
	
	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException {
		accountService.deleteAccountByAccountNumber(accountNumber);
	}
	
	@GetMapping("/email/{email}")
	public Account getAccountByEmailo(@PathVariable("email")String email) throws AccountNotFoundException {
		return accountService.getAccountByEmail(email);
	}
}

