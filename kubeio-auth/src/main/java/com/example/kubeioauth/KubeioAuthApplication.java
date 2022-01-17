package com.example.kubeioauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KubeioAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(KubeioAuthApplication.class, args);
    }

}