package com.java4.popcorn.api.line.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.api.line.message.temp.LineMessage;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class LineMessageRequest {
    private String to = "U18f79b1492deb6b43ae022c04acc586a";
    private List<LineMessage> messages;

    public List<LineMessage> addInnerTextMessage(String text){
        if(messages == null){
            messages = new ArrayList<>();
        }
        LineMessage lineInnerMessage = new LineMessage();
        lineInnerMessage.setText(text);
        messages.add(lineInnerMessage);
        return messages;
    }

    public String printAsJsonFile() {
        return printAsJsonFile("message.json");
    }

    public String printAsJsonFile(String path) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(new File(path), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}
