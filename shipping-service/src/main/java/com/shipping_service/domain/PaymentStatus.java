package com.shipping_service.domain;

public enum PaymentStatus {
    APPROVED, DECLINED;

    public static PaymentStatus from(String status) {
        if (status == null) return null;
        return PaymentStatus.valueOf(status.toUpperCase());
    }
}
