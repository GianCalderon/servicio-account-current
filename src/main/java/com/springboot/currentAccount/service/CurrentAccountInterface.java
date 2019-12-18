package com.springboot.currentAccount.service;

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CuentaDto;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.EnterpriseDto;
import com.springboot.currentAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountInterface {

	
	public Flux<CurrentAccount> findAll();
	public Mono<CurrentAccount> findById(String id);
	public Mono<CurrentAccount> save(CurrentAccount currentAccount);
	public Mono<CurrentAccount> update(CurrentAccount currentAccount ,String id);
	public Mono<Void> delete(CurrentAccount currentAccount);
	
	public Mono<CurrentAccountPerDto> savePerDto(CurrentAccountPerDto currentAccountPerDto);
	public Mono<CurrentAccountEnterDto> saveEnterDto(CurrentAccountEnterDto currentAccountEnterDto);
	public Mono<CurrentAccount> findByNumAccount(String numAccount);
	
	public Mono<PersonalDto> saveAddCuentaPer(CuentaDto cuentaDto);
	public Mono<EnterpriseDto> saveAddCuentaEnt(CuentaDto cuentaDto);
	
	
	
	
}
