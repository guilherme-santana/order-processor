package com.order_service.infrastructure.persistence;

import com.order_service.domain.Order;
import com.order_service.domain.OrderRepository;
import com.order_service.domain.OrderStatus;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    @Override
    public void update(String id, OrderStatus status) {
        Order order = findById(id);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setClientId(order.getClientId());
        orderEntity.setTotalValue(order.getTotalValue());
        orderEntity.setDateCreated(order.getDateCreated());
        orderEntity.setDateUpdate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        orderEntity.setStatus(status);
        orderRepositoryJpa.save(orderEntity);
    }

    @Override
    public Order findById(String id) {
        OrderEntity orderType = orderRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Orden não encontrada."));
        Order order = new Order();
        order.setId(orderType.getId());
        order.setClientId(orderType.getClientId());
        order.setDateCreated(orderType.getDateCreated());
        order.setStatus(orderType.getStatus());
        order.setTotalValue(orderType.getTotalValue());

        return order;
    }


}
