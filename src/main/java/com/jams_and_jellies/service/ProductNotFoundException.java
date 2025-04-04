package com.jams_and_jellies.service;

class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(Long id) {
        super("Could not find product " + id);
    }
}