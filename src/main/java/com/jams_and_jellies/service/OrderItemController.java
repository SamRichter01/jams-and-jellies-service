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
class OrderItemController {

    private final OrderItemRepository repository;

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    private final OrderItemModelAssembler assembler;

    OrderItemController(OrderItemRepository repository, OrderItemModelAssembler assembler, OrderRepository orderRepo, ProductRepository productRepo) {
        this.repository = repository;
        this.assembler = assembler;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    // Aggregate root
    // tag:get-aggregate-root[]
    @GetMapping("/orderItems")
    CollectionModel<EntityModel<OrderItem>> all() {
        List<EntityModel<OrderItem>> orderItems = StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(assembler::toModel).collect(Collectors.toList());
        
        return CollectionModel.of(orderItems, linkTo(methodOn(OrderItemController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/orderItems")
    OrderItem newOrderItem(@RequestBody OrderInfo newOrderInfo) {

        Long orderId = newOrderInfo.getOrderId();
        Long productId = newOrderInfo.getProductId();
        Long count = newOrderInfo.getCount();

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        return repository.save(new OrderItem(product, order, count));
    }

    // Single item

    @GetMapping("/orderItems/{id}")
    EntityModel<OrderItem> one(@PathVariable Long id) {
        OrderItem orderItem = repository.findById(id)
            .orElseThrow(() -> new OrderItemNotFoundException(id));

        return assembler.toModel(orderItem);

    }

    @PutMapping("/orderItems/{id}")
    OrderItem replaceProduct(@RequestBody OrderInfo newOrderInfo, @PathVariable Long id) {

        Long orderId = newOrderInfo.getOrderId();
        Long productId = newOrderInfo.getProductId();
        Long count = newOrderInfo.getCount();

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        OrderItem newOrderItem = new OrderItem(product, order, count);

        return repository.findById(id)
            .map(orderItem -> {
                orderItem.setCount(newOrderInfo.getCount());
                return repository.save(orderItem);
            }).orElseGet(() -> {
                return repository.save(newOrderItem);
            });
    }   

    @DeleteMapping("/orderItems/{id}") 
    void deleteOrderItem(@PathVariable Long id) {
        repository.deleteById(id);
    }   
}
