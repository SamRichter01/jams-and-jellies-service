package com.jams_and_jellies.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    
	@Bean
	public CommandLineRunner initDatabase(ProductRepository repository) {
		return (args) -> {

            /**
            log.info("Preloading " + repository.save(new Product("Strawberry")));
            log.info("Preloading " + repository.save(new Product("Grape")));
            log.info("Preloading " + repository.save(new Product("Apricot")));
            */

            /** 
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
            */
		};
	}
}
