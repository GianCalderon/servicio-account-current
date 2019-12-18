package com.springboot.currentAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentAccountPerDto {

	private String numberAccount;
	private Double tea;
	private String state;
	private Double balance;
	private List<PersonalDto> holders;
	
	
}
