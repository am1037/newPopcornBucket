package com.java4.popcorn.notnow.Kobis.movieInfo.notmine.movieJjim;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieJjimDAO {
    @Autowired
    GeneralDAO<MovieJjimVO> dao;

    public boolean insertOne(MovieJjimVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public MovieJjimVO selectOne(MovieJjimVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
