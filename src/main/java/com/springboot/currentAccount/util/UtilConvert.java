package com.springboot.currentAccount.util;

import org.springframework.stereotype.Component;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;

@Component
public class UtilConvert {
	
	
	public CurrentAccount convertCurrentAccountPer(CurrentAccountPerDto currentAccountPerDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setNumberAccount(currentAccountPerDto.getNumberAccount());
		currentAccount.setState(currentAccountPerDto.getState());
		currentAccount.setBalance(currentAccountPerDto.getBalance());
		
		
		return currentAccount;

	}
	
	public CurrentAccount convertCurrentAccountEnter(CurrentAccountEnterDto currentAccountEnterDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setNumberAccount(currentAccountEnterDto.getNumberAccount());
		currentAccount.setState(currentAccountEnterDto.getState());
		currentAccount.setBalance(currentAccountEnterDto.getBalance());
		
		
		return currentAccount;

	}

}
