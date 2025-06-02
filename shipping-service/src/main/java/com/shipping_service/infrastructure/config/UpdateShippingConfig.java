package com.shipping_service.infrastructure.config;

import com.shipping_service.application.UpdateShippingUseCase;
import com.shipping_service.domain.ShippingPublisher;
import com.shipping_service.domain.ShippingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateShippingConfig {

    @Bean
    public UpdateShippingUseCase updateShippingUseCase(ShippingRepository shippingRepository, ShippingPublisher shippingPublisher){
        return new UpdateShippingUseCase(shippingRepository, shippingPublisher);
    }

}
