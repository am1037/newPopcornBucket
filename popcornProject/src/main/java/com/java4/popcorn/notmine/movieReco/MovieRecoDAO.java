package com.java4.popcorn.notmine.movieReco;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.notmine.movieJjim.MovieJjimVO;
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
