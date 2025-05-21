package com.payment_service.infrastructure.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.topics")
public class KafkaTopicsProperties {
    private String orderPaymentProcessed;

    public String getOrderPaymentProcessed() {
        return orderPaymentProcessed;
    }

    public void setOrderPaymentProcessed(String orderPaymentProcessed) {
        this.orderPaymentProcessed = orderPaymentProcessed;
    }
}
