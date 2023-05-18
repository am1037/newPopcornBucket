package com.java4.popcorn.notmine.member;


import com.java4.popcorn.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDAO {
    @Autowired
    GeneralDAO<MemberVO> dao;

    public boolean insertOne(MemberVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

    public MemberVO selectOne(MemberVO vo) {
        dao.setVo(vo);
        return dao.selectOne();
    }

    public void printAddress(){
        System.out.println(dao);
    }

}
