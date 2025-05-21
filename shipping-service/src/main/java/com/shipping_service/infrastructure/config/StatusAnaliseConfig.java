package com.shipping_service.infrastructure.config;

import com.shipping_service.application.StatusAnaliseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusAnaliseConfig {

    @Bean
    public StatusAnaliseUseCase statusAnaliseUseCase() {
        return new StatusAnaliseUseCase();
    }
}
