package com.jams_and_jellies.service;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class OrderItem {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private Long count;

    protected OrderItem() {}

    public OrderItem(Product product, Order order, Long count) {
        this.product = product;
        this.order = order;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("OrderItem[id='%d, productId='%s', orderId='%s', count='%d']", id, product, order, count);
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return product.getId();
    }

    public Long getOrderId() {
        return order.getId();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}