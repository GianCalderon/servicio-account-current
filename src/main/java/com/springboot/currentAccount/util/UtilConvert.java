package com.springboot.currentAccount.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.document.Headline;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.CurrentAccountEntDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public CurrentAccount convertAccountDto(AccountDto accountDto) {
		
		 LOGGER.info("convetir 1 -->"+accountDto.toString());
		 
		 List<Headline> listHeadline=new ArrayList<Headline>();
		 Headline headline=new Headline();
		 headline.setNumDoc(accountDto.getNumDoc());
		 listHeadline.add(headline);

		CurrentAccount  CurrentAccount = new CurrentAccount();

		CurrentAccount.setNameBank(accountDto.getNameBank());
		CurrentAccount.setHeadlines(listHeadline);
		CurrentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		CurrentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		CurrentAccount.setState("Activo");
		CurrentAccount.setBalance(accountDto.getBalance());
		CurrentAccount.setTea(12.5);
		CurrentAccount.setCreateDate(new Date());
		CurrentAccount.setUpdateDate(new Date());
		

		 LOGGER.info("convetir 2-->"+CurrentAccount.toString());
		return CurrentAccount;

	}
	
	
//	public CurrentAccount convertCurrentAccountEnt(CurrentAccountEntDto currentAccountEntDto) {
//
//		CurrentAccount  currentAccount = new CurrentAccount();
//
//		currentAccount.setNumDoc(currentAccountEntDto.getHeadline().getNumDoc());
//		currentAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
//		currentAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
//		currentAccount.setState(currentAccountEntDto.getState());
//		currentAccount.setBalance(currentAccountEntDto.getBalance());
//		currentAccount.setTea(currentAccountEntDto.getTea());
//		currentAccount.setCreateDate(new Date());
//		currentAccount.setUpdateDate(new Date());
//		
//		
//		return currentAccount;
//
//	}
	



}
