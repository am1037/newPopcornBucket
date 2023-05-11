package com.java4.popcorn.crawling;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {
    String theater; // 0001 0002 ...
    String date; // YYYYMMDD
    List<MovieScreenVO> movieScreenList;
}
