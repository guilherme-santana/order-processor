package com.order_service.infrastructure.controller;

import com.order_service.application.CreateOrderUseCase;
import com.order_service.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Map<String, String> payload) {
        String clientId = payload.get("clientId");
        BigDecimal totalValue = new BigDecimal(payload.get("totalValue"));
        Order order = createOrderUseCase.execute(clientId, totalValue);
        return ResponseEntity.ok(order);
    }
}
