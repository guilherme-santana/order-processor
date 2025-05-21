package com.order_service.application;

import com.order_service.domain.Order;
import com.order_service.domain.OrderRepository;
import com.order_service.domain.event.OrderCreatedEvent;
import com.order_service.domain.event.OrderEventPublisher;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public CreateOrderUseCase(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    public Order execute(String clientId, BigDecimal totalValue) {
        if (totalValue == null || totalValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do pedido deve ser maior que zero.");
        }
        Order order = new Order(UUID.randomUUID().toString(), clientId, totalValue);
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getClientId(),
                order.getTotalValue().toString(),
                order.getStatus().toString(),
                order.getDateCreated().toString()
        );
        orderEventPublisher.publishOrderCreatedEvent(event);
        return orderRepository.save(order);

    }
}
