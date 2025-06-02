package com.order_service.application;

import com.order_service.domain.OrderRepository;
import com.order_service.domain.OrderStatus;

public class UpdateStatusUseCase {

    private final OrderRepository orderRepository;

    public UpdateStatusUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(String id, String status) {
        orderRepository.update(id, OrderStatus.orderStatus(status));
    }
}
