package com.java4.popcorn.notnow.Kobis.movieInfo.notmine.movieReco;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRecoDAO {
    @Autowired
    GeneralDAO<MovieRecoVO> dao;

    public boolean insertOne(MovieRecoVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public MovieRecoVO selectOne(MovieRecoVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
