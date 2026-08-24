package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Inventory;
import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.repository.InventoryRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.InventoryEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.InventoryJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryRepositoryAdapter implements InventoryRepository {

    private final InventoryJpaRepository inventoryJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public InventoryRepositoryAdapter(
            InventoryJpaRepository inventoryJpaRepository,
            ProductJpaRepository productJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {

        InventoryEntity entity = toEntity(inventory);

        return toDomain(inventoryJpaRepository.save(entity));
    }

    @Override
    public Optional<Inventory> findById(Long id) {

        return inventoryJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {

        return inventoryJpaRepository.findByProductId(productId)
                .map(this::toDomain);
    }

    private InventoryEntity toEntity(Inventory inventory) {

        InventoryEntity entity = new InventoryEntity();

        entity.setId(inventory.getId());
        entity.setQuantity(inventory.getQuantity());

        if (inventory.getProduct() != null) {

            Long productId = inventory.getProduct().getId();

            ProductEntity productEntity = productJpaRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found with id: " + productId));

            entity.setProduct(productEntity);
        }

        return entity;
    }

    private Inventory toDomain(InventoryEntity entity) {

        Product product = null;

        if (entity.getProduct() != null) {

            ProductEntity productEntity = entity.getProduct();

            product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setPrice(productEntity.getPrice());
            product.setSku(productEntity.getSku());
            product.setActive(productEntity.isActive());
        }

        return new Inventory(
                entity.getId(),
                product,
                entity.getQuantity()
        );
    }
}