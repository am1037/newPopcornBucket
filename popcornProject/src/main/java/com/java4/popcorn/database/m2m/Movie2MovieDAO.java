package com.java4.popcorn.database.m2m;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Movie2MovieDAO {

    @Autowired
    SqlSessionTemplate my;

//<mapper namespace="m2mDAO">
//    <select id="selectByDOCID" resultType="java.com.java4.popcorn.database.m2m.Movie2MovieVO">
//    select * from popcorn.movie2movie where DOCID = #{DOCID}
//</select>
//
//<select id="selectByTitle" resultType="java.com.java4.popcorn.database.m2m.Movie2MovieVO">
//    select * from popcorn.movie2movie where movie_title = #{movie_title}
//</select>
//
//<insert id="insertOne">
//    insert into popcorn.movie2movie
//    values(#{DOCID}, #{movie_title})
//</insert>
//
//<update id="updateByDOCID">
//    update popcorn.movie2movie
//    set movie_title = #{movie_title}
//    where DOCID = #{DOCID}
//</update>
//
//<update id="updateByTitle">
//    update popcorn.movie2movie
//    set DOCID = #{DOCID}
//    where movie_title = #{movie_title}
//</update>

    public Movie2MovieVO selectByDOCID(String DOCID) {
        return my.selectOne("m2mDAO.selectByDOCID", DOCID);
    }
    public Movie2MovieVO selectByTitle(String movie_title) {
        return my.selectOne("m2mDAO.selectByTitle", movie_title);
    }
    public void insertOne(Movie2MovieVO vo) {
        my.insert("m2mDAO.insertOne", vo);
    }
    public void updateByDOCID(Movie2MovieVO vo) {
        my.update("m2mDAO.updateByDOCID", vo);
    }
    public void updateByTitle(Movie2MovieVO vo) {
        my.update("m2mDAO.updateByTitle", vo);
    }



}
