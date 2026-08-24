package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {

    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findById(Long id);

    List<OrderItem> findByOrderId(Long orderId);
}