package com.order_service.infrastructure.persistence;

import com.order_service.domain.Order;
import com.order_service.domain.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;

    public OrderRepositoryImpl(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setClientId(order.getClientId());
        orderEntity.setTotalValue(order.getTotalValue());
        orderEntity.setDateCreated(order.getDateCreated());
        orderEntity.setStatus(order.getStatus());

        orderRepositoryJpa.save(orderEntity);
        return order;
    }
}
