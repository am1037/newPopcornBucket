package com.java4.popcorn.cgv;

import com.java4.popcorn.crawling.DB.MovieScreenDAO;
import com.java4.popcorn.crawling.DB.MovieScreenVO;
import com.java4.popcorn.movieInfo.MovieVO;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        MovieScreenDAO dao = new MovieScreenDAO();
//        Schedule schedule = new Schedule();
//
//        String[] theaterCodes = {"0004", "0009", "0056", "0257"};
//        for(String t:theaterCodes){
//            for(int i=20230513;i<=20230520;i++){
//                List<MovieScreenVO> list = schedule.fromJson(t, i).getMovieScreenList();
//                for(MovieScreenVO ms:list){
//                    System.out.println(ms);
//                }
//            }
//        }
        //System.out.println(schedule);
//        크롤링 예시 입니다
//        CGV cgv = new CGV();
//        0004오리 0009명동 0056강남 0257광교에 있는 cgv의
//        20230513부터 20230520까지의 상영시간표를 크롤링합니다
//        String[] theaterCodes = {"0004", "0009", "0056", "0257"};
//        for(String t:theaterCodes){
//            for(int i=20230513;i<=20230520;i++){
//                cgv.crawl(t, String.valueOf(i)).printAsJsonFile();
//            }
//        }
        //cgv.count("0004", 20230513, 20230519).forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
