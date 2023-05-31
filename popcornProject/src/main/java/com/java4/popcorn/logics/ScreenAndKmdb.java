package com.java4.popcorn.logics;

import com.java4.popcorn.api.kmdb.KmdbAPI;
import com.java4.popcorn.controllers.SharedPropertiesStore;
import com.java4.popcorn.database.m2m.Movie2MovieDAO;
import com.java4.popcorn.database.screen.ScreenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScreenAndKmdb {

    @Autowired
    Movie2MovieDAO m2mDAO;
    @Autowired
    ScreenDAO screenDAO;
    @Autowired
    KmdbAPI kmdbAPI;

    @Autowired
    SharedPropertiesStore sps;

    //즉
    //1. 크롤링을 통해 screen DB를 채운다(cgv crawrler의 역할입니다)
    //2. 1에서 얻은 movie_title을 kmdbAPI에 쿼리하여 DOCID를 얻는다
    //  2-1. 기본적으로 1대1이 성립하면 일단 넣고
    //  2-2. 1대1이 성립하지 않으면 상영시간이 같은 것을 넣고
    //  2-3. 그래도 매칭되지 않으면 json 등으로 에러 로그를 출력합니다.
    //3. screen에 DOCID를 지정할 때는 DOCID의 값을 사용합니다.


}
