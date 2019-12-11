package com.springboot.currentAccount.dto;

import lombok.Data;

@Data
public class CurrentAccountEnterDto {

	private String numberAccount;
	private String state;
	private int balance;
	private EnterpriseDto holders;
}
