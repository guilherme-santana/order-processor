package com.shipping_service.infrastructure.config;

import com.shipping_service.application.CreateShippingUseCase;
import com.shipping_service.domain.ShippingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateShippingConfig {

    @Bean
    public CreateShippingUseCase createShippingUseCase(ShippingRepository shippingRepository) {
        return new CreateShippingUseCase(shippingRepository);
    }
}
