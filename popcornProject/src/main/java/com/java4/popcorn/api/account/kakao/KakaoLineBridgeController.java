package com.java4.popcorn.api.account.kakao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.api.line.message.LineAPI;
import com.java4.popcorn.api.line.message.WebhookData;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Controller
public class KakaoLineBridgeController {
    //https://developers.kakao.com/console/app/860023/product/login
    //위 링크에서 redirect 허용 url 수정할 필요가 있다.
    final String baseUrl = "http://localhost:8887/newPopcornBucket_war_exploded/";
    final String redirectURI = baseUrl + "stepThree";
    //stepTwo가 붙은 url은 stepTwo method로 가는 url,
    //stepThree가 붙은 url은 stepThree method로 가는 url이다.
    //stepOne이 없는 것은, 라인과의 상호작용 중 한 케이스(친구추가)가 stepOne이기 때문.

    final String host = "https://kauth.kakao.com";
    final String restAPIKey = "04b500e325879415373f7b3fa5ff0e86";
    final String targetURL = host + "/oauth/authorize?client_id=" + restAPIKey + "&redirect_uri=" + redirectURI + "&response_type=code";
    // targetURL 예시 : https://kauth.kakao.com/oauth/authorize?client_id=04b500e325879415373f7b3fa5ff0e86&redirect_uri=https://11e3-115-137-12-120.ngrok-free.app/newPopcornBucket_war_exploded/kakaoTest/kakaoLine&response_type=code

    public String getTargetURL() {
        return targetURL;
    }

    @Autowired
    MongoMemberDAO mongoMemberDAO;
    @Autowired
    LineAPI lineAPI;

    //stepOne : 유저가 라인을 친구추가하면, 이 때 전달되는 라인 ID를 이용해서 헤더에 값을 포함한 get request를 만든다.
    @RequestMapping(method = RequestMethod.POST, value = "/line")
    public void line(@RequestBody String body){ //stepOne
        System.out.println("body: " + body);
        WebhookData wd = new WebhookData(body);
        String lineId = wd.getEvents().get(0).getSource().getUserId();
        System.out.println("wd: " + wd);
        if(wd.getEvents().get(0).getType().equals("follow")){
            //String str = "안녕하세요, 영화 알림 서비스 입니다. 다음 링크를 통하여 개인 식별용 카카오톡 계정을 등록해주세요.\n" + getTargetURL();
            String urlWithLineId = baseUrl + "stepTwo?lineId=" + lineId;
            String str = "안녕하세요, 영화 알림 서비스 입니다. 다음 링크를 통하여 개인 식별용 카카오톡 계정을 등록해주세요.\n" + urlWithLineId;
            System.out.println(lineAPI.sendMessageTest(str, lineId));
            //mongoMemberDAO.insertOne(userId);
        }else if(wd.getEvents().get(0).getType().equals("unfollow")){
            mongoMemberDAO.deleteOne(lineId);
        }else {
            System.out.println(lineAPI.sendMessageTest("아직 아무 기능도 없답니다.", lineId));
        }
    }

    //stepTwo : 이 곳에서 targetURL을 향해 유저를 보내, 카카오톡 서버로 하여금 이쪽으로 알림을 보내게 한다.
    //유저는 이 과정에서 우리쪽 페이지로 리다이렉트하게 된다.
    @RequestMapping(method = RequestMethod.GET, value = "/stepTwo")
    public String stepTwo(@RequestParam("lineId") String lineId,
                        HttpServletRequest request){
        //System.out.println("lineId: " + lineId);
        request.getSession().setAttribute("lineId", lineId);
        System.out.println("targetURL: " + targetURL);
        return "redirect:"+targetURL;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stepThree")
    public String stepThree(@RequestParam(value = "code", required = false) String code,
                            HttpServletRequest request,
                            Model model){
        String token = kakaoGetToken(code, redirectURI);
        request.getSession().setAttribute("kakaoId", kakaoGetIdFromToken(token));

        String kakaoId = request.getSession().getAttribute("kakaoId").toString();
        try {
            if(mongoMemberDAO.selectOneByKakaoId(kakaoId)!=null){
                return "kakaoAlreadyRegistered";
            }
            String lineId = request.getSession().getAttribute("lineId").toString();
            mongoMemberDAO.insertOne(lineId, kakaoId, new ArrayList<String>(), new ArrayList<String>());
            return "kakaoLoginComplete";
        }catch (Exception e){
            System.out.println("kakao login failed");
            return "kakaoLoginFailed";
        }
    }

    public String kakaoGetIdFromToken(String token){
        System.out.println("Kakao, getting Id from access token");

        String temp = "https://kapi.kakao.com/v1/user/access_token_info";
        URL url = null;
        try {
            url = new URL(temp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            System.out.println("Kakao, getting Id from access token : responseCode : " + conn.getResponseCode());
            return valueFromJson(conn.getInputStream(), "id");
        }catch (Exception e) {
            if(e.getMessage().contains("401"))
                System.out.println("Temporary stop message : Kakao, getting token info failed by 401");
            else
                System.out.println("Temporary stop message : Kakao, getting token info failed, but not 401");
            return null;
        }
    }
    @RequestMapping("/kakaoTest/checkSession")
    public void checkSession(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("kakaoId"));
        System.out.println(request.getSession().getAttribute("lineId"));
    }
    public String kakaoGetToken(String code, String redirectURI) {
        System.out.println("code : " + code);
        System.out.println("redirectURI : " + redirectURI);
        String str = "/oauth/token";
        URL url;
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

            return valueFromJson(conn.getInputStream(), "access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private String valueFromJson(InputStream is, String key){
        System.out.println("Kakao, getting value from json");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String result = sb.toString();
            System.out.println("result : " + result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            return jsonNode.get(key).asText();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
