package com.springboot.currentAccount.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection ="Operaciones-Cuentas")
public class ManageOperationDto {
	

	private String id;
	private String numberAccount;
	private String numOperation;
	private String typeOperation;
	private Double amount;
	private Double commission;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOperation;

}
