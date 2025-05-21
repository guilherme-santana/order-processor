package com.order_service.domain.event;

public class OrderCreatedEvent {
    private String orderId;
    private String clientId;
    private String totalValue;
    private String status;
    private String dateCreated;

    public OrderCreatedEvent(String orderId, String clientId, String totalValue, String status, String dateCreated) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.totalValue = totalValue;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
