package com.java4.popcorn.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/*
오직 cgv 극장 코드를 받기 위한 단발성 이용을 위한 코드입니다.
cgv의 현재 극장들을 크롤링하여 극장,코드의 csv 형태로 출력합니다.
 */
public class cgvTheaterCodeCrwaling {
    public static void main(String[] args) {
        String url = "http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=";
        String num;
        Document document = null;
        for (int i = 1; i <= 600; i++) {
            num = String.format("%04d", i);
            try {
                String str = Jsoup.connect(url+num).get().title();
                if(str.length()>0) {
                    int startIndex = str.indexOf("[") + 1;
                    int endIndex = str.indexOf("]");
                    str = str.substring(startIndex, endIndex);
                    System.out.println(num + "," + str);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
