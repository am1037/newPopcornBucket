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
            return om.readValue(file, Schedule.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
