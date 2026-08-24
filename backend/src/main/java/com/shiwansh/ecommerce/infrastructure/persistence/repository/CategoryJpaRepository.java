package com.shiwansh.ecommerce.infrastructure.persistence.repository;

import com.shiwansh.ecommerce.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}