package com.payment_service.infrastructure.messaging;

import com.orders.avro.OrderCreated;
import com.payment_service.application.ProcessPaymentUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProcessPaymentUseCase processPaymentUseCase;

    public OrderCreatedConsumer(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @KafkaListener(
            topics = "${kafka.topics.order-created}",
            groupId = "payment-service-group",
            containerFactory = "orderCreatedKafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, OrderCreated> record) {
        OrderCreated orderCreated = record.value();
        logger.info("M=listen message: Evento recebido: {}", orderCreated);
        processPaymentUseCase.process(orderCreated.getId());
    }
}
