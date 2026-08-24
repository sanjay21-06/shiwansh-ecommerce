package com.shiwansh.ecommerce.infrastructure.persistence.repository;

import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantJpaRepository extends JpaRepository<ProductVariantEntity, Long> {
}