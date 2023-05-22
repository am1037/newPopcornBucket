package com.java4.popcorn.api.account.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class MyLittleKakaoAPI {
    String host = "https://kauth.kakao.com";
    String redirectURI = "http://localhost:8887/newPopcornBucket_war_exploded/kakaoRedirect";
    String restAPIKey = "04b500e325879415373f7b3fa5ff0e86";
    public int kakaoGetAccessCode(String redirectURI){
        String str = "/oauth/authorize?client_id=" + restAPIKey + "&redirect_uri=" + redirectURI + "&response_type=code";
        URL url;
        try {
            url = new URL(host + str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            return conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public KakaoGetTokenResponse kakaoGetToken(String code, String redirectURI) {
        System.out.println("Kakao, getting token for code : " + code);
        String str = "/oauth/token";
        URL url = null;
        try {
            url = new URL(host + str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String requestBody = "grant_type=" + URLEncoder.encode("authorization_code", "UTF-8") +
                    "&client_id=" + URLEncoder.encode(restAPIKey, "UTF-8") +
                    "&redirect_uri=" + redirectURI +
                    "&code=" + URLEncoder.encode(code, "UTF-8");
            os.write(requestBody.getBytes());
            os.flush();
            os.close();
            System.out.println("responseCode : " + conn.getResponseCode());
            if (conn.getResponseCode() != 200) {
                System.out.println("Temporary stop message : Kakao, getting token failed");
                return null;
            }
            return inputStreamToClass(conn.getInputStream(), KakaoGetTokenResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int kakaoLogout(KakaoGetTokenResponse tokenResponse){
        System.out.println("Kakao, logging out");
        String temp = "https://kapi.kakao.com/v1/user/logout";
        URL url = null;
        try {
            url = new URL(temp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + tokenResponse.getAccess_token());
            return conn.getResponseCode();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public KakaoTokenInfoResponse kakaoGetTokenInfo(KakaoGetTokenResponse tokenResponse){
        System.out.println("Kakao, getting token info");
        String temp = "https://kapi.kakao.com/v1/user/access_token_info";
        URL url = null;
        try {
            url = new URL(temp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + tokenResponse.getAccess_token());
            return inputStreamToClass(conn.getInputStream(), KakaoTokenInfoResponse.class);
        }catch (Exception e) {
            if(e.getMessage().contains("401"))
                System.out.println("Temporary stop message : Kakao, getting token info failed by 401");
            else
                System.out.println("Temporary stop message : Kakao, getting token info failed, but not 401");
            return null;
        }
    }

    private <T> T inputStreamToClass(InputStream is, Class<T> clazz) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ObjectMapper om = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return om.readValue(sb.toString(), clazz);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
