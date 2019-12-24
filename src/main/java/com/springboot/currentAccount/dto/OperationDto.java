package com.springboot.currentAccount.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OperationDto {
	
	
	@NotBlank
	private String numDoc;
	
	@NotBlank
	private String numAccount;
	
	@NotBlank
	private String numOperation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMovement;
	
	@NotBlank
	private String tipoMovement;
	
	@NotBlank
	private int amount;
	
	
	

}
