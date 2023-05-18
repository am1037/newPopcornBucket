package com.java4.popcorn.api.kmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class KmdbAPI {
    String key = "&ServiceKey="+System.getenv("key4kmdb");
    String str = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&detail=Y";
    public KmdbMovieSimpleInfoResponseVO getMovieInfo(int startCount){
        try {
            String strstr = "&listCount=110";
            HttpURLConnection connection = (HttpURLConnection) new URL(str+strstr+key+"&startCount="+startCount).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            ObjectMapper om = new ObjectMapper();
            return om.readValue(connection.getInputStream(), KmdbMovieSimpleInfoResponseVO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getMovieInfoByString(int startCount){
        try {
            String strstr = "&listCount=110";
            HttpURLConnection connection = (HttpURLConnection) new URL(str+strstr+key+"&startCount="+startCount).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public KmdbMovieSimpleInfoResponseVO getMovieInfoByTitle(String title){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(str+key+"&title="+title).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            ObjectMapper om = new ObjectMapper();
            return om.readValue(connection.getInputStream(), KmdbMovieSimpleInfoResponseVO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        KmdbAPI kmdbAPI = new KmdbAPI();
//        for(int i=0;i<100;i++) {
//            System.out.println(i);
//            KmdbMovieSimpleInfoResponse kmdbMovieSimpleInfoResponse = kmdbAPI.getMovieInfo(i);
//            kmdbMovieSimpleInfoResponse.printAsJson("kmdb/"+"yy" + i + ".json");
//        }
        System.out.println(kmdbAPI.getMovieInfoByString(0));
        //System.out.println(KmdbMovieSimpleInfoResponse.readFromJson("10.json").getData().get(0).getResult());

    }
}
