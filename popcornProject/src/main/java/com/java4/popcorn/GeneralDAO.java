package com.java4.popcorn;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralDAO<T> {
    private T vo;
    private String className;
    private String alias;

    @Autowired
    SqlSessionTemplate my;

    public boolean insertOne(T vo) {
        return my.insert(alias+".insertOne", vo) == 1;
    }
    public boolean insertOne() {
        return my.insert(alias+".insertOne", vo) == 1;
    }

    public T selectOne(T vo){
        return my.selectOne(alias+".selectOne", vo);
    }
    public T selectOne(){
        return my.selectOne(alias+".selectOne", vo);
    }
    public T getVo() {
        return vo;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public void setVo(T vo) {
        className = vo.getClass().getSimpleName();
        alias = className.substring(0, className.length()-2) + "DAO";
        System.out.println(alias);
        this.vo = vo;
    }
}
