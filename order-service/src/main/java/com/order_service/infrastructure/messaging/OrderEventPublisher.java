package com.order_service.infrastructure.messaging;

import com.order_service.domain.event.OrderCreatedEvent;
import com.order_service.infrastructure.config.kafka.KafkaTopicsProperties;
import com.orders.avro.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher implements com.order_service.domain.event.OrderEventPublisher {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, OrderCreated> kafkaEvent;
    private final KafkaTopicsProperties kafkaTopicsProperties;

    public OrderEventPublisher(KafkaTemplate<String, OrderCreated> kafkaEvent, KafkaTopicsProperties kafkaTopicsProperties) {
        this.kafkaEvent = kafkaEvent;
        this.kafkaTopicsProperties = kafkaTopicsProperties;
    }

    @Override
    public void publishOrderCreatedEvent(OrderCreatedEvent event) {

        OrderCreated orderCreatedAvro = OrderCreated.newBuilder()
                .setId(event.getOrderId())
                .setClientId(event.getClientId())
                .setTotalValue(event.getTotalValue())
                .setStatus(event.getStatus())
                .setDateCreated(event.getDateCreated())
                .build();

        kafkaEvent.send(kafkaTopicsProperties.getOrderCreated(), event.getOrderId(), orderCreatedAvro)
                .thenAccept(result -> logger.info("M=publishOrderCreatedEvent message: Evento enviado: {}", result.getProducerRecord()))
                .exceptionally(ex -> {
                    logger.error("M=publishOrderCreatedEvent message: Erro ao enviar evento", ex);
                    return null;
                });

    }
}
