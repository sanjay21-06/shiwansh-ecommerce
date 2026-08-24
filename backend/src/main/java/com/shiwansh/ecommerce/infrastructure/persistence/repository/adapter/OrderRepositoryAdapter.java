package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Address;
import com.shiwansh.ecommerce.domain.model.Order;
import com.shiwansh.ecommerce.domain.model.User;
import com.shiwansh.ecommerce.domain.repository.OrderRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.AddressEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.OrderEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.UserEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.AddressJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.OrderJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final AddressJpaRepository addressJpaRepository;

    public OrderRepositoryAdapter(
            OrderJpaRepository orderJpaRepository,
            UserJpaRepository userJpaRepository,
            AddressJpaRepository addressJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
    }

    @Override
    public Order save(Order order) {
        return toDomain(orderJpaRepository.save(toEntity(order)));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderJpaRepository.findByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private OrderEntity toEntity(Order order) {

        OrderEntity entity = new OrderEntity();

        entity.setId(order.getId());
        entity.setTotalAmount(order.getTotalAmount());
        entity.setStatus(order.getStatus());

        if (order.getUser() != null) {
            Long userId = order.getUser().getId();

            UserEntity userEntity = userJpaRepository.findById(userId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "User not found with id: " + userId));

            entity.setUser(userEntity);
        }

        if (order.getAddress() != null) {
            Long addressId = order.getAddress().getId();

            AddressEntity addressEntity = addressJpaRepository.findById(addressId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Address not found with id: " + addressId));

            entity.setAddress(addressEntity);
        }

        return entity;
    }

    private Order toDomain(OrderEntity entity) {

        User user = null;
        Address address = null;

        if (entity.getUser() != null) {
            UserEntity userEntity = entity.getUser();

            user = new User();
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setEmail(userEntity.getEmail());
            user.setRole(userEntity.getRole());
            user.setActive(userEntity.isActive());
        }

        if (entity.getAddress() != null) {
            AddressEntity addressEntity = entity.getAddress();

            address = new Address();
            address.setId(addressEntity.getId());
            address.setAddressLine(addressEntity.getAddressLine());
            address.setCity(addressEntity.getCity());
            address.setState(addressEntity.getState());
            address.setPostalCode(addressEntity.getPostalCode());
            address.setCountry(addressEntity.getCountry());
            address.setPhone(addressEntity.getPhone());
        }

        return new Order(
                entity.getId(),
                user,
                address,
                entity.getTotalAmount(),
                entity.getStatus()
        );
    }
}