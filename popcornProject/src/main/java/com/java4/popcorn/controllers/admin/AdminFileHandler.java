package com.java4.popcorn.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AdminFileHandler {
//    public AdminFileHandler() {
//        File file = new File("error");
//    }
//
    public File[] readErrorJsons() {
        File file = new File("error");
        return file.listFiles();
    }

    public List<String> readErrorJsonsAsStringList() {
        File[] files = readErrorJsons();
        List<String> list = new ArrayList<>();
        for(File file : files) {
            ObjectMapper om = new ObjectMapper();
            String str;
            try {
                str = om.readValue(file, String.class);
                list.add(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
