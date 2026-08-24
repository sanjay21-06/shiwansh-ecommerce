package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Cart;
import com.shiwansh.ecommerce.domain.model.User;
import com.shiwansh.ecommerce.domain.repository.CartRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.CartEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.UserEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.CartJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepositoryAdapter implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public CartRepositoryAdapter(
            CartJpaRepository cartJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return toDomain(cartJpaRepository.save(toEntity(cart)));
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartJpaRepository.findByUserId(userId)
                .map(this::toDomain);
    }

    private CartEntity toEntity(Cart cart) {

        CartEntity entity = new CartEntity();
        entity.setId(cart.getId());

        if (cart.getUser() != null) {
            Long userId = cart.getUser().getId();

            UserEntity userEntity = userJpaRepository.findById(userId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "User not found with id: " + userId));

            entity.setUser(userEntity);
        }

        return entity;
    }

    private Cart toDomain(CartEntity entity) {

        User user = null;

        if (entity.getUser() != null) {
            user = new User();
            user.setId(entity.getUser().getId());
            user.setName(entity.getUser().getName());
            user.setEmail(entity.getUser().getEmail());
            user.setRole(entity.getUser().getRole());
            user.setActive(entity.getUser().isActive());
        }

        return new Cart(entity.getId(), user);
    }
}