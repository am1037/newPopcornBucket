package com.java4.popcorn.rankUser;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.rankMovie.RankMovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankUserDAO {
    @Autowired
    GeneralDAO<RankUserVO> dao;

    public boolean insertOne(RankUserVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public RankUserVO selectOne(RankUserVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
