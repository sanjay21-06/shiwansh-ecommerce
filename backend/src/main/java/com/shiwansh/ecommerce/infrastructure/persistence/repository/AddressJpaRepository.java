package com.shiwansh.ecommerce.infrastructure.persistence.repository;

import com.shiwansh.ecommerce.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByUserId(Long userId);
}