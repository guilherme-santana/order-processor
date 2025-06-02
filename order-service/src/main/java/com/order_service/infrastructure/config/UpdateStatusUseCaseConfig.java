package com.order_service.infrastructure.config;

import com.order_service.application.UpdateStatusUseCase;
import com.order_service.domain.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateStatusUseCaseConfig {

    @Bean
    public UpdateStatusUseCase UpdateStatusUseCase(OrderRepository orderRepository) {
        return new UpdateStatusUseCase(orderRepository);
    }
}
