package com.example.eurekarserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.eurekarserver.model.Account;
import com.example.eurekarserver.repo.AccountRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class AccountAppApplication {
	
	private final AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(AccountAppApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		// TODO Auto-generated method stub
		/*
		 * accountRepository.insertAccount(new Account("ACC-001", "John Doe",
		 * "Bangalore","john@email.com")); accountRepository.insertAccount(new
		 * Account("ACC-002", "Mary Lawrence", "New York","mary@email.com"));
		 */
	}

}
