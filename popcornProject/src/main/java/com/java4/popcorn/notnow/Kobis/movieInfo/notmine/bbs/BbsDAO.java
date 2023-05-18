package com.java4.popcorn.notnow.Kobis.movieInfo.notmine.bbs;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BbsDAO {
    @Autowired
    GeneralDAO<BbsVO> dao;

    public boolean insertOne(BbsVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public BbsVO selectOne(BbsVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
