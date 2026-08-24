package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.ProductVariant;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository {

    ProductVariant save(ProductVariant productVariant);

    Optional<ProductVariant> findById(Long id);

    List<ProductVariant> findAll();

    void deleteById(Long id);
}