package com.java4.popcorn.cgv;

import com.java4.popcorn.DB.MovieScreenVO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CGV {

    String url = "http://www.cgv.co.kr/theaters/?areacode=02"; //오리
    String str1 = "&theaterCode=";
    String str2 = "&date=";

    public Schedule crawl(String theater, String date) {
        /*
        theater ex)0004와
        date ex)20230505를 받아서 대상 url을 구성하고
        그 날의 상영 영화, 스케줄을 받아온다
         */
        String s1 = str1 + theater;
        String s2 = str2 + date;
        Schedule schedule = new Schedule();
        schedule.setTheater(theater);
        schedule.setDate(date);
        List<MovieScreenVO> msList = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get(url + s1 + s2);
        driver.switchTo().frame("ifrm_movie_time_table");

        List<WebElement> coltimesList = driver.findElement(By.className("showtimes-wrap"))
                .findElement(By.className("sect-showtimes"))
                .findElements(By.className("col-times"));
        //이따가 그림으로 정리하기! -> 일단 정리함!
        for (WebElement we : coltimesList) {
            String movieTitle = we.findElement(By.className("info-movie"))
                                  .findElement(By.tagName("strong")).getText();
            List<WebElement> typehallList = we.findElements(By.className("type-hall"));
            for (WebElement h : typehallList) {
                List<WebElement> showList = h.findElement(By.className("info-timetable"))
                                             .findElements(By.tagName("li"));
                for (WebElement s : showList) {
                    MovieScreenVO ms = new MovieScreenVO();
                    ms.setTitle(movieTitle);
                    ms.setTheater("cgv");
                    ms.setTheater_id(theater);
                    ms.setScreen(h.findElement(By.className("info-hall")).findElements(By.tagName("li")).get(1).getText());
                    String[] tempo = s.getText().split("\n");
                    ms.setTime(tempo[0]);
                    ms.setDate(date);
                    msList.add(ms);
                    //System.out.println(ms);
                }
            }
        }
        driver.close();
        schedule.setMovieScreenList(msList);
        return schedule;
    }

    public MovieScreenVO readJson(String path) {
        File file = new File(path);
        return null;
    }


    public static Map<String, Integer> count (Schedule schedule) {
        return count(schedule.getMovieScreenList());
    }

    public static Map<String, Integer> count (List<MovieScreenVO> msList) {
        Map<String, Integer> map = new HashMap<>();
        msList.forEach(x -> {
            map.putIfAbsent(x.getTitle(), 0);
            map.computeIfPresent(x.getTitle(), (k, v) -> v + 1);
        });
        return map;
    }

    public static Map<String, Integer> count (String tcode, int i1, int i2) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=i1; i<=i2; i++) {
            Schedule schedule = new Schedule();
            schedule = schedule.fromJson(tcode, i);
            schedule.getMovieScreenList().forEach(x -> {
                map.putIfAbsent(x.getTitle(), 0);
                map.computeIfPresent(x.getTitle(), (k, v) -> v + 1);
            });
        }
        return map;
    }
}
