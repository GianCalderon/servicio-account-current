package com.springboot.currentAccount.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.currentAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(PersonalClient.class);
	
	@Autowired
	private WebClient clientPer;
	
	public Flux<PersonalDto> findAll() {
		
		return clientPer.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalDto.class));
	}


	public Mono<PersonalDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return clientPer.get().uri("/{id}",param)
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
		
		return clientPer.post()
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(personalDto)
				   .retrieve()
				   .bodyToMono(PersonalDto.class);
	}

}
