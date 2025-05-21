package com.shipping_service.infrastructure.messaging;

import com.payments.avro.OrderPaymentProcessed;
import com.shipping_service.application.CreateShippingUseCase;
import com.shipping_service.application.StatusAnaliseUseCase;
import com.shipping_service.domain.Shipping;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StatusAnaliseUseCase statusAnaliseUseCase;
    private final CreateShippingUseCase createShippingUseCase;

    public PaymentProcessConsumer(StatusAnaliseUseCase statusAnaliseUseCase, CreateShippingUseCase createShippingUseCase) {
        this.statusAnaliseUseCase = statusAnaliseUseCase;
        this.createShippingUseCase = createShippingUseCase;
    }

    @KafkaListener(
            topics = "${kafka.topics.order-payment-processed}",
            groupId = "shipping-service-group",
            containerFactory = "orderPaymentProcessedKafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, OrderPaymentProcessed> consumerRecord) {
        OrderPaymentProcessed orderPaymentProcessed = consumerRecord.value();
        logger.info("M=listen message: Evento Recebido: {}", orderPaymentProcessed);

        Shipping shipping = new Shipping(orderPaymentProcessed.getOrderId(),statusAnaliseUseCase.execute(orderPaymentProcessed));
        createShippingUseCase.execute(shipping);
    }
}
