package com.order_service.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Order {

    private String id;
    private String clientId;
    private BigDecimal totalValue;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdate;
    private OrderStatus status;

    public Order(String id, String clientId, BigDecimal totalValue) {
        this.id = id;
        this.clientId = clientId;
        this.totalValue = totalValue;
        this.dateCreated = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.status = OrderStatus.PENDING_PAYMENT;
    }

    public Order(String id, String clientId, BigDecimal totalValue, OrderStatus status) {
        this.id = id;
        this.clientId = clientId;
        this.totalValue = totalValue;
        this.status = status;
    }

    public Order(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ZonedDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(ZonedDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
