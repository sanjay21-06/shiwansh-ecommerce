package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Inventory;

import java.util.Optional;

public interface InventoryRepository {

    Inventory save(Inventory inventory);

    Optional<Inventory> findById(Long id);

    Optional<Inventory> findByProductId(Long productId);
}