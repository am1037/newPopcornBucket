package com.java4.popcorn.dbtest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.DriverManager;
import java.sql.SQLException;

public class testSession {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "314059ac";

        try {
            DriverManager.getConnection(url, username, password);
            System.out.println("연결 성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
