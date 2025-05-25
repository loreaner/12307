package com.example.order.entity;

import lombok.Data;

@Data
// 文件位置: order-module/src/main/java/com/example/order/event/TicketOrderCreateEvent.java
public class TicketOrderCreateEvent {
    private String orderId;
    private String userId;
    private long createdAt;

    public TicketOrderCreateEvent(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.createdAt = System.currentTimeMillis();
    }

    // Getter 和 Setter
}
