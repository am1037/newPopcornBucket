package com.java4.popcorn.api.account.kakao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class AlarmTest {

    public static void main(String[] args) {
        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://bizmsg-web.kakaoenterprise.com/v1/oauth/token");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.addRequestProperty("Authorization", "Basic JtruBDvStgLYXTs6qlUPbFwc2kwXAxPk");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            bos.flush();
            bos.close();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    responseBody.append(line);
                }
            }
        } catch (Exception e) {
            responseBody.append(e);
        }
        System.out.println(responseBody);
    }
}