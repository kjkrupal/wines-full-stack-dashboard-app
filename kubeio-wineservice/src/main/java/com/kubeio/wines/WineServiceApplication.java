package com.kubeio.wines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class WineServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WineServiceApplication.class, args);
	}

}
