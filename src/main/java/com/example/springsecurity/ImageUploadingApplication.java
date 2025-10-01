package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.example.springsecurity", 
    "com.example.controller", 
    "com.example.reposetory",
    "com.example.config"
}) 
@EnableJpaRepositories(basePackages = "com.example.reposetory") // <-- JPA repositories ke liye
@EntityScan(basePackages = "com.example.model")
public class ImageUploadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploadingApplication.class, args);
	}

}