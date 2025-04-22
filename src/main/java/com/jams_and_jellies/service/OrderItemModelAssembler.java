package com.jams_and_jellies.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class OrderItemModelAssembler implements RepresentationModelAssembler<OrderItem, EntityModel<OrderItem>> {

  @Override
  public EntityModel<OrderItem> toModel(OrderItem orderItem) {

    return EntityModel.of(orderItem, //
        linkTo(methodOn(OrderItemController.class).one(orderItem.getId())).withSelfRel(),
        linkTo(methodOn(OrderItemController.class).all()).withRel("orderItems"));
  }
}