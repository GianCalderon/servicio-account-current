package com.springboot.currentAccount.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PersonalDto {

	private String idAccount;
	private String nameAccount;
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;
	private List<Map<String,String>> idCuentas;


}
