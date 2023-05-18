package com.java4.popcorn.line;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class LineMessage {
    private String to = "U18f79b1492deb6b43ae022c04acc586a";
    private List<LineInnerMessage> messages;

    public List<LineInnerMessage> addInnerTextMessage(String text){
        if(messages == null){
            messages = new ArrayList<>();
        }
        LineInnerMessage lineInnerMessage = new LineInnerMessage();
        lineInnerMessage.setText(text);
        messages.add(lineInnerMessage);
        return messages;
    }
    @Data
    static class LineInnerMessage {
        String type = "text";
        String text = "default text";
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
