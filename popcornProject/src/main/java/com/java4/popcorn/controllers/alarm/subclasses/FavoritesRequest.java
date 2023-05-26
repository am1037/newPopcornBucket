package com.java4.popcorn.controllers.alarm.subclasses;

import lombok.Data;

import java.util.List;

@Data
public class FavoritesRequest {
    List<String> primaries;
    List<String> warnings;
    List<String> dangers;
}
