package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    Optional<Payment> findByOrderId(Long orderId);
}