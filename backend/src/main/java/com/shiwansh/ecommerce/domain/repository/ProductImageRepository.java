package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository {

    ProductImage save(ProductImage productImage);

    Optional<ProductImage> findById(Long id);

    List<ProductImage> findAll();

    void deleteById(Long id);
}