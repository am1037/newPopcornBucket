package com.java4.popcorn.movieJjim;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.member.MemberVO;
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
