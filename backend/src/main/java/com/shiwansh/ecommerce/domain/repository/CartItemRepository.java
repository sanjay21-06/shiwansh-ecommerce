package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findById(Long id);

    List<CartItem> findByCartId(Long cartId);

    void deleteById(Long id);
}