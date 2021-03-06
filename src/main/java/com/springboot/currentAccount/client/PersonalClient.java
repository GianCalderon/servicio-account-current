package com.springboot.currentAccount.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.currentAccount.dto.AccountClient;
import com.springboot.currentAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(PersonalClient.class);


	@Autowired
	@Qualifier("personal")
	private WebClient clientPer;
	
	public Flux<PersonalDto> findAll() {
		
		return clientPer.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalDto.class));
	}


	public Mono<PersonalDto> findById(String id) {
		
		
		return clientPer.get()
				.uri("/{id}",Collections.singletonMap("id",id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<PersonalDto> save(PersonalDto personalDto) {
		
		LOGGER.info("listo a enviar: "+personalDto.toString());
		
		return clientPer.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(personalDto))
			   .retrieve()
			   .bodyToMono(PersonalDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return clientPer.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PersonalDto> update(PersonalDto personalDto, String id) {
		
		LOGGER.info("listo a enviar: "+personalDto.toString()+"ID --> :"+id);
		
		
		return clientPer.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(personalDto)
				   .retrieve()
				   .bodyToMono(PersonalDto.class);
	}
	
	public Mono<PersonalDto> findByNumDoc(String numDoc) {
		
		return clientPer.get()
				.uri("/numDoc/{dni}",Collections.singletonMap("dni",numDoc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}
	
	public Flux<AccountClient> extractAccounts(String dni) {

		return clientPer.get()
				.uri("/valid/{dni}",Collections.singletonMap("dni",dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountClient.class);
			
	}
	
	
	
	

}
