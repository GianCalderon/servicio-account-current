package com.springboot.currentAccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccount.client.EnterpriseClient;
import com.springboot.currentAccount.client.PersonalClient;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CuentaDto;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.PersonalDto;
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
		
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setName("Cuenta-Corriente");
		currentAccount.setIdOperation(new ArrayList<String>());
		
		return repo.save(currentAccount);
	}

	@Override
	public Mono<CurrentAccount> update(CurrentAccount currentAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setName(currentAccount.getName());
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
	public Mono<CurrentAccountPerDto> savePerDto(CurrentAccountPerDto currentAccountPerDto) {
		
		LOGGER.info(currentAccountPerDto.toString());

		return save(convert.convertCurrentAccountPer(currentAccountPerDto)).flatMap(ca -> {

			currentAccountPerDto.getHolders().forEach(p -> {

				p.setIdAccount(ca.getId());
				p.setNameAccount("Cuenta-Corriente");
				
				webClientPer.save(p).block();

			});

			return Mono.just(currentAccountPerDto);
		});
		
		
	}
	
	@Override
	public Mono<CurrentAccountEnterDto> saveEnterDto(CurrentAccountEnterDto currentAccountEnterDto) {
		
		LOGGER.info("service:"+currentAccountEnterDto.toString());

		return save(convert.convertCurrentAccountEnter(currentAccountEnterDto)).flatMap(sa -> {

			currentAccountEnterDto.getHolders().setIdCuenta(sa.getId());
			webClientEnter.save(currentAccountEnterDto.getHolders()).block();
			

			return Mono.just(currentAccountEnterDto);
		});
		
		
  }
	
	@Override
	public Mono<CurrentAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}

	@Override
	public Mono<PersonalDto> saveAddCuenta(CuentaDto cuentaDto) {
		
	    return repo.save(convert.convertCurrentAccount(cuentaDto)).flatMap(c->{
	    	
	    	return webClientPer.findById(cuentaDto.getDni()).flatMap(p->{
	    		
	    		LOGGER.info("FLUJO ---->: "+p.toString());
	    		
	    		List<Map<String,String>> lista=p.getIdCuentas();
	            
	    		 Map<String,String> listmap = new HashMap<String,String>();
	    		 listmap.put(c.getId(),c.getName());
	             lista.add(listmap);
	           
	             p.setIdCuentas(lista);
	             
	             LOGGER.info("FLUJO  22 ---->: "+p.toString());
	             
	            return webClientPer.update(p,cuentaDto.getDni());
	            
	 
	    	});
	    	
	    });
	}
	
	
	

}
