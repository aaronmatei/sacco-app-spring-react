package com.realogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SaccoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaccoAppApplication.class, args);
		System.out.println("User service has started....");
	}



}
