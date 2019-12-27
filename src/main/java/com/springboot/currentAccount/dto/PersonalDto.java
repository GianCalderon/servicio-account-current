package com.springboot.currentAccount.dto;

import com.springboot.currentAccount.util.CodAccount;

import lombok.Data;

@Data
public class PersonalDto {

	private String idAccount;
	private String numberAccount="xxxxxxxxxxxxxx";
	private String nameAccount=CodAccount.NAME_CURRENT_ACCOUNT;
	

	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;



}
