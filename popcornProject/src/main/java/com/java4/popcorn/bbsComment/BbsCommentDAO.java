package com.java4.popcorn.bbsComment;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.bbs.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BbsCommentDAO {
    @Autowired
    GeneralDAO<BbsCommentVO> dao;

    public boolean insertOne(BbsCommentVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public BbsCommentVO selectOne(BbsCommentVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
