package com.springboot.currentAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentAccountDto {

	private String numberAccount;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
	
}
