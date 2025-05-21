package com.shipping_service.domain;

public interface ShippingRepository {

    void save(Shipping shipping);
    Shipping update(String id, ShippingStatus shippingStatus);

    Shipping findById(String id);

}
