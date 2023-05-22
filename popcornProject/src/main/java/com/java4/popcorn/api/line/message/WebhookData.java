package com.java4.popcorn.api.line.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.api.line.message.temp.Event;
import lombok.Data;

import java.util.List;
@Data
public class WebhookData {

    private String destination;
    private List<Event> events;

    public WebhookData() {
    }

    public WebhookData(String str){
        ObjectMapper om = new ObjectMapper();
        try {
            WebhookData wd = om.readValue(str, WebhookData.class);
            destination = wd.getDestination();
            events = wd.getEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // getters and setters
}
