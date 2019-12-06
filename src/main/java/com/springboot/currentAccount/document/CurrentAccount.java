package com.springboot.currentAccount.document;

import org.springframework.data.annotation.Id;

public class CurrentAccount {
	
	@Id
	private String id;
	private String numberAccount;
	private String state;
	private int balance;
	

}
