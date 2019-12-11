package com.springboot.currentAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentAccountPerDto {

	private String numberAccount;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
	
}
