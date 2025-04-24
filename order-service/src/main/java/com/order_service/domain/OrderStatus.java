package com.order_service.domain;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAYMENT_FAILED,
    PAID,
    PENDING_SHIPMENT,
    COMPLETED,
    CANCELED
}
