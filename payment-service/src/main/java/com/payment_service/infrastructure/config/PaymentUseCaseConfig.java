package com.payment_service.infrastructure.config;

import com.payment_service.application.ProcessPaymentUseCase;
import com.payment_service.infrastructure.messaging.PaymentProcessPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentUseCaseConfig {

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentProcessPublisher paymentProcessPublisher) {
        return new ProcessPaymentUseCase(paymentProcessPublisher);
    }
}
