package com.order_service.application;

import com.order_service.domain.Order;
import com.order_service.domain.OrderRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(String clientId, BigDecimal totalValue){
        if(totalValue == null || totalValue.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Valor do pedido deve ser maior que zero.");
        }
        Order order = new Order(UUID.randomUUID().toString(),clientId,totalValue);
        return orderRepository.save(order);
    }
}
