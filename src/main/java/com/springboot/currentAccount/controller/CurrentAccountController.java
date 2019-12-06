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

import com.springboot.currentAccount.document.CurrentAccount;
import com.springboot.currentAccount.dto.CurrentAccountDto;
import com.springboot.currentAccount.service.CurrentAccountImpl;
import com.springboot.currentAccount.serviceDto.CurrentAccountImplDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/currentAccount")
public class CurrentAccountController {

	
	private static final Logger log = LoggerFactory.getLogger(CurrentAccountController.class);

	@Autowired
	CurrentAccountImpl service;
	
	@Autowired
	CurrentAccountImplDto serviceDto;

//	@Autowired
//	CurrentAccountImplDto serviceDto;

	@GetMapping
	public Mono<ResponseEntity<Flux<CurrentAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> search(@PathVariable String id) {

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<CurrentAccount>> save(@RequestBody CurrentAccount currentAccount) {

		return service.save(currentAccount)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> update(@RequestBody CurrentAccount currentAccount,
			@PathVariable String id) {

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

	@PostMapping("/saveDto")
	public Mono<ResponseEntity<CurrentAccountDto>> saveDto(@RequestBody CurrentAccountDto currentAccountDto) {

		log.info(currentAccountDto.toString());

		return serviceDto.save(currentAccountDto).map(s -> ResponseEntity.created(URI.create("/api/currentAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	
	
}
