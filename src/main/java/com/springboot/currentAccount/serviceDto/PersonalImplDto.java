package com.springboot.currentAccount.serviceDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.currentAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalImplDto {
	
	
private static final Logger log = LoggerFactory.getLogger(PersonalImplDto.class);
	
	@Autowired
	private WebClient client;
	
	public Flux<PersonalDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalDto.class));
	}


	public Mono<PersonalDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<PersonalDto> save(PersonalDto personalDto) {
		
		log.info("listo a enviar: "+personalDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
			   .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
		       .body(BodyInserters.fromValue(personalDto))
			   .retrieve()
			   .bodyToMono(PersonalDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PersonalDto> update(PersonalDto personalDto, String id) {
		
		return client.post()
				   .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
				   .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
				   .syncBody(personalDto)
				   .retrieve()
				   .bodyToMono(PersonalDto.class);
	}

}
