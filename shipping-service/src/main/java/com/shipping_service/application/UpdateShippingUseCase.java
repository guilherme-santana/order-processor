package com.shipping_service.application;

import com.shipping_service.domain.Shipping;
import com.shipping_service.domain.ShippingPublisher;
import com.shipping_service.domain.ShippingRepository;
import com.shipping_service.domain.ShippingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateShippingUseCase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ShippingRepository shippingRepository;
    private final ShippingPublisher shippingPublisher;

    public UpdateShippingUseCase(ShippingRepository shippingRepository, ShippingPublisher shippingPublisher) {
        this.shippingRepository = shippingRepository;
        this.shippingPublisher = shippingPublisher;
    }

    public Shipping execute(String id, ShippingStatus shippingStatus) {
        Shipping shipping = shippingRepository.update(id, shippingStatus);
        shippingPublisher.publisherShippingEvent(shipping);
        logger.info("M=execute, id= {}, status={}", shipping.getId(), shipping.getShippingStatus());
        return shipping;
    }
}
