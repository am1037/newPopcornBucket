package com.java4.popcorn.movieInfo;

import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.notmine.bbs.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDAO {
    @Autowired
    GeneralDAO<MovieVO> dao;

    public boolean insertOne(MovieVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

}