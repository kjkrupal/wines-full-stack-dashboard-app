package com.kubeio.analysis.kubeioanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class KubeioAnalysisApplication {
	public static void main(String[] args) {
		SpringApplication.run(KubeioAnalysisApplication.class, args);
	}

}
