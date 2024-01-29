package com.example.eurekarserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts_table")
public class Account {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_holder_name")
	private String  accountHolderName;
	
	@Column(name = "account_holder_address")
	private String accountHolderAddress;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "ammount")
	private float ammount;

	public Account(String accountHolderName, String accountHolderAddress, String email, float ammount) {
		super();
		this.accountHolderName = accountHolderName;
		this.accountHolderAddress = accountHolderAddress;
		this.email = email;
		this.ammount = ammount;
	}

	public Account(float ammount) {
		super();
		this.ammount = ammount;
	}
	
	
}
