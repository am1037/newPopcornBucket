package com.java4.popcorn.notmine.userSimilarity;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.notmine.rankUser.RankUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSimilarityDAO {
    @Autowired
    GeneralDAO<UserSimilarityVO> dao;

    public boolean insertOne(UserSimilarityVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public UserSimilarityVO selectOne(UserSimilarityVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
