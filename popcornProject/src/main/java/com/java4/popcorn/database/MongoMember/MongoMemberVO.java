package com.java4.popcorn.database.MongoMember;

import lombok.Data;

import java.util.List;

@Data
public class MongoMemberVO {
    //String _id;
    String id;
    String line_id;
    String kakao_id;
    List<String> theater_favorites;
    List<String> movie_favorites;
}
