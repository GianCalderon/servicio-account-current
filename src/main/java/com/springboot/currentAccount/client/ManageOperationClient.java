package com.springboot.currentAccount.client;

import java.util.Collections;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.currentAccount.dto.ManageOperationDto;

import reactor.core.publisher.Flux;

@Component
public class ManageOperationClient {
	
	
	WebClient clientManageOperation = WebClient.create("http://localhost:8011/api/operationAccount");
	
	public Flux<ManageOperationDto> findByNumberAccount(String numberAccount) {
		
		
		return clientManageOperation.get()
				.uri("/account/{numberAccount}",Collections.singletonMap("numberAccount",numberAccount))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(ManageOperationDto.class);
		        
	}

}
