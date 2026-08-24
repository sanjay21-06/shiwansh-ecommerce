package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Cart;
import com.shiwansh.ecommerce.domain.model.CartItem;
import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.repository.CartItemRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.CartEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.CartItemEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.CartItemJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.CartJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CartItemRepositoryAdapter implements CartItemRepository {

    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartJpaRepository cartJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public CartItemRepositoryAdapter(
            CartItemJpaRepository cartItemJpaRepository,
            CartJpaRepository cartJpaRepository,
            ProductJpaRepository productJpaRepository) {
        this.cartItemJpaRepository = cartItemJpaRepository;
        this.cartJpaRepository = cartJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return toDomain(cartItemJpaRepository.save(toEntity(cartItem)));
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<CartItem> findByCartId(Long cartId) {
        return cartItemJpaRepository.findByCartId(cartId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        cartItemJpaRepository.deleteById(id);
    }

    private CartItemEntity toEntity(CartItem item) {

        CartItemEntity entity = new CartItemEntity();

        entity.setId(item.getId());
        entity.setQuantity(item.getQuantity());

        if (item.getCart() != null) {
            Long cartId = item.getCart().getId();

            CartEntity cartEntity = cartJpaRepository.findById(cartId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Cart not found with id: " + cartId));

            entity.setCart(cartEntity);
        }

        if (item.getProduct() != null) {
            Long productId = item.getProduct().getId();

            ProductEntity productEntity = productJpaRepository.findById(productId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Product not found with id: " + productId));

            entity.setProduct(productEntity);
        }

        return entity;
    }

    private CartItem toDomain(CartItemEntity entity) {

        Cart cart = null;
        Product product = null;

        if (entity.getCart() != null) {
            cart = new Cart();
            cart.setId(entity.getCart().getId());
        }

        if (entity.getProduct() != null) {
            ProductEntity productEntity = entity.getProduct();

            product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setPrice(productEntity.getPrice());
            product.setSku(productEntity.getSku());
            product.setActive(productEntity.isActive());
        }

        return new CartItem(
                entity.getId(),
                cart,
                product,
                entity.getQuantity()
        );
    }
}