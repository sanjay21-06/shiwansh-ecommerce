package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Order;
import com.shiwansh.ecommerce.domain.model.OrderItem;
import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.repository.OrderItemRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.OrderEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.OrderItemEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.OrderItemJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.OrderJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepositoryAdapter implements OrderItemRepository {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public OrderItemRepositoryAdapter(
            OrderItemJpaRepository orderItemJpaRepository,
            OrderJpaRepository orderJpaRepository,
            ProductJpaRepository productJpaRepository) {
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return toDomain(orderItemJpaRepository.save(toEntity(orderItem)));
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemJpaRepository.findByOrderId(orderId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private OrderItemEntity toEntity(OrderItem item) {

        OrderItemEntity entity = new OrderItemEntity();

        entity.setId(item.getId());
        entity.setQuantity(item.getQuantity());
        entity.setPrice(item.getPrice());

        if (item.getOrder() != null) {
            Long orderId = item.getOrder().getId();

            OrderEntity orderEntity = orderJpaRepository.findById(orderId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Order not found with id: " + orderId));

            entity.setOrder(orderEntity);
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

    private OrderItem toDomain(OrderItemEntity entity) {

        Order order = null;
        Product product = null;

        if (entity.getOrder() != null) {
            order = new Order();
            order.setId(entity.getOrder().getId());
            order.setTotalAmount(entity.getOrder().getTotalAmount());
            order.setStatus(entity.getOrder().getStatus());
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

        return new OrderItem(
                entity.getId(),
                order,
                product,
                entity.getQuantity(),
                entity.getPrice()
        );
    }
}