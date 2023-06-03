package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.controllers.alarm.subclasses.AlarmVO;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.mongo.member.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmService {
    @Autowired
    ScreenDAO screenDAO;

    @Autowired
    MongoMemberDAO mongoMemberDAO;

    @Autowired
    SharedPropertiesStore store;

    public void setProperties(){

    }



    public MongoMemberVO getMemberByKakaoId(String kakaoId){
        return mongoMemberDAO.selectOneByKakaoId(kakaoId);
    }

    public void constructAlarm(MongoMemberVO vo){
        List<String> listTheater = vo.getTheater_favorites();
        List<String> listMovie = vo.getMovie_favorites();
        String lindId = vo.getLine_id();
    }
}
