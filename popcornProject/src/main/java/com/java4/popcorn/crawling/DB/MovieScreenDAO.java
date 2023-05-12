package com.java4.popcorn.crawling.DB;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void insert(MovieScreenVO ms){
        try {
            System.out.println(ms);
            my.insert("MovieScreenDAO.insertOne", ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
