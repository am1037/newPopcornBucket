package com.java4.popcorn.api.line.message.temp;

import lombok.Data;

@Data
public class Event {

    private String replyToken;
    private String type;
    private String mode;
    private long timestamp;
    private Source source;
    private String webhookEventId;
    private DeliveryContext deliveryContext;
    private LineMessage message;

    // getters and setters
}

