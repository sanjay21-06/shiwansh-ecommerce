package com.shiwansh.ecommerce.infrastructure.persistence.repository;

import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageJpaRepository extends JpaRepository<ProductImageEntity, Long> {
}