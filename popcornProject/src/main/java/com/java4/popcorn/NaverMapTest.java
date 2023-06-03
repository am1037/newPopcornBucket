package com.java4.popcorn;

import com.java4.popcorn.database.theater.TheaterDAO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class NaverMapTest {
    public static void main(String[] args) {
        try {
            System.out.println(new NaverMapTest().byAddress("서울특별시 강남구 테헤란로 152"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String byAddress(String address) throws Exception {
        String query = "?query=" + URLEncoder.encode(address, "UTF-8");
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode" + query;
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "apaqutnndn");
        con.setRequestProperty("X-NCP-APIGW-API-KEY", "Tpd4XqCveoqk4ALIJK6FLTptt76pCnWq9gdrEsJy");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

}
