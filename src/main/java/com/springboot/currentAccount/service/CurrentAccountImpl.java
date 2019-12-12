package com.springboot.currentAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccount.client.EnterpriseClient;
import com.springboot.currentAccount.client.PersonalClient;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.EnterpriseDto;
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
	PersonalClient webClientPer;
	
	@Autowired
	EnterpriseClient webClientEnter;

	
	@Override
	public Flux<CurrentAccount> findAll() {
	
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccount> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<CurrentAccount> save(CurrentAccount currentAccount) {
		
		return repo.save(currentAccount);
	}

	@Override
	public Mono<CurrentAccount> update(CurrentAccount currentAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNumberAccount(currentAccount.getNumberAccount());
			s.setState(currentAccount.getState());
			s.setBalance(currentAccount.getBalance());
			
			return repo.save(s);
			});
	}
	

	@Override
	public Mono<Void> delete(CurrentAccount currentAccount) {
		
		return repo.delete(currentAccount);
	}

	@Override
	public Mono<CurrentAccountPerDto> saveDto(CurrentAccountPerDto currentAccountPerDto) {
		
		LOGGER.info(currentAccountPerDto.toString());

		return save(convert.convertCurrentAccountPer(currentAccountPerDto)).flatMap(ca -> {

			currentAccountPerDto.getHolders().forEach(p -> {

				p.setIdCuenta(ca.getId());
				webClientPer.save(p).block();

			});

			return Mono.just(currentAccountPerDto);
		});
		
		
	}
	
	@Override
	public Mono<CurrentAccountEnterDto> saveDto(CurrentAccountEnterDto currentAccountEnterDto) {
		
		LOGGER.info("service:"+currentAccountEnterDto.toString());

		return save(convert.convertCurrentAccountEnter(currentAccountEnterDto)).flatMap(sa -> {

			currentAccountEnterDto.getHolders().setIdCuenta(sa.getId());
			webClientEnter.save(currentAccountEnterDto.getHolders()).block();
			

			return Mono.just(currentAccountEnterDto);
		});
		
		
  }
	
	
	

}
