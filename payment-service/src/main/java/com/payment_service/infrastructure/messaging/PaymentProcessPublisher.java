package com.payment_service.infrastructure.messaging;

import com.payment_service.domain.PublishPaymentProcessed;
import com.payment_service.infrastructure.config.kafka.KafkaTopicsProperties;
import com.payments.avro.OrderPaymentProcessed;
import com.payments.avro.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentProcessPublisher implements PublishPaymentProcessed {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, OrderPaymentProcessed> kafkaTemplate;
    private final KafkaTopicsProperties kafkaTopicsProperties;

    public PaymentProcessPublisher(KafkaTemplate<String, OrderPaymentProcessed> kafkaTemplate, KafkaTopicsProperties kafkaTopicsProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicsProperties = kafkaTopicsProperties;
    }

    @Override
    public void publishPaymentProcessed(String orderId, String paymentStatus) {
        OrderPaymentProcessed event = OrderPaymentProcessed.newBuilder()
                .setOrderId(orderId)
                .setPaymentStatus(PaymentStatus.valueOf(paymentStatus))
                .setProcessedAt(Instant.ofEpochMilli(System.currentTimeMillis()))
                .build();
        kafkaTemplate.send(kafkaTopicsProperties.getOrderPaymentProcessed(), event.getOrderId(), event)
                .thenAccept(result -> logger.info("M=publishPaymentProcessed message: Evento enviado: {}", result.getProducerRecord()))
                .exceptionally(ex -> {
                    logger.error("M=publishPaymentProcessed message: Erro ao enviar o evento: {}", ex);
                    return null;
                })

        ;
    }
}
