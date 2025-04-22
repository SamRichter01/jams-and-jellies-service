package com.jams_and_jellies.service;

public class OrderInfo {

    private Long productId;

    private Long orderId;

    private Long count;

    protected OrderInfo() {}

    public OrderInfo(Long productId, Long orderId, Long count) {
        this.productId = productId;
        this.orderId = orderId;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCount() {
        return count;
    }
}