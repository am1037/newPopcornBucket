package com.java4.popcorn.line.temp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliveryContext {

    @JsonProperty("isRedelivery")
    private boolean isRedelivery;

    // getters and setters
}
