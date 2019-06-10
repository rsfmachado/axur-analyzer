package com.axur.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxurAnalyzerApplication {
	
	public static final String APP_LOG = "[AXUR_ANALYZER]";

	public static void main(String[] args) {
		SpringApplication.run(AxurAnalyzerApplication.class, args);
	}

}
