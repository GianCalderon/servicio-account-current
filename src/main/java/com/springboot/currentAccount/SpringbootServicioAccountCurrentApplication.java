package com.springboot.currentAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountCurrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountCurrentApplication.class, args);
	}

}
