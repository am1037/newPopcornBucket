package com.java4.popcorn.database.movie.mapping;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieMappingDAO {

    @Autowired
    SqlSessionTemplate my;

    public List<MovieMappingVO> selectAll(){
        return my.selectList("MovieMappingDAO.selectAll");
    }

    public List<MovieMappingVO> selectByCompany(String company){
        Map<String, String> map = new HashMap<>();
        map.put("company", company);
        return my.selectList("MovieMappingDAO.selectByCompany", map);
    }

    public List<MovieMappingVO> selectOneByMovieId(String docid){
        Map<String, String> map = new HashMap<>();
        map.put("docid", docid);
        return my.selectList("MovieMappingDAO.selectByMovieId", map);
    }

}
