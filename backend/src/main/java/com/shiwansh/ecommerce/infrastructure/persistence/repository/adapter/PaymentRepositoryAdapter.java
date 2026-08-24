package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Order;
import com.shiwansh.ecommerce.domain.model.Payment;
import com.shiwansh.ecommerce.domain.repository.PaymentRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.OrderEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.PaymentEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.OrderJpaRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.PaymentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final OrderJpaRepository orderJpaRepository;

    public PaymentRepositoryAdapter(
            PaymentJpaRepository paymentJpaRepository,
            OrderJpaRepository orderJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return toDomain(paymentJpaRepository.save(toEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return paymentJpaRepository.findByOrderId(orderId)
                .map(this::toDomain);
    }

    private PaymentEntity toEntity(Payment payment) {

        PaymentEntity entity = new PaymentEntity();

        entity.setId(payment.getId());
        entity.setAmount(payment.getAmount());
        entity.setMethod(payment.getMethod());
        entity.setStatus(payment.getStatus());
        entity.setTransactionId(payment.getTransactionId());
        entity.setPaidAt(payment.getPaidAt());

        if (payment.getOrder() != null) {
            Long orderId = payment.getOrder().getId();

            OrderEntity orderEntity = orderJpaRepository.findById(orderId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Order not found with id: " + orderId));

            entity.setOrder(orderEntity);
        }

        return entity;
    }

    private Payment toDomain(PaymentEntity entity) {

        Order order = null;

        if (entity.getOrder() != null) {
            order = new Order();
            order.setId(entity.getOrder().getId());
            order.setTotalAmount(entity.getOrder().getTotalAmount());
            order.setStatus(entity.getOrder().getStatus());
        }

        return new Payment(
                entity.getId(),
                order,
                entity.getAmount(),
                entity.getMethod(),
                entity.getStatus(),
                entity.getTransactionId(),
                entity.getPaidAt()
        );
    }
}