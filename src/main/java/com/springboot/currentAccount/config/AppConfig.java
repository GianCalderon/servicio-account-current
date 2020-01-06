package com.springboot.currentAccount.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	
	@Bean
	@Qualifier("personal")
	public WebClient registrarWebClientPer() {
		
		return WebClient.create("http://localhost:8001/api/personal");
		//return WebClient.create("http://gateway-server:9090/api/personal");
       
	}

	@Bean
	@Qualifier("enterprise")
	public WebClient registrarWebClientEnt() {
		
		return WebClient.create("http://localhost:8002/api/enterprise");
		//return WebClient.create("http://gateway-server:9090/api/enterprise");
	}


}
