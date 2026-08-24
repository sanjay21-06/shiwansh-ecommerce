package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Cart;

import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findById(Long id);

    Optional<Cart> findByUserId(Long userId);
}