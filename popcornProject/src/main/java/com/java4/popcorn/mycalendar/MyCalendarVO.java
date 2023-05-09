package com.java4.popcorn.mycalendar;

import java.sql.Date;

import lombok.Data;

@Data
public class MyCalendarVO {
    private int calendarlogNo;
    private Date myDate;
    private Integer movieId;
    private String memberId;
}
