package com.springboot.currentAccount.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnterpriseDto {
	
	private String idAccount;
	private String nameAccount;
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String address;
	private List<Map<String,String>> idCuentas;
	


}
