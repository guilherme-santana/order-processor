package com.shipping_service.infrastructure.controller;

import com.shipping_service.application.UpdateShippingUseCase;
import com.shipping_service.domain.Shipping;
import com.shipping_service.domain.ShippingStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shipping")
public class ShippingManagementController {
    private final UpdateShippingUseCase updateShippingUseCase;

    public ShippingManagementController(UpdateShippingUseCase updateShippingUseCase) {
        this.updateShippingUseCase = updateShippingUseCase;
    }

    @PutMapping
    public ResponseEntity<Shipping> management(@RequestBody Map<String, String> payload) {
        String orderId = payload.get("orderId");
        ShippingStatus shippingStatus = ShippingStatus.valueOf(payload.get("status"));
        return ResponseEntity.ok(
                updateShippingUseCase.execute(orderId, shippingStatus)
        );
    }
}
