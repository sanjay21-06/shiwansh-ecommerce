package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);
}