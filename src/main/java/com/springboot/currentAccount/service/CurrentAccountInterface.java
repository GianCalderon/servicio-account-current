package com.springboot.currentAccount.service;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.CurrentAccountEntDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.EnterpriseDto;
import com.springboot.currentAccount.dto.OperationDto;
import com.springboot.currentAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountInterface {

	
	public Flux<CurrentAccount> findAll();
	public Mono<CurrentAccount> findById(String id);
	public Mono<CurrentAccount> save(CurrentAccount currentAccount);
	public Mono<CurrentAccount> update(CurrentAccount currentAccount ,String id);
	public Mono<Void> delete(CurrentAccount currentAccount);
	
	public Mono<CurrentAccount> findByNumAccount(String numAccount);
	public Mono<CurrentAccount> saveOperation(OperationDto operationDto);	
	public Mono<PersonalDto> saveHeadline(AccountDto accountDto);     
	public Mono<CurrentAccountPerDto> saveHeadlines (CurrentAccountPerDto currentAccountPerDto);
	
	public Mono<EnterpriseDto> saveEnterprise(AccountDto accountDto);
	

	

	
	
	
	
}
