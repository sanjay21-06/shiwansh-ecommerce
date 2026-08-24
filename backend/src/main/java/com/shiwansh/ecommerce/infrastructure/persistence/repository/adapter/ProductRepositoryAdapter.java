package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Category;
import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.repository.ProductRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.CategoryEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.CategoryJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    public ProductRepositoryAdapter(
            ProductJpaRepository productJpaRepository,
            CategoryJpaRepository categoryJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Product save(Product product) {

        ProductEntity entity = toEntity(product);

        ProductEntity savedEntity = productJpaRepository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {

        return productJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Product> findAll() {

        return productJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        productJpaRepository.deleteById(id);
    }

    // ==============================
    // Domain → Entity
    // ==============================

    private ProductEntity toEntity(Product product) {

        ProductEntity entity = new ProductEntity();

        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setSku(product.getSku());
        entity.setActive(product.isActive());

        if (product.getCategory() != null) {

            Long categoryId = product.getCategory().getId();

            CategoryEntity categoryEntity =
                    categoryJpaRepository.findById(categoryId)
                            .orElseThrow(() ->
                                    new IllegalArgumentException(
                                            "Category not found with id: " + categoryId
                                    ));

            entity.setCategory(categoryEntity);
        }

        return entity;
    }

    // ==============================
    // Entity → Domain
    // ==============================

    private Product toDomain(ProductEntity entity) {

        Category category = null;

        if (entity.getCategory() != null) {

            CategoryEntity categoryEntity = entity.getCategory();

            category = new Category(
                    categoryEntity.getId(),
                    categoryEntity.getName(),
                    categoryEntity.getDescription(),
                    categoryEntity.isActive()
            );
        }

        return new Product(
                entity.getId(),
                category,
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getSku(),
                entity.isActive()
        );
    }
}