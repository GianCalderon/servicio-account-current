package com.springboot.currentAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.currentAccount.document.CurrentAccount;

public interface CurrentAccountRepo extends ReactiveMongoRepository<CurrentAccount, String> {

}
