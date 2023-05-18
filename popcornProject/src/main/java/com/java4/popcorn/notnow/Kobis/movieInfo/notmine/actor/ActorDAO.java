package com.java4.popcorn.notnow.Kobis.movieInfo.notmine.actor;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorDAO {
    @Autowired
    GeneralDAO<ActorVO> dao;

    public boolean insertOne(ActorVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public ActorVO selectOne(ActorVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
