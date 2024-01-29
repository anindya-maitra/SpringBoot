package com.example.eurekarserver;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.eurekarserver.exception.MinimumBalanceException;
import com.example.eurekarserver.model.Account;
import com.example.eurekarserver.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountApi {
	
	private AccountService accountService;
	
	@ExceptionHandler
	public ErrorResponse handleException(AccountNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@ExceptionHandler(MinimumBalanceException.class)
	public ErrorResponse handleException1(MinimumBalanceException e1) {
        System.out.println(e1.toString());
		
		ErrorResponse response = new ErrorResponse();
		System.out.println(e1.getMessage());
		response.setMessage(e1.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}

	public AccountApi(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.insertAccount(account));
	}
	@GetMapping
	public ResponseEntity<List<Account>> listAccounts()
	{
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccount());
	}
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable("accountNumber")String accountNumber) throws AccountNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByAccountNumber(accountNumber));
	}
	
	@PutMapping("/{accountNumber}")
	public ResponseEntity<Account> updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account)
			throws AccountNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccountByAccountNumber(accountNumber, account));
		
	}
	
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException {
		accountService.deleteAccountByAccountNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletion Successful");
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Account> getAccountByEmail(@PathVariable("email")String email) throws AccountNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByEmail(email));
	}
	
	@PutMapping("/deposit/{accountNumber}")
	public Account deposit(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account) throws AccountNotFoundException {
		return accountService.depositAmmount(accountNumber, account);
	}
	
	@PutMapping("withdrawal/{accountNumber}")
	public Account withdrawal(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account) throws AccountNotFoundException, MinimumBalanceException {
		return accountService.withdrawalAmmount(accountNumber, account);
	}
}

