package com.java4.popcorn.api.line.message;

import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class LineAPI {
    URL url;
    private static final String token = "Bearer "+System.getenv("line_token");

    public String writeMessage(String content, String to){
        //json 작성
        LineMessageRequest mj = new LineMessageRequest();
        mj.setTo(to);
        mj.addInnerTextMessage(content);
        return mj.printAsJsonFile();
    }
    public int sendMessageTest(String content, String to){
        try {
            url = new URL("https://api.line.me/v2/bot/message/push");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //토큰 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", token);

            //json 작성
            String str = writeMessage(content, to);

            //json 첨부
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            String jsonContent = new String(Files.readAllBytes(Paths.get(str)), StandardCharsets.UTF_8);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(jsonContent.getBytes(StandardCharsets.UTF_16));
            outputStream.flush();
            outputStream.close();
            return con.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
