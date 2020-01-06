package com.springboot.currentAccount.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.currentAccount.document.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountRepo extends ReactiveMongoRepository<CurrentAccount, String> {

	public Mono<CurrentAccount> findByNumberAccount(String numberAccount);
	
	@Query("{'headlines.numDoc': ?0 }") 
	 public Flux<CurrentAccount> findByDni(String numDoc);
		
	 @Query("{'headlines.numDoc': ?0 , 'nameBank': ?1}") 
	 public Flux<CurrentAccount> findByDniAndNameBank(String numDoc , String nameBank);
	
	
}
