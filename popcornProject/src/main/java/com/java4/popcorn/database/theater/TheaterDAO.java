package com.java4.popcorn.database.theater;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TheaterDAO {

    @Autowired
    SqlSessionTemplate my;

    List<TheaterVO> theaterCodes;

    public List<TheaterVO> getTheaterCodes() {
        if(theaterCodes ==null){
            theaterCodes = this.selectAllTheaterCode();
        }
        return theaterCodes;
    }

    public List<TheaterVO> getTheaterCodes(String region) {
        theaterCodes = this.selectTheaterByRegion(region);
        return theaterCodes;
    }

    public List<TheaterVO> selectAllTheaterCode(){
        try {
            return my.selectList("TheaterDAO.selectAllTheater");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> selectAllRegion(){
        return my.selectList("TheaterDAO.selectAllRegion");
    }
    public List<TheaterVO> selectTheaterByRegion(String region){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_region", region);
            return my.selectList("TheaterDAO.selectTheatersByRegion", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void crawlingAllTheaterCodes(){
        String url = "http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=";
        String num;
        Document document = null;
        List<TheaterVO> list = new ArrayList<>();
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
                    TheaterVO vo = new TheaterVO();
                    vo.setTheater_name(str);
                    vo.setTheater_id(num);
                    vo.setTheater_location(location);
                    vo.setTheater_company("cgv");
                    vo.setTheater_region(location.split(" ")[0]);
                    list.add(vo);
                    my.insert("MovieScreenDAO.insertOneTheater", vo);
                    //System.out.println(vo);
                    //System.out.println("cgv,"+num + "," + str + "," + location);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
