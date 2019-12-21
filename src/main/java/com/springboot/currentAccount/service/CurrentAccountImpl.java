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
import com.springboot.currentAccount.dto.AccountDto;
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
	public Mono<CurrentAccountPerDto> savePerDto(CurrentAccountPerDto currentAccountPerDto) {
		
		LOGGER.info(currentAccountPerDto.toString());

		return save(convert.convertCurrentAccountPer(currentAccountPerDto)).flatMap(sa -> {

			currentAccountPerDto.getHolders().forEach(titular -> {

				titular.setNameAccount(sa.getNameAccount());
				titular.setIdAccount(sa.getId());

				webClientPer.save(titular).block();

			});

			return Mono.just(currentAccountPerDto);
		});
		
		
	}
	
	@Override
	public Mono<CurrentAccountEnterDto> saveEnterDto(CurrentAccountEnterDto currentAccountEnterDto) {
		
		LOGGER.info("service:"+currentAccountEnterDto.toString());

		return save(convert.convertCurrentAccountEnt(currentAccountEnterDto)).flatMap(sa -> {

              currentAccountEnterDto.getHeadline().setIdAccount(sa.getId());
              currentAccountEnterDto.getHeadline().setNameAccount(sa.getNameAccount());

		
		
		
			webClientEnt.save(currentAccountEnterDto.getHeadline()).block();
			

			return Mono.just(currentAccountEnterDto);
		});
		
		
  }
	
	@Override
	public Mono<CurrentAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}

	@Override
	public Mono<PersonalDto> saveAddCuentaPer(CuentaDto cuentaDto) {
		
		
	    return repo.save(convert.convertCurrentAccountAdd(cuentaDto)).flatMap(c->{
	    	
	    	return webClientPer.findByNumDoc(cuentaDto.getNumDoc()).flatMap(titular->{
	    		
	    		LOGGER.info("Flujo Inicial ---->: "+titular.toString());
	            
	    		titular.setIdCuenta("002");
	    		titular.setNameAccount(c.getNameAccount());
	    		titular.setIdAccount(c.getNumberAccount());
	    		

	             LOGGER.info("Flujo Final ----->: "+titular.toString());
	             
	            return webClientPer.update(titular,cuentaDto.getNumDoc());
	            
	 
	    	});
	    	
	    });
	}

	
	@Override
	public Mono<EnterpriseDto> saveAddCuentaEnt(CuentaDto cuentaDto) {
	    
		return repo.save(convert.convertCurrentAccountAdd(cuentaDto)).flatMap(c->{
	    	
	    	return webClientEnt.findByNumDoc(cuentaDto.getNumDoc()).flatMap(titular->{
	    		
	    		LOGGER.info("Flujo Inicial ---->: "+titular.toString());
	            
	    		
	    		titular.setNameAccount(c.getNameAccount());
	    		titular.setIdAccount(c.getId());
	    		

	             LOGGER.info("Flujo Final ----->: "+titular.toString());
	             
	            return webClientEnt.update(titular,cuentaDto.getNumDoc());
	            
	 
	    	});
	    	
	    });
	}
	
	@Override
	public Mono<PersonalDto> validPer(CuentaDto cuentaDto) {
	 
		
	    return webClientPer.validPer(cuentaDto.getNumDoc()).collectList().flatMap(c->{
	    	int cont=0;
	    	LOGGER.info("PRUEBA 2 --->"+c.toString());
	    	LOGGER.info("PRUEBA 2.1 --->"+c.size());
	    	 for (int i=0; i<c.size();i++) {
	    		 
	    		 AccountDto obj=c.get(i);
	    		

		    		
	    		 LOGGER.info("PRUEBA 3 --->"+cuentaDto.toString());
		    	if(obj.getIdAccount().substring(0,6).equals("001020")) {
		    			
		    	       cont++;
		    	
		    	}
					
				}

	    	 LOGGER.info("contador "+cont);
	    	 if(cont==0) {
	    		 
	    		 return saveAddCuentaPer(cuentaDto);
	    		 
	    	 }else {
	    		 
	    		 return Mono.empty();
	    	 }
	    	 
//	    	 return Mono.empty();
	     });
	     
	    
	}
	
	@Override
	public Mono<EnterpriseDto> validEnt(CuentaDto cuentaDto) {
	 
		
	    return webClientEnt.validEnt(cuentaDto.getNumDoc()).collectList().flatMap(c->{
	    	int cont=0;
	    	LOGGER.info("PRUEBA 2 --->"+c.toString());
	    	LOGGER.info("PRUEBA 2.1 --->"+c.size());
	    	 for (int i=0; i<c.size();i++) {
	    		 
	    		 AccountDto obj=c.get(i);
	    		

		    		
	    		 LOGGER.info("PRUEBA 3 --->"+cuentaDto.toString());
		    	if(obj.getIdAccount().substring(0,6).equals("001020")) {
		    			
		    	       cont++;
		    	
		    	}
					
				}

	    	 LOGGER.info("contador "+cont);
	    	 if(cont==0) {
	    		 
	    		 return saveAddCuentaEnt(cuentaDto);
	    		 
	    	 }else {
	    		 
	    		 return Mono.empty();
	    	 }
	    	 
//	    	 return Mono.empty();
	     });
	     
	    
	}


	
	
	

}
