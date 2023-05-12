package com.java4.popcorn.cgv;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Deprecated
public class ScheduleWeek {
    List<Schedule> scheduleList = new ArrayList<>();
    Map<String, Integer> movieCount = new HashMap<>();

    public void setMovieCountFromJson(String tcode, int i1, int i2) {
        File file;
        ObjectMapper om = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        Schedule schedule;
        ScheduleWeek scheduleWeek = new ScheduleWeek();

        for(int i = i1; i <= i2; i++) {
            file = new File("popcornProject\\file-outputs\\schedule" + tcode + String.valueOf(i) +".json");
            try {
                schedule = om.readValue(file, Schedule.class);
                //System.out.println(schedule);
                scheduleWeek.getScheduleList().add(schedule);
                schedule.getMovieScreenList().forEach(x -> {
                    map.putIfAbsent(x.getTitle(), 0);
                    map.computeIfPresent(x.getTitle(), (k, v) -> v + 1);
                });
                scheduleWeek.setMovieCount(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.forEach((k, v) -> System.out.println(k + " : " + v));
        //System.out.println(scheduleWeek);
    }
}
