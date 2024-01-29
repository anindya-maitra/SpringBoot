package com.example.eurekarserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



public class MinimumBalanceException extends Exception {
	private String messege;

	public MinimumBalanceException(String messege) {
		super(messege);
		this.messege = messege;
	}

	public String getMessege() {
		super.getMessage();
		return messege;
	}

	public void setMessege(String messege) {
		
		this.messege = messege;
	}

	@Override
	public String toString() {
		return messege;
	}
	
	
}
