package com.java4.popcorn.notnow.Kobis.movieInfo.notmine.mycalendar;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCalendarDAO {
    @Autowired
    GeneralDAO<MyCalendarVO> dao;

    public boolean insertOne(MyCalendarVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public MyCalendarVO selectOne(MyCalendarVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
