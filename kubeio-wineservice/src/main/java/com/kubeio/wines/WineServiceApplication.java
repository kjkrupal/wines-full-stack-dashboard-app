package com.kubeio.wines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WineServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WineServiceApplication.class, args);
	}

}
