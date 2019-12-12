package com.springboot.currentAccount.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "currentAccount")
public class CurrentAccount {
	
	@Id
	private String id;
	private String name;
	private String numberAccount;
	private String tea;
	private String state;
	private int balance;

}
