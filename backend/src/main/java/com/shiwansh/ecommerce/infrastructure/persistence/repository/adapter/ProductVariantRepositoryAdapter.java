package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.model.ProductVariant;
import com.shiwansh.ecommerce.domain.repository.ProductVariantRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductVariantEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductVariantJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductVariantRepositoryAdapter implements ProductVariantRepository {

    private final ProductVariantJpaRepository productVariantJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public ProductVariantRepositoryAdapter(
            ProductVariantJpaRepository productVariantJpaRepository,
            ProductJpaRepository productJpaRepository) {
        this.productVariantJpaRepository = productVariantJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public ProductVariant save(ProductVariant variant) {
        ProductVariantEntity entity = toEntity(variant);
        return toDomain(productVariantJpaRepository.save(entity));
    }

    @Override
    public Optional<ProductVariant> findById(Long id) {
        return productVariantJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<ProductVariant> findAll() {
        return productVariantJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productVariantJpaRepository.deleteById(id);
    }

    private ProductVariantEntity toEntity(ProductVariant variant) {

        ProductVariantEntity entity = new ProductVariantEntity();

        entity.setId(variant.getId());
        entity.setSize(variant.getSize());
        entity.setColor(variant.getColor());
        entity.setAdditionalPrice(variant.getAdditionalPrice());

        if (variant.getProduct() != null) {
            Long productId = variant.getProduct().getId();

            ProductEntity productEntity = productJpaRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found with id: " + productId));

            entity.setProduct(productEntity);
        }

        return entity;
    }

    private ProductVariant toDomain(ProductVariantEntity entity) {

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

        return new ProductVariant(
                entity.getId(),
                product,
                entity.getSize(),
                entity.getColor(),
                entity.getAdditionalPrice()
        );
    }
}