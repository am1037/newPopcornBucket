package com.java4.popcorn.notmine.moviecalendar;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieCalendarDAO {
    @Autowired
    GeneralDAO<MovieCalendarVO> dao;

    public boolean insertOne(MovieCalendarVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public MovieCalendarVO selectOne(MovieCalendarVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
