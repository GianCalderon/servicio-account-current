package com.springboot.currentAccount.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccount.client.EnterpriseClient;
import com.springboot.currentAccount.client.PersonalClient;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountClient;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.EnterpriseDto;
import com.springboot.currentAccount.dto.OperationDto;
import com.springboot.currentAccount.dto.PersonalDto;
import com.springboot.currentAccount.repo.CurrentAccountRepo;
import com.springboot.currentAccount.util.CodAccount;
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
	PersonalClient client;
	
	@Autowired
	EnterpriseClient clientEnt;

	
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
		
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setNameAccount("Cuenta-Corriente");
		currentAccount.setIdOperation(new ArrayList<String>());
		
		return repo.save(currentAccount);
	}

	@Override
	public Mono<CurrentAccount> update(CurrentAccount currentAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(currentAccount.getNameAccount());
			s.setNumberAccount(currentAccount.getNumberAccount());
			s.setBalance(currentAccount.getBalance());
			s.setState(currentAccount.getState());
			s.setTea(currentAccount.getTea());
			s.setUpdateDate(currentAccount.getUpdateDate());
			s.setCreateDate(currentAccount.getCreateDate());
			s.setIdOperation(currentAccount.getIdOperation());
			
			
			return repo.save(s);
			});
	}
	

	@Override
	public Mono<Void> delete(CurrentAccount currentAccount) {
		
		return repo.delete(currentAccount);
	}

	
	@Override
	public Mono<CurrentAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}
	
	
	@Override
	public Mono<CurrentAccount> saveOperation(OperationDto operationDto) {
		
		return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{

			if(operationDto.getTipoMovement().equals("debito")) {
				
				p.setBalance(p.getBalance()-operationDto.getAmount());
				return repo.save(p);
				
			}else if(operationDto.getTipoMovement().equals("abono")) {
				
				p.setBalance(p.getBalance()+operationDto.getAmount());
				return repo.save(p);
			}
			
			return repo.save(p);

		});
	}
	

	@Override
	public Mono<PersonalDto> saveHeadline(AccountDto accountDto) {
	
     return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
			
			int cont = 0;

		     for (int i = 0; i < cuentas.size(); i++) {

					AccountClient obj = cuentas.get(i);

					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;

				}
		     
				if (cont == 0) {

					return repo.save(convert.convertAccountEnt(accountDto)).flatMap(cuenta -> {

						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

							LOGGER.info("Flujo Inicial ---->: " + titular.toString());

							titular.setIdAccount(cuenta.getId());
							titular.setNameAccount(cuenta.getNameAccount());
							titular.setNumberAccount(cuenta.getNumberAccount());

							LOGGER.info("Flujo Final ----->: " + titular.toString());

							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
								
								p.setIdAccount(cuenta.getId());
								return Mono.just(p);
							});

						});

					});

				} else {

					return Mono.empty();
				}

			});
	}

	@Override
	public Mono<CurrentAccountPerDto> saveHeadlines(CurrentAccountPerDto currentAccountPerDto) {
		
		return save(convert.convertCurrentAccountPer(currentAccountPerDto)).flatMap(cuenta -> {

			currentAccountPerDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular).block();

			});

			return Mono.just(currentAccountPerDto);
		});
	}
	
	
	
	
	@Override
	public Mono<CurrentAccount> saveEnterprise(AccountDto accountDto) {


		LOGGER.info("Service 1---> :"+accountDto.toString());

					return clientEnt.findByNumDoc(accountDto.getNumDoc()).flatMap(enteprise -> {
						
						LOGGER.info("Service 2---> :"+enteprise.toString());
						
						return repo.save(convert.convertAccountEnt(accountDto)).flatMap(cuenta -> {

						LOGGER.info("Flujo Inicial ---->: " + enteprise.toString());

						enteprise.setIdAccount(cuenta.getId());
						enteprise.setNameAccount(cuenta.getNameAccount());
						enteprise.setNumberAccount(cuenta.getNumberAccount());

						LOGGER.info("Flujo Final ----->: " + enteprise.toString());

						 clientEnt.update(enteprise, accountDto.getNumDoc()).block();
						 
						 return Mono.just(cuenta);
					

					});

				});

				} 
	


	
	
	

}
