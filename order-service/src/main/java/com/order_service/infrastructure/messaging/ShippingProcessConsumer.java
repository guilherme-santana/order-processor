package com.order_service.infrastructure.messaging;

import com.order_service.application.UpdateStatusUseCase;
import com.shipping.avro.ShippingProcessed;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ShippingProcessConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UpdateStatusUseCase updateStatusUseCase;

    public ShippingProcessConsumer(UpdateStatusUseCase updateStatusUseCase) {
        this.updateStatusUseCase = updateStatusUseCase;
    }

    @KafkaListener(
            topics = "${kafka.topics.shippingProcessed}",
            groupId = "order-service-group",
            containerFactory = "shippingProcessedKafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, ShippingProcessed> consumerRecord) {
        ShippingProcessed shippingProcessed = consumerRecord.value();
        logger.info("M=listen message: Evento Recebido: {}", shippingProcessed);
        updateStatusUseCase.execute(shippingProcessed.getOrderId(), shippingProcessed.getShippingStatus().toString());
    }
}
