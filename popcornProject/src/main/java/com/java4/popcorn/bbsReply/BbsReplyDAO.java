package com.java4.popcorn.bbsReply;


import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.bbsComment.BbsCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BbsReplyDAO {
    @Autowired
    GeneralDAO<BbsReplyVO> dao;

    public boolean insertOne(BbsReplyVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public BbsReplyVO selectOne(BbsReplyVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
