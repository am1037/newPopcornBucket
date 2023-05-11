package com.java4.popcorn.notmine.rankActor;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.notmine.mycalendar.MyCalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankActorDAO {
    @Autowired
    GeneralDAO<RankActorVO> dao;

    public boolean insertOne(RankActorVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public RankActorVO selectOne(RankActorVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
