package com.java4.popcorn.crawling.cgv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.data.screen.ScreenVO;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;

//VO for CGV
@Data
public class Schedule {
    String theater; // 0001 0002 ...
    String date; // YYYYMMDD
    List<ScreenVO> movieScreenList;
    public void printAsJsonFile(String path){
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(new File(path), this);
            System.out.println("Saved as " + path);
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
            theater = file.getName().substring(0,4);
            this.date = file.getName().substring(6, 13);
            this.movieScreenList = schedule.getMovieScreenList();
            return schedule;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public void printAsJsonFile(){
        ObjectMapper om = new ObjectMapper();
        String filename = "schedule"+this.getTheater()+this.getDate()+".json";
        try {
            om.writeValue(new File("popcornProject\\file-outputs\\"+filename), this);
            System.out.println("Saved as " + filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
