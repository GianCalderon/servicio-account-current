package com.springboot.currentAccount.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccount.client.EnterpriseClient;
import com.springboot.currentAccount.client.ManageOperationClient;
import com.springboot.currentAccount.client.PersonalClient;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.ManageOperationDto;
import com.springboot.currentAccount.repo.CurrentAccountRepo;
import com.springboot.currentAccount.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountImpl implements CurrentAccountInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountImpl.class);

	@Autowired
	CurrentAccountRepo repo;

	@Autowired
	UtilConvert convert;

	@Autowired
	PersonalClient clientPer;
	
	@Autowired
	EnterpriseClient clientEnt;
	
	@Autowired
	ManageOperationClient manageOperationClient;

	@Override
	public Flux<CurrentAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccount> findById(String id) {

		return repo.findById(id);
	}

	@Override
	public Mono<CurrentAccount> savePersonal(AccountDto accountDto) {

		return clientPer.findByNumDoc(accountDto.getNumDoc()).flatMap(persona ->{
		return repo.findByDniAndNameBank(accountDto.getNumDoc(), accountDto.getNameBank()).count().flatMap(AccountCant->{
			LOGGER.info("Cantidad cuentas por dni/banco: "+AccountCant);
			if(AccountCant==0) return repo.save(convert.convertAccountDto(accountDto));
			  else return Mono.empty();
          });
		});
  }
	
	public Mono<CurrentAccount> saveEnterprise(AccountDto accountDto) {

		return clientEnt.findByNumDoc(accountDto.getNumDoc()).flatMap(persona ->{
			
			return repo.save(convert.convertAccountDto(accountDto));
		
		});
  }


	@Override
	public Mono<CurrentAccount> update(CurrentAccount currentAccount, String id) {

		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(currentAccount.getNameAccount());
			s.setNumberAccount(currentAccount.getNumberAccount());
			s.setBalance(currentAccount.getBalance());
			s.setState(currentAccount.getState());
			s.setTea(currentAccount.getTea());
			s.setHeadlines(currentAccount.getHeadlines());
			s.setUpdateDate(new Date());

			return repo.save(s);
		});
	}

	@Override
	public Mono<Void> delete(CurrentAccount CurrentAccount) {

		return repo.delete(CurrentAccount);
	}

	@Override
	public Mono<CurrentAccount> findByNumAccount(String numberAccount) {

		return repo.findByNumberAccount(numberAccount);
	}

	public Flux<CurrentAccount> findByNumDoc(String numDoc) {

		return repo.findByDni(numDoc);
	}
	
	@Override
	public Flux<ManageOperationDto> searchOperations(String numDoc) {
		
		
		return repo.findByDni(numDoc).flatMap(account->{
			
			return manageOperationClient.findByNumberAccount(account.getNumberAccount());

		});
		
		
	}

}