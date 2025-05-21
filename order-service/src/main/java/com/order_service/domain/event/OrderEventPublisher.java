package com.order_service.domain.event;

public interface OrderEventPublisher {
    void publishOrderCreatedEvent(OrderCreatedEvent event);
}
