package com.java4.popcorn.cgv;

import com.java4.popcorn.DB.TheaterCodeVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
오직 cgv 극장 코드를 받기 위한 단발성 이용을 위한 코드입니다.
cgv의 현재 극장들을 크롤링하여 극장,코드의 csv 형태로 출력합니다.
 */
@Deprecated
public class cgvTheaterCodeCrwaling {
//    public void insertTheaterCodes(){
//        String url = "http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=";
//        String num;
//        Document document = null;
//        List<TheaterCodeVO> list = new ArrayList<>();
//        for (int i = 1; i <= 400; i++) {
//            num = String.format("%04d", i);
//            try {
//                Connection conn = Jsoup.connect(url+num);
//                document = conn.get();
//                String str = document.title();
//                Elements elements = document
//                        .select("#cgvwrap")
//                        .select("#contaniner")
//                        .select(".wrap-theater")
//                        .select(".sect-theater")
//                        .select(".wrap-theaterinfo")
//                        .select(".theater-info");
//                //.select(".title");
//                String[] strs = elements.select(".title").toString().split("<br>");
//                String location = strs[1].trim().split("<")[0];
//                if(str.length()>0) {
//                    int startIndex = str.indexOf("[") + 1;
//                    int endIndex = str.indexOf("]");
//                    str = str.substring(startIndex, endIndex);
//                    TheaterCodeVO vo = new TheaterCodeVO();
//                    vo.setTheater_name(str);
//                    vo.setTheater_id(num);
//                    vo.setTheater_location(location);
//                    vo.setTheater_company("cgv");
//                    vo.setTheater_region(location.split(" ")[0]);
//                    list.add(vo);
//                    my.insert("theater.insertTheater",vo);
//                    System.out.println(vo);
//                    //System.out.println("cgv,"+num + "," + str + "," + location);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public static void main(String[] args) {
//        String url = "http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=";
//        String num;
//        Document document = null;
//        List<TheaterCodeVO> list = new ArrayList<>();
//        for (int i = 1; i <= 400; i++) {
//            num = String.format("%04d", i);
//            try {
//                Connection conn = Jsoup.connect(url+num);
//                document = conn.get();
//                String str = document.title();
//                Elements elements = document
//                        .select("#cgvwrap")
//                        .select("#contaniner")
//                        .select(".wrap-theater")
//                        .select(".sect-theater")
//                        .select(".wrap-theaterinfo")
//                        .select(".theater-info");
//                        //.select(".title");
//                String[] strs = elements.select(".title").toString().split("<br>");
//                String location = strs[1].trim().split("<")[0];
//                if(str.length()>0) {
//                    int startIndex = str.indexOf("[") + 1;
//                    int endIndex = str.indexOf("]");
//                    str = str.substring(startIndex, endIndex);
//                    TheaterCodeVO vo = new TheaterCodeVO();
//                    vo.setTheater_name(str);
//                    vo.setTheater_id(num);
//                    vo.setTheater_location(location);
//                    vo.setTheater_company("cgv");
//                    vo.setTheater_region(location.split(" ")[0]);
//                    list.add(vo);
//                    //my.insert("theater.insertTheater",vo);
//                    System.out.println(vo);
//                    //System.out.println("cgv,"+num + "," + str + "," + location);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
