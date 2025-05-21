package com.order_service.infrastructure.config;

import com.order_service.application.UpdatePaymentStatusUseCase;
import com.order_service.domain.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdatePaymentStatusUseCaseConfig {

    @Bean
    public UpdatePaymentStatusUseCase UpdatePaymentStatusUseCase(OrderRepository orderRepository) {
        return new UpdatePaymentStatusUseCase(orderRepository);
    }
}
