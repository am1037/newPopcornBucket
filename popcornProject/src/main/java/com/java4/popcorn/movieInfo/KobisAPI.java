package com.java4.popcorn.movieInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

import java.util.HashMap;
import java.util.Map;

public class KobisAPI {
    public static void main(String[] args) throws Exception{
        //String kobis_KEY = "a17e223009d340bb7cc3be1a0c850983";
        String kobis_KEY = "3a4689aefb175c72ac62bf54c189e930";
        KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(kobis_KEY);

        Map<String, String> m4r = new HashMap<>();
        m4r.put("curPage", "0");
        m4r.put("directorNm", "애스터");
        ObjectMapper om = new ObjectMapper();
        int num = 10;
        int i = 1;
        while (num == 10){
                System.out.println(i + "번째 페이지");
                m4r.put("curPage", String.valueOf(i));
                KobisResponse kr = om.readValue(kobis.getMovieList(true, m4r), KobisResponse.class);
//            System.out.println(kr);
//            System.out.println(kr.getMovieListResult().getMovieList().size());
                num = kr.getMovieListResult().getMovieList().size();
                om.writeValue(new java.io.File("popcornProject\\file-outputs\\애스터_movieListResult" + i + ".json"), kr.getMovieListResult());
                i++;
        }

    }
}
