package com.springboot.currentAccount.document;

import org.springframework.data.annotation.Id;

public class CurrentAccount {
	
	@Id
	private String id;
	private String numberAccount;
	private String state;
	private int balance;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "CurrentAccount [id=" + id + ", numberAccount=" + numberAccount + ", state=" + state + ", balance="
				+ balance + "]";
	}
	
	
	
	
	

}
