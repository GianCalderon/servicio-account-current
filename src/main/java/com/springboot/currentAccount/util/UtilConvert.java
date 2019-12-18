package com.springboot.currentAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CuentaDto;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;

@Component
public class UtilConvert {
	
	
	public CurrentAccount convertCurrentAccountPer(CurrentAccountPerDto currentAccountPerDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setName("Cuenta-Corriente");
		currentAccount.setNumberAccount(currentAccountPerDto.getNumberAccount());
		currentAccount.setState(currentAccountPerDto.getState());
		currentAccount.setBalance(currentAccountPerDto.getBalance());
		currentAccount.setTea(currentAccountPerDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());
		
		
		return currentAccount;

	}
	
	public CurrentAccount convertCurrentAccountEnter(CurrentAccountEnterDto currentAccountEnterDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setName("Cuenta-Corriente");
		currentAccount.setNumberAccount(currentAccountEnterDto.getNumberAccount());
		currentAccount.setState(currentAccountEnterDto.getState());
		currentAccount.setBalance(currentAccountEnterDto.getBalance());
		currentAccount.setTea(currentAccountEnterDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());
		
		
		
		return currentAccount;

	}
	
	public CurrentAccount convertCurrentAccount(CuentaDto cuentaDto) {

		CurrentAccount  currentAccount = new CurrentAccount();

		currentAccount.setName("Cuenta-Corriente");
		currentAccount.setNumberAccount(cuentaDto.getNumberAccount());
		currentAccount.setState(cuentaDto.getState());
		currentAccount.setBalance(cuentaDto.getBalance());
		currentAccount.setTea(cuentaDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());

		
		return currentAccount;

	}

}
