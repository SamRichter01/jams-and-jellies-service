package com.jams_and_jellies.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * Sourced and adapted from the spring data jpa guide at https://spring.io/guides/gs/accessing-data-jpa
 */ 
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "PRODUCT_ID")
  private Long id;

  private String name;

  protected Product() {}

  public Product(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Product[id='%d', name='%s']", id, name);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}