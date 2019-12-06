package com.springboot.currentAccount.serviceDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountDto;
import com.springboot.currentAccount.service.CurrentAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountImplDto {

	private static final Logger log = LoggerFactory.getLogger(CurrentAccountImplDto.class);

	@Autowired
	CurrentAccountImpl service;

	@Autowired
	PersonalImplDto serviceWebClient;

	public Flux<CurrentAccountDto> findAll() {

		return null;
	}

	public Mono<CurrentAccountDto> findById(String id) {

		return null;
	}

	public Mono<CurrentAccountDto> save(CurrentAccountDto currentAccountDto) {

		log.info(currentAccountDto.toString());

		return service.save(convertCurrentAccount(currentAccountDto)).flatMap(ca -> {

			currentAccountDto.getHolders().forEach(p -> {

				p.setIdCuenta(ca.getId());

				serviceWebClient.save(p).block();

			});

			return Mono.just(currentAccountDto);
		});
	}

	public Mono<CurrentAccountDto> update(CurrentAccountDto currentAccountDto, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Void> delete(CurrentAccountDto currentAccountDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public CurrentAccount convertCurrentAccount(CurrentAccountDto currentAccountDto) {

		CurrentAccount currentAccount = new CurrentAccount();

		currentAccount.setNumberAccount(currentAccountDto.getNumberAccount());
		currentAccount.setState(currentAccountDto.getState());
		currentAccount.setBalance(currentAccountDto.getBalance());

		return currentAccount;

	}

}
