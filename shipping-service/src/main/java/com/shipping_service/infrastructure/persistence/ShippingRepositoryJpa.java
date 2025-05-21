package com.shipping_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepositoryJpa extends JpaRepository<ShippingEntity, String> {
}
