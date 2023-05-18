package com.java4.popcorn.api.Kobis;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

import java.util.HashMap;
import java.util.Map;

public class KobisAPI {
    public static void main(String[] args){
        String kobis_KEY = "102955b1e67ec69156fad182a886776b";
        KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(kobis_KEY);

        Map<String, String> m4r = new HashMap<>();
        m4r.put("curPage", "0");
        ObjectMapper om = new ObjectMapper();
        int num = 10;
        int i = 2969;
        while (num == 10){
            try {
                System.out.println(i + "번째 페이지");
                m4r.put("curPage", String.valueOf(i));
                KobisResponseVO kr = om.readValue(kobis.getMovieList(true, m4r), KobisResponseVO.class);
//            System.out.println(kr);
//            System.out.println(kr.getMovieListResult().getMovieList().size());
                num = kr.getMovieListResult().getMovieList().size();
                om.writeValue(new java.io.File("popcornProject\\file-outputs\\movieListResult" + i + ".json"), kr.getMovieListResult());
                i++;
            }catch (Exception e){
                System.out.println("Error at " + i + "번째 페이지");
                e.printStackTrace();
            }
        }

    }
}
