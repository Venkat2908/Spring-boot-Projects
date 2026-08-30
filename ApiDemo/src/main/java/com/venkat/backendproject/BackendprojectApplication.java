package com.venkat.backendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.venkat.backendproject.repository")
@EntityScan(basePackages = "com.venkat.backendproject.Models")

public class BackendprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendprojectApplication.class, args);
	}

}
