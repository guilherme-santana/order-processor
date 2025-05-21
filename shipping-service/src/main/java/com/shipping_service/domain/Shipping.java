package com.shipping_service.domain;

public class Shipping {

    private String id;
    private ShippingStatus shippingStatus;


    public Shipping(String id, ShippingStatus shippingStatus) {
        this.id = id;
        this.shippingStatus = shippingStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
