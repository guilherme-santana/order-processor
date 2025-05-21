package com.shipping_service.application;

import com.shipping_service.domain.Shipping;
import com.shipping_service.domain.ShippingRepository;
import com.shipping_service.domain.ShippingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateShippingUseCase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ShippingRepository shippingRepository;

    public UpdateShippingUseCase(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public Shipping execute(String id, ShippingStatus shippingStatus) {
        Shipping shipping = shippingRepository.update(id, shippingStatus);
        logger.info("M=execute, id= {}, status={}", shipping.getId(), shipping.getShippingStatus());
        return shipping;
    }
}
