package com.shiwansh.ecommerce.infrastructure.persistence.repository;

import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}