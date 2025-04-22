package com.jams_and_jellies.service;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Long id;

    protected Order() {}
    
    @Override
    public String toString() {
        return String.format("Order[id='%d']", id);
    }

    public Long getId() {
        return id;
    }
}