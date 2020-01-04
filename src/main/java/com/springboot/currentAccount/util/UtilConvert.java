package com.springboot.currentAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.currentAccount.controller.CurrentAccountController;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.CurrentAccountEntDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public CurrentAccount convertCurrentAccountPer(CurrentAccountPerDto currentAccountPerDto) {
		
	

		CurrentAccount  currentAccount = new CurrentAccount();

		
		currentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		currentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		currentAccount.setState(currentAccountPerDto.getState());
		currentAccount.setBalance(currentAccountPerDto.getBalance());
		currentAccount.setTea(currentAccountPerDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());
		
		
		return currentAccount;

	}
	
	public CurrentAccount convertAccount(AccountDto accountDto) {

		
		LOGGER.info("Convert ---> :"+accountDto.toString());

		CurrentAccount  currentAccount = new CurrentAccount();
		
		currentAccount.setNumDoc(accountDto.getNumDoc());
		currentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		currentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		currentAccount.setState("Activo");
		currentAccount.setBalance(accountDto.getBalance());
		currentAccount.setTea(13.0);
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());
		
		LOGGER.info("Convert ---> :"+currentAccount.toString());
		
		return currentAccount;

	}
	
	
	public CurrentAccount convertCurrentAccountEnt(CurrentAccountEntDto currentAccountEntDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setNumDoc(currentAccountEntDto.getHeadline().getNumDoc());
		currentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		currentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		currentAccount.setState(currentAccountEntDto.getState());
		currentAccount.setBalance(currentAccountEntDto.getBalance());
		currentAccount.setTea(currentAccountEntDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());
		
		
		return currentAccount;

	}
	
	public CurrentAccount convertCurrentAccountAdd(AccountDto accountDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setNumDoc(accountDto.getNumDoc());
		currentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		currentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		currentAccount.setState("Activo");
		currentAccount.setBalance(accountDto.getBalance());
		currentAccount.setTea(13.0);
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());

		return currentAccount;

	}


}
