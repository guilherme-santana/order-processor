package com.shipping_service.infrastructure.persistence;

import com.shipping_service.domain.ShippingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "shipping")
public class ShippingEntity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    public ShippingEntity(String id, ShippingStatus shippingStatus) {
        this.id = id;
        this.shippingStatus = shippingStatus;
    }
    public ShippingEntity() {
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
