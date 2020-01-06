package com.springboot.currentAccount.document;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "CurrentAccount")
public class CurrentAccount {
	
	@Id
	private String id;
	
	@NotNull(message = "Account name must not be null")
	@NotEmpty(message = "name may not be empty")
	private String nameAccount;
	
	@NotNull(message = "Account name must not be null")
	@NotEmpty(message = "name may not be empty")
	private String nameBank;

	private List<Headline> headlines;
	
	@NotNull(message = "Account numberAccount must not be null")
	@NotEmpty(message = "numberAccount may not be empty")
	private String numberAccount;
	
	
	@NotNull(message = "Account tea must not be null")
	@Min(12)
	@Max(20)
	private Double tea;
	
	@NotNull(message = "Account state must not be null")
	@NotEmpty(message = "state may not be empty")
	private String state;
	
	@NotNull(message = "Account balance must not be null")
	@Min(0)
	private Double balance;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;


	public CurrentAccount(String nameAccount, String numberAccount, Double tea, String state, Double balance,
			Date createDate, Date updateDate) {
		
	
		this.nameAccount = nameAccount;
		this.numberAccount = numberAccount;
		this.tea = tea;
		this.state = state;
		this.balance = balance;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public CurrentAccount() {
	
	}
	
	
	
	
	

}
