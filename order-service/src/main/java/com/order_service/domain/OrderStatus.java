package com.order_service.domain;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAYMENT_FAILED,
    PAID,
    PENDING_SHIPMENT,
    SENT,
    COMPLETED,
    CANCELED;


    public static OrderStatus orderStatus(String status) {
        try {
            if (status.contains("DECLINED")) {
                return OrderStatus.PAYMENT_FAILED;
            } else if (status.contains("APPROVED")) {
                return OrderStatus.PENDING_SHIPMENT;
            }
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Status inválido" + status);
        }
    }
}
