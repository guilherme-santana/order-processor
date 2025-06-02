package com.shipping_service.infrastructure.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.topics")
public class KafkaTopicsProperties {
    private String shippingProcessed;

    public String getShippingProcessed() {
        return shippingProcessed;
    }

    public void setShippingProcessed(String shippingProcessed) {
        this.shippingProcessed = shippingProcessed;
    }
}
