package com.springboot.currentAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.currentAccount.document.CurrentAccount;

import reactor.core.publisher.Mono;

public interface CurrentAccountRepo extends ReactiveMongoRepository<CurrentAccount, String> {

	public Mono<CurrentAccount> findByNumberAccount(String numberAccount);
	
	
}
