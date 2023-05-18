package com.java4.popcorn.api.line.temp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliveryContext {

    @JsonProperty("isRedelivery")
    private boolean isRedelivery;

    // getters and setters
}
