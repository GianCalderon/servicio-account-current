package com.springboot.currentAccount.service;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.ManageOperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountInterface{

	public Flux<CurrentAccount> findAll();
	public Mono<CurrentAccount> findById(String id);
	public Mono<CurrentAccount> savePersonal(AccountDto accountDto);
	public Mono<CurrentAccount> saveEnterprise(AccountDto accountDto);
	public Mono<CurrentAccount> update(CurrentAccount CurrentAccount, String id);
	public Mono<Void> delete(CurrentAccount CurrentAccount);

	public Mono<CurrentAccount> findByNumAccount(String numAccount);
	public Flux<CurrentAccount> findByNumDoc(String numDoc);
	
	public Flux<ManageOperationDto> searchOperations(String numDoc);
	

	
    

}
