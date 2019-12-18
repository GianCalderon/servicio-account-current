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
import com.springboot.currentAccount.dto.Cuenta;
import com.springboot.currentAccount.dto.CuentaDto;
import com.springboot.currentAccount.dto.CurrentAccountEnterDto;
import com.springboot.currentAccount.dto.CurrentAccountPerDto;
import com.springboot.currentAccount.dto.EnterpriseDto;
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
	EnterpriseClient webClientEnt;

	
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

			
			currentAccountEnterDto.getHolders().setIdAccount(sa.getId());
			currentAccountEnterDto.getHolders().setNameAccount("Cuenta-Corriente");
		
		
		
			webClientEnt.save(currentAccountEnterDto.getHolders()).block();
			

			return Mono.just(currentAccountEnterDto);
		});
		
		
  }
	
	@Override
	public Mono<CurrentAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}

	@Override
	public Mono<PersonalDto> saveAddCuentaPer(CuentaDto cuentaDto) {
		
	    return repo.save(convert.convertCurrentAccount(cuentaDto)).flatMap(c->{
	    	
	    	return webClientPer.findById(cuentaDto.getDni()).flatMap(p->{
	    		
	    		LOGGER.info("Flujo Inicial---->: "+p.toString());
	    		
	    		
	    		List<Cuenta> lista=p.getIdCuentas();
	            
	             Cuenta cuenta=new Cuenta();
	             
	             cuenta.setNameAccount(c.getName());
	             cuenta.setNumAccount(c.getId());
	               lista.add(cuenta);
	           
	               p.setIdCuentas(lista);
	             
	             LOGGER.info("Flujo Final ---->: "+p.toString());
	             
	            return webClientPer.update(p,cuentaDto.getDni());
	            
	 
	    	});
	    	
	    });
	}
	
	@Override
	public Mono<EnterpriseDto> saveAddCuentaEnt(CuentaDto cuentaDto) {
		
	    return repo.save(convert.convertCurrentAccount(cuentaDto)).flatMap(c->{
	    	
	    	return webClientEnt.findById(cuentaDto.getDni()).flatMap(p->{
	    		
	    		LOGGER.info("Flujo Inicial---->: "+p.toString());
	    		
	    		List<Map<String,String>> lista=p.getIdCuentas();
	            
	    		 Map<String,String> listmap = new HashMap<String,String>();
	    		 listmap.put(c.getId(),c.getName());
	             lista.add(listmap);
	           
	             p.setIdCuentas(lista);
	             
	             LOGGER.info("Flujo Final ---->: "+p.toString());
	             
	            return webClientEnt.update(p,cuentaDto.getDni());
	            
	 
	    	});
	    	
	    });
	}
	
	
	

}
