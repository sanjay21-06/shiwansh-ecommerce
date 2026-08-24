package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Address;
import com.shiwansh.ecommerce.domain.model.User;
import com.shiwansh.ecommerce.domain.repository.AddressRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.AddressEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.UserEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.AddressJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryAdapter implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public AddressRepositoryAdapter(
            AddressJpaRepository addressJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Address save(Address address) {
        return toDomain(addressJpaRepository.save(toEntity(address)));
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return addressJpaRepository.findByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        addressJpaRepository.deleteById(id);
    }

    private AddressEntity toEntity(Address address) {

        AddressEntity entity = new AddressEntity();

        entity.setId(address.getId());
        entity.setAddressLine(address.getAddressLine());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setPostalCode(address.getPostalCode());
        entity.setCountry(address.getCountry());
        entity.setPhone(address.getPhone());

        if (address.getUser() != null) {
            Long userId = address.getUser().getId();

            UserEntity userEntity = userJpaRepository.findById(userId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "User not found with id: " + userId));

            entity.setUser(userEntity);
        }

        return entity;
    }

    private Address toDomain(AddressEntity entity) {

        User user = null;

        if (entity.getUser() != null) {
            user = new User();
            user.setId(entity.getUser().getId());
            user.setName(entity.getUser().getName());
            user.setEmail(entity.getUser().getEmail());
            user.setRole(entity.getUser().getRole());
            user.setActive(entity.getUser().isActive());
        }

        return new Address(
                entity.getId(),
                user,
                entity.getAddressLine(),
                entity.getCity(),
                entity.getState(),
                entity.getPostalCode(),
                entity.getCountry(),
                entity.getPhone()
        );
    }
}