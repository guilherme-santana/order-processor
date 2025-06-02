package com.shipping_service.infrastructure.messaging;

import com.shipping.avro.ShippingProcessed;
import com.shipping.avro.ShippingStatus;
import com.shipping_service.domain.Shipping;
import com.shipping_service.infrastructure.config.kafka.KafkaTopicsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ShippingPublisher implements com.shipping_service.domain.ShippingPublisher {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, ShippingProcessed> kafkaEvent;
    private final KafkaTopicsProperties kafkaTopicsProperties;

    public ShippingPublisher(KafkaTemplate<String, ShippingProcessed> kafkaEvent, KafkaTopicsProperties kafkaTopicsProperties) {
        this.kafkaEvent = kafkaEvent;
        this.kafkaTopicsProperties = kafkaTopicsProperties;
    }

    @Override
    public void publisherShippingEvent(Shipping shipping) {
        ShippingProcessed event = new ShippingProcessed.Builder()
                .setOrderId(shipping.getId())
                .setShippingStatus(ShippingStatus.valueOf(shipping.getShippingStatus().toString()))
                .setProcessedAt(Instant.ofEpochMilli(System.currentTimeMillis()))
                .build();

        kafkaEvent.send(kafkaTopicsProperties.getShippingProcessed(), event.getOrderId(), event)
                .thenAccept(result -> logger.info("M=publisherShippingEvent message: Evento enviado: {}", result.getProducerRecord())).exceptionally(ex -> {
                    logger.error("M=publisherShippingEvent message: Erro ao enviar o evento: {}", ex);
                    return null;
                });
    }
}
