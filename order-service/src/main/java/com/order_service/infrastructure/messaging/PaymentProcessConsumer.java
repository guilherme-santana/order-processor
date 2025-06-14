package com.order_service.infrastructure.messaging;

import com.order_service.application.UpdateStatusUseCase;
import com.payments.avro.OrderPaymentProcessed;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UpdateStatusUseCase updateStatusUseCase;

    public PaymentProcessConsumer(UpdateStatusUseCase updateStatusUseCase) {
        this.updateStatusUseCase = updateStatusUseCase;
    }

    @KafkaListener(
            topics = "${kafka.topics.order-payment-processed}",
            groupId = "order-service-group",
            containerFactory = "orderPaymentProcessedKafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, OrderPaymentProcessed> consumerRecord) {
        OrderPaymentProcessed orderPaymentProcessed = consumerRecord.value();
        logger.info("M=listen message: Evento Recebido: {}", orderPaymentProcessed);
        updateStatusUseCase.execute(orderPaymentProcessed.getOrderId(), orderPaymentProcessed.getPaymentStatus().toString());
    }
}
