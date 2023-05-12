package com.java4.popcorn.crawling.DB;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieScreenVO {

    String title;
    String theater;
    String screen;
    String time;
    String date;
    String theater_id;
    String movie_id;

}
