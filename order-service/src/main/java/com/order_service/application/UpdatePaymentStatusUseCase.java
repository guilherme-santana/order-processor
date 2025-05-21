package com.order_service.application;

import com.order_service.domain.OrderRepository;
import com.order_service.domain.OrderStatus;

public class UpdatePaymentStatusUseCase {

    private final OrderRepository orderRepository;

    public UpdatePaymentStatusUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(String id, String status) {
        OrderStatus orderStatus = OrderStatus.PENDING_PAYMENT;
        if (status.contains("APPROVED")) {
            orderStatus = OrderStatus.PENDING_SHIPMENT;
        } else if (status.contains("DECLINED")) {
            orderStatus = OrderStatus.PAYMENT_FAILED;
        }

        orderRepository.update(id, orderStatus);
    }
}
