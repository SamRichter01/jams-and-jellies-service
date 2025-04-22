package com.jams_and_jellies.service;

import org.springframework.data.repository.CrudRepository;

/**
 * Sourced and adapted from the spring data jpa guide at https://spring.io/guides/gs/accessing-data-jpa
 */ 
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findById(long id);
}