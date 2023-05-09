package com.java4.popcorn.crawling;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieScreenDAO {
    @Autowired
    SqlSessionTemplate my;

    public void selectAll(){
        try {
            List<MovieScreenVO> msl = my.selectList("ms.selectAll");
            System.out.println(msl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(MovieScreenVO ms){
        try {
            my.insert("ms.insertOne", ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
