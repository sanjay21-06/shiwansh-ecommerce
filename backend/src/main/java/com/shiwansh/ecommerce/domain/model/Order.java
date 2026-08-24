package com.shiwansh.ecommerce.domain.model;

import java.math.BigDecimal;

public class Order {

    private Long id;
    private User user;
    private Address address;
    private BigDecimal totalAmount;
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, User user, Address address,
                 BigDecimal totalAmount, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}