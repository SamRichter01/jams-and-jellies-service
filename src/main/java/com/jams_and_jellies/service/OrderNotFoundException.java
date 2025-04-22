package com.jams_and_jellies.service;

class OrderNotFoundException extends RuntimeException {
    OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}