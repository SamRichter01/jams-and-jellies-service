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
class ProductController {

    private final ProductRepository repository;

    private final ProductModelAssembler assembler;

    ProductController(ProductRepository repository, ProductModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag:get-aggregate-root[]
    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> all() {
        List<EntityModel<Product>> products = StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(assembler::toModel).collect(Collectors.toList());
        
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    // Single item

    @GetMapping("/products/{id}")
    EntityModel<Product> one(@PathVariable Long id) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        return assembler.toModel(product);

    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repository.findById(id)
            .map(product -> {
                product.setName(newProduct.getName());
                return repository.save(product);
            }).orElseGet(() -> {
                return repository.save(newProduct);
            });
    }   

    @DeleteMapping("/products/{id}") 
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }   
}
