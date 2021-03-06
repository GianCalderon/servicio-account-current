package com.springboot.currentAccount.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.currentAccount.client.PersonalClient;
import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.AccountDto;
import com.springboot.currentAccount.dto.ManageOperationDto;
import com.springboot.currentAccount.service.CurrentAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/currentAccount")
public class CurrentAccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountController.class);

	@Autowired
	CurrentAccountImpl service;
	
	@Autowired
	PersonalClient client;

	@GetMapping
	public Mono<ResponseEntity<Flux<CurrentAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> search(@PathVariable String id) {
		
		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@PostMapping("/savePer")
	public Mono<ResponseEntity<CurrentAccount>> savePersonal(@RequestBody AccountDto accountDto) {
		
		LOGGER.info("accountDto ---> "+accountDto.toString());

		return service.savePersonal(accountDto)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccount".concat(s.getId())))
						 .contentType(MediaType.APPLICATION_JSON).body(s))
				         .defaultIfEmpty(new ResponseEntity<CurrentAccount>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping("/saveEnt")
	public Mono<ResponseEntity<CurrentAccount>> saveEnterprise(@RequestBody AccountDto accountDto) {
		
		LOGGER.info("accountDto ---> "+accountDto.toString());

		return service.saveEnterprise(accountDto)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccount".concat(s.getId())))
						 .contentType(MediaType.APPLICATION_JSON).body(s))
				         .defaultIfEmpty(new ResponseEntity<CurrentAccount>(HttpStatus.NOT_FOUND));

	}
	
	

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> update(@RequestBody CurrentAccount currentAccount,
			@PathVariable String id) {
		
		
		LOGGER.info("Controller ----> "+currentAccount.toString());

		return service.update(currentAccount, id)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	//OPERACIONES QUE CONSUMEN SERVICIO

	
	@GetMapping("/numDoc/{numDoc}")
	public Flux<CurrentAccount> searchByDni(@PathVariable String numDoc) {
				
		return service.findByNumDoc(numDoc);

	}
	
	@GetMapping("/account/{numberAccount}")
	public Mono<ResponseEntity<CurrentAccount>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/operations/{numDoc}")
	public Flux<ManageOperationDto> searchOperations(@PathVariable String numDoc) {
		
		return service.searchOperations(numDoc);

	}
	

	
	
	

}
