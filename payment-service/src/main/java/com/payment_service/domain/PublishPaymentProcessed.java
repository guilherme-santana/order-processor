package com.payment_service.domain;

public interface PublishPaymentProcessed {
    void publishPaymentProcessed(String orderId, String paymentStatus);
}
