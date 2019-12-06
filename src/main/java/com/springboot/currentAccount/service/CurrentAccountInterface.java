package com.springboot.currentAccount.service;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountInterface {

	
	public Flux<CurrentAccount> findAll();
	public Mono<CurrentAccount> findById(String id);
	public Mono<CurrentAccount> save(CurrentAccount currentAccount);
	public Mono<CurrentAccount> update(CurrentAccount currentAccount ,String id);
	public Mono<Void> delete(CurrentAccount currentAccount);
	
	
	
}
