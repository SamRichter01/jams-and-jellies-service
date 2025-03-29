package com.jams_and_jellies.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(ServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			repository.save(new Product("Grape"));
			repository.save(new Product("Raspberry"));

			// fetch all customers
			log.info("Productss found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(product -> {
			  log.info(product.toString());
			});
			log.info("");
	  
			// fetch an individual customer by ID
			Product product = repository.findById(1L);
			log.info("Product found with findById(1L):");
			log.info("--------------------------------");
			log.info(product.toString());
			log.info("");
	  
			// fetch customers by last name
			log.info("Product found with findByName('Grape'):");
			log.info("--------------------------------------------");
			repository.findByName("Grape").forEach(grape -> {
			  log.info(grape.toString());
			});
			log.info("");
		};
	}

}
