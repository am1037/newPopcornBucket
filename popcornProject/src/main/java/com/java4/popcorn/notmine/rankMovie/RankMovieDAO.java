package com.java4.popcorn.notmine.rankMovie;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.notmine.rankActor.RankActorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankMovieDAO {
    @Autowired
    GeneralDAO<RankMovieVO> dao;

    public boolean insertOne(RankMovieVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public RankMovieVO selectOne(RankMovieVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
