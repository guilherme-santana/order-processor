package com.shipping_service.application;

import com.shipping_service.domain.Shipping;
import com.shipping_service.domain.ShippingRepository;
import com.shipping_service.domain.ShippingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateShippingUseCase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ShippingRepository shippingRepository;

    public CreateShippingUseCase(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public void execute(Shipping shipping) {
        if (shipping == null || shipping.getShippingStatus() == null) {
            logger.warn("M=execute, shipping ou status nulo. Ignorando.");
            return;
        }
        logger.info("M=execute, status={}, shipping={}", shipping.getShippingStatus(), shipping);

        if (ShippingStatus.PENDING_SHIPMENT.equals(shipping.getShippingStatus())) {
            logger.info("M=execute salvando no repositório...");
            shippingRepository.save(shipping);
        } else {
            logger.info("M=execute, status não é PENDING_SHIPMENT. Nada salvo.");
        }
    }
}
