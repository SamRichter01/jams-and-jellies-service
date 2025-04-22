package com.jams_and_jellies.service;

class OrderItemNotFoundException extends RuntimeException {
    OrderItemNotFoundException(Long id) {
        super("Could not find orderItem " + id);
    }
}