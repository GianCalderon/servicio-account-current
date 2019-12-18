package com.springboot.currentAccount.dto;

import lombok.Data;

@Data
public class CurrentAccountEnterDto {

	private String numberAccount;
	private Double tea;
	private String state;
	private Double balance;
	private EnterpriseDto holders;
}
