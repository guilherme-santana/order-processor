package com.order_service.infrastructure.persistence;

import com.order_service.domain.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String id;
    private String clientId;
    private BigDecimal totalValue;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public ZonedDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(ZonedDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public OrderEntity(String id, String clientId, BigDecimal totalValue, ZonedDateTime dateCreated, ZonedDateTime dateUpdate, OrderStatus status) {
        this.id = id;
        this.clientId = clientId;
        this.totalValue = totalValue;
        this.dateCreated = dateCreated;
        this.dateUpdate = dateUpdate;
        this.status = status;
    }

    public OrderEntity() {
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
}
