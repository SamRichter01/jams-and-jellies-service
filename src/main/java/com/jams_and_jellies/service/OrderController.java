package com.jams_and_jellies.service;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class OrderController {

    private final OrderRepository repository;

    private final OrderModelAssembler assembler;

    OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return repository.save(newOrder);
    }

    // Aggregate root
    // tag:get-aggregate-root[]
    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all() {
        List<EntityModel<Order>> products = StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(assembler::toModel).collect(Collectors.toList());
        
        return CollectionModel.of(products, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]


    @GetMapping("/orders/{id}")
    EntityModel<Order> one(@PathVariable Long id) {
        Order order = repository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);

    }

    @DeleteMapping("/orders/{id}") 
    void deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);
    }   
}