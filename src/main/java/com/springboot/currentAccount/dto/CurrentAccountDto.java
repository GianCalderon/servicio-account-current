package com.springboot.currentAccount.dto;

import java.util.List;

public class CurrentAccountDto {

	private String numberAccount;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
	
	public CurrentAccountDto(String numberAccount, String state, int balance, List<PersonalDto> holders) {
		super();
		this.numberAccount = numberAccount;
		this.state = state;
		this.balance = balance;
		this.holders = holders;
	}
	
	
	public String getNumberAccount() {
		return numberAccount;
	}
	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public List<PersonalDto> getHolders() {
		return holders;
	}
	public void setHolders(List<PersonalDto> holders) {
		this.holders = holders;
	}


	@Override
	public String toString() {
		return "CurrentAccountDto [numberAccount=" + numberAccount + ", state=" + state + ", balance=" + balance
				+ ", holders=" + holders + "]";
	}
	
	

	
	
	
	
	
	
	
}
