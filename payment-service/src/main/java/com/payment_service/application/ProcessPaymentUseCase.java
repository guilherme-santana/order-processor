package com.payment_service.application;

import com.payment_service.domain.PublishPaymentProcessed;
import com.payment_service.infrastructure.messaging.PaymentProcessPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessPaymentUseCase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PublishPaymentProcessed paymentProcessPublisher;

    public ProcessPaymentUseCase(PaymentProcessPublisher paymentProcessPublisher) {
        this.paymentProcessPublisher = paymentProcessPublisher;
    }

    public void process(String orderId) {

        try {
            Thread.sleep(30000);
        } catch (Exception e) {
        }

        boolean paymentApproved = Math.random() > 0.2;

        String status = paymentApproved ? "APPROVED" : "DECLINED";
        logger.info("M=process message: {}", status);

        paymentProcessPublisher.publishPaymentProcessed(orderId, status);
    }

}
