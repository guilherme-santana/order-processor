package com.shipping_service.infrastructure.persistence;

import com.shipping_service.domain.Shipping;
import com.shipping_service.domain.ShippingRepository;
import com.shipping_service.domain.ShippingStatus;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingRepositoryImpl implements ShippingRepository {
    private final ShippingRepositoryJpa shippingRepositoryJpa;

    public ShippingRepositoryImpl(ShippingRepositoryJpa shippingRepositoryJpa) {
        this.shippingRepositoryJpa = shippingRepositoryJpa;
    }

    @Override
    public void save(Shipping shipping) {
        ShippingEntity shippingEntity = new ShippingEntity(shipping.getId(), shipping.getShippingStatus());
        shippingRepositoryJpa.save(shippingEntity);
    }

    @Override
    public Shipping update(String id, ShippingStatus shippingStatus) {
        Shipping shipping = findById(id);
        ShippingEntity shippingEntity = new ShippingEntity(shipping.getId(), shippingStatus);
        shippingRepositoryJpa.save(shippingEntity);
        shipping.setShippingStatus(shippingStatus);
        return shipping;
    }

    @Override
    public Shipping findById(String id) {
        ShippingEntity shippingEntity = shippingRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Orden não encontrada."));
        return new Shipping(shippingEntity.getId(), shippingEntity.getShippingStatus());
    }
}
