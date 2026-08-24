package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.model.ProductImage;
import com.shiwansh.ecommerce.domain.repository.ProductImageRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductImageEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductImageJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductImageRepositoryAdapter implements ProductImageRepository {

    private final ProductImageJpaRepository productImageJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public ProductImageRepositoryAdapter(
            ProductImageJpaRepository productImageJpaRepository,
            ProductJpaRepository productJpaRepository) {
        this.productImageJpaRepository = productImageJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        ProductImageEntity entity = toEntity(productImage);
        return toDomain(productImageJpaRepository.save(entity));
    }

    @Override
    public Optional<ProductImage> findById(Long id) {
        return productImageJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productImageJpaRepository.deleteById(id);
    }

    private ProductImageEntity toEntity(ProductImage image) {

        ProductImageEntity entity = new ProductImageEntity();

        entity.setId(image.getId());
        entity.setImageUrl(image.getImageUrl());
        entity.setPrimary(image.isPrimary());

        if (image.getProduct() != null) {
            Long productId = image.getProduct().getId();

            ProductEntity productEntity = productJpaRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found with id: " + productId));

            entity.setProduct(productEntity);
        }

        return entity;
    }

    private ProductImage toDomain(ProductImageEntity entity) {

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

        return new ProductImage(
                entity.getId(),
                product,
                entity.getImageUrl(),
                entity.isPrimary()
        );
    }
}