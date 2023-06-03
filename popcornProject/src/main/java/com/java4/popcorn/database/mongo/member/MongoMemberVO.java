package com.java4.popcorn.database.mongo.member;

import lombok.Data;

import java.util.List;

@Data
public class MongoMemberVO {
    //String _id;
    String member_id;
    String line_id;
    String kakao_id;
    List<String> theater_favorites;
    List<String> movie_favorites;
}
