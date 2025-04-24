package com.order_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryJpa extends JpaRepository<OrderEntity, String> {
}
