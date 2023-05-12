package com.java4.popcorn.cgv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.crawling.DB.MovieScreenVO;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
public class Schedule {
    String theater; // 0001 0002 ...
    String date; // YYYYMMDD
    List<MovieScreenVO> movieScreenList;
    public void printAsJsonFile(){
        ObjectMapper om = new ObjectMapper();
        String filename = "schedule"+this.getTheater()+this.getDate()+".json";
        try {
            om.writeValue(new File("popcornProject\\file-outputs\\"+filename), this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Schedule fromJson(String tcode, int date){
        ObjectMapper om = new ObjectMapper();
        File file = new File("popcornProject\\file-outputs\\schedule" + tcode + date +".json");
        try {
            Schedule schedule = om.readValue(file, Schedule.class);
            theater = tcode;
            this.date = String.valueOf(date);
            this.movieScreenList = schedule.getMovieScreenList();
            return schedule;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Schedule fromJson(File file){
        ObjectMapper om = new ObjectMapper();
        try {
            Schedule schedule = om.readValue(file, Schedule.class);
            theater = file.getName().substring(8,12);
            this.date = file.getName().substring(12,20);
            this.movieScreenList = schedule.getMovieScreenList();
            return schedule;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
