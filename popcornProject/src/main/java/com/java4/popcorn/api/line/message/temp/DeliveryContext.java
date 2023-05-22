package com.java4.popcorn.api.line.message.temp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliveryContext {

    @JsonProperty("isRedelivery")
    private boolean isRedelivery;

    // getters and setters
}
