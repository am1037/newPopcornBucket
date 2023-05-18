package com.java4.popcorn.data.screen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.data.theater.TheaterVO;
import com.java4.popcorn.crawling.cgv.Schedule;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScreenDAO {
    @Autowired
    SqlSessionTemplate my;

    List<TheaterVO> theaterCodes;
    public List<ScreenVO> selectByTheaterAndDate(String theater, String data){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater);
            map.put("data", data);
            return my.selectList("ScreenDAO.selectByTheaterAndDate", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ScreenVO> selectByTheater(String theater_id){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater_id);
            return my.selectList("ScreenDAO.selectByTheater", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertJson(File file){
        try {
            System.out.println(file.toString());
            ObjectMapper om = new ObjectMapper();
            Schedule schedule = om.readValue(file, Schedule.class);
            schedule.getMovieScreenList().forEach(this::insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert(ScreenVO ms){
        try {
            //System.out.println(ms);
            my.insert("ScreenDAO.insertOne", ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int insertExistingFiles(){
        String path = "schedules/";
        File dir = new File(path);
        File[] files = dir.listFiles();
        int count = 0;
        if(files == null){
            System.out.println("no files");
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                insertJson(file);
                count++;
            }
        }
        return count;
    }

    @Deprecated
    public int insertFromUntilByTheater(String theater, String dateFrom, String dateUntil){
        int i1 = Integer.parseInt(dateFrom);
        int i2 = Integer.parseInt(dateUntil);
        int count = 0;
        for (int i=i1; i<=i2; i++){
            String date = String.valueOf(i);
            System.out.println("theater: "+theater + ", date: "+date);
            Schedule schedule;
            try {
                schedule = new Schedule().fromJson(new File("schedules/"+theater+"_"+date+".json"));
                schedule.getMovieScreenList().forEach(this::insert);
                System.out.println("theater: "+theater + ", date: "+date+" inserted");
                count++;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<TheaterVO> selectAllTheaterCode(){
        try {
            return my.selectList("ScreenDAO.selectAllTheater");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void truncateTable(){
        try {
            my.update("ScreenDAO.truncateScreen");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Deprecated
    public List<ScreenVO> selectAll(){
        try {
            List<ScreenVO> msl = my.selectList("ScreenDAO.selectAll");
            System.out.println(msl);
            return msl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    public List<ScreenVO> select0513(String str1, String str2){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("str1", str1);
            map.put("str2", str2);
            return my.selectList("ScreenDAO.select0513", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
