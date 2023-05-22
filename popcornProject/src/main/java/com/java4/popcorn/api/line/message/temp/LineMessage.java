package com.java4.popcorn.api.line.message.temp;

import lombok.Data;

@Data
public class LineMessage {
    String type = "text";
    String id;
    String text = "default text";
}
