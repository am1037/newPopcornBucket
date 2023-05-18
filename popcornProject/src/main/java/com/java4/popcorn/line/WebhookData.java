package com.java4.popcorn.line;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.line.temp.Event;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
