package com.java4.popcorn.database.screen;

import lombok.Data;

@Data
public class ScreenVO {

    String title;
    String company;
    String screen_name;
    String time;
    String date;
    String theater_id;
    String movie_docid;
    Integer runtime;
    String detail_url;
    String time_end;
    int pk;

}
