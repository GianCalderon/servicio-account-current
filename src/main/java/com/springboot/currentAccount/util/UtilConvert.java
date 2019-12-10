package com.springboot.currentAccount.util;

import org.springframework.stereotype.Component;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountDto;

@Component
public class UtilConvert {
	
	
	public CurrentAccount convertCurrentAccount(CurrentAccountDto currentAccountDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setNumberAccount(currentAccountDto.getNumberAccount());
		currentAccount.setState(currentAccountDto.getState());
		currentAccount.setBalance(currentAccountDto.getBalance());
		
		
		return currentAccount;

	}

}
