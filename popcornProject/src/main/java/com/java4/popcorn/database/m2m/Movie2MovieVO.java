package com.java4.popcorn.database.m2m;

import lombok.Data;

@Data
public class Movie2MovieVO {
    String movie_title;
    String DOCID;
    int m2m_pk;
    public String getKmdbURL() {
        //ex) https://www.kmdb.or.kr/db/kor/detail/movie/K/04845
        return "https://www.kmdb.or.kr/db/kor/detail/movie/"+DOCID.charAt(0)+"/"+DOCID.substring(1);
    }
}
