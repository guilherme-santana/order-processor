package com.shipping_service.application;

import com.payments.avro.OrderPaymentProcessed;
import com.shipping_service.domain.PaymentStatus;
import com.shipping_service.domain.ShippingStatus;

public class StatusAnaliseUseCase {

    public ShippingStatus execute(OrderPaymentProcessed orderPaymentProcessed) {
        PaymentStatus status = PaymentStatus.from(String.valueOf(orderPaymentProcessed.getPaymentStatus()));

        return (status == PaymentStatus.APPROVED)
                ? ShippingStatus.PENDING_SHIPMENT
                : ShippingStatus.DECLINED;
    }
}
