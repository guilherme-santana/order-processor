package com.order_service.infrastructure.config;

import com.order_service.application.CreateOrderUseCase;
import com.order_service.domain.OrderRepository;
import com.order_service.domain.event.OrderEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderUseCaseConfig {

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher){
        return new CreateOrderUseCase(orderRepository,orderEventPublisher);
    }
}
