package com.jams_and_jellies.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Sourced and adapted from the spring data jpa guide at https://spring.io/guides/gs/accessing-data-jpa
 */ 
public interface ProductRepository extends CrudRepository<Product, Long> {

  List<Product> findByName(String name);

  Product findById(long id);
}