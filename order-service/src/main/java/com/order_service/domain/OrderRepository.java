package com.order_service.domain;

import com.order_service.infrastructure.persistence.OrderEntity;

public interface OrderRepository {
    Order save(Order order);

    void update(String id, OrderStatus status);

    Order findById(String id);
}
