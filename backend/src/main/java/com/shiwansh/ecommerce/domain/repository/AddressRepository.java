package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    Address save(Address address);

    Optional<Address> findById(Long id);

    List<Address> findByUserId(Long userId);

    void deleteById(Long id);
}