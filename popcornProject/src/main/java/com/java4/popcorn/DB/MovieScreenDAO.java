package com.java4.popcorn.DB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.cgv.Schedule;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieScreenDAO {
    @Autowired
    SqlSessionTemplate my;

    List<TheaterCodeVO> theaterCodes;

    public List<TheaterCodeVO> getTheaterCodes() {
        if(theaterCodes ==null){
            theaterCodes = this.selectAllTheaterCode();
        }
        return theaterCodes;
    }

    public List<MovieScreenVO> selectByTheaterAndDate(String theater, String data){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater);
            map.put("data", data);
            return my.selectList("MovieScreenDAO.selectByTheaterAndDate", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<MovieScreenVO> selectByTheater(String theater_id){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater_id);
            return my.selectList("MovieScreenDAO.selectByTheater", map);
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
    public void insert(MovieScreenVO ms){
        try {
            //System.out.println(ms);
            my.insert("MovieScreenDAO.insertOne", ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TheaterCodeVO> selectAllTheaterCode(){
        try {
            return my.selectList("MovieScreenDAO.selectAllTheater");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TheaterCodeVO selectOneTheater(String theater_id){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater_id);
            return my.selectOne("MovieScreenDAO.selectOneTheater", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void truncateTable(){
        try {
            my.update("MovieScreenDAO.truncateScreen");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertTheaterCodes(){
        String url = "http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=";
        String num;
        Document document = null;
        List<TheaterCodeVO> list = new ArrayList<>();
        for (int i = 1; i <= 400; i++) {
            num = String.format("%04d", i);
            try {
                Connection conn = Jsoup.connect(url+num);
                document = conn.get();
                String str = document.title();
                Elements elements = document
                        .select("#cgvwrap")
                        .select("#contaniner")
                        .select(".wrap-theater")
                        .select(".sect-theater")
                        .select(".wrap-theaterinfo")
                        .select(".theater-info");
                //.select(".title");
                if(str.length()>0) {
                    //System.out.println(str);
                    String[] strs;
                    String location;
                    try {
                        strs = elements.select(".title").toString().split("<br>");
                        location = strs[1].trim().split("<")[0];
                    }catch (ArrayIndexOutOfBoundsException e){
                        strs = elements.select(".title").toString().split("<br>");
                        location = strs[0]
                                .replace("<div class=\"theater-info\">", "")
                                .replace("<strong class=\"title\">", "")
                                .trim()
                                .split("<")[0];
                    }
                    int startIndex = str.indexOf("[") + 1;
                    int endIndex = str.indexOf("]");
                    str = str.substring(startIndex, endIndex);
                    TheaterCodeVO vo = new TheaterCodeVO();
                    vo.setTheater_name(str);
                    vo.setTheater_id(num);
                    vo.setTheater_location(location);
                    vo.setTheater_company("cgv");
                    vo.setTheater_region(location.split(" ")[0]);
                    list.add(vo);
                    my.insert("MovieScreenDAO.insertOneTheater", vo);
                    System.out.println(vo);
                    //System.out.println("cgv,"+num + "," + str + "," + location);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Deprecated
    public List<MovieScreenVO> selectAll(){
        try {
            List<MovieScreenVO> msl = my.selectList("ms.selectAll");
            System.out.println(msl);
            return msl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    public List<MovieScreenVO> select0513(String str1, String str2){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("str1", str1);
            map.put("str2", str2);
            return my.selectList("MovieScreenDAO.select0513", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
