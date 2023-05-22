package com.java4.popcorn.api.account.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Deprecated
@Controller
public class KakaoTestController {

    @Autowired
    MyLittleKakaoAPI kakaoAPI;

    String token;

    @RequestMapping("/kakaoTest")
    public String kakaoTest(Model model){
        String host = "https://kauth.kakao.com";
        String rediURI = "http://localhost:8887/newPopcornBucket_war_exploded/kakaoRedirect";
        String restAPIKey = "04b500e325879415373f7b3fa5ff0e86";
        String str = "/oauth/authorize?client_id=" + restAPIKey + "&redirect_uri=" + rediURI + "&response_type=code";
        model.addAttribute("kakao", host+str);
        return "kakaoTest";
    }

    @RequestMapping("/kakaoLogin")
    public void kakaoGetToken(@RequestParam("code") String code,
                              Model model){
        System.out.println("kakaoLogin code : " + code);
        KakaoGetTokenResponse kgtr = kakaoAPI.kakaoGetToken(code, "http://localhost:8887/newPopcornBucket_war_exploded/kakaoLogin");
        token = kgtr.getAccess_token();
        System.out.println("access_token : " + kgtr.getAccess_token());
    }

    @RequestMapping("/kakaoLogout")
    public void kakaoLogout(@RequestParam("code") String code,
                            Model model){
        System.out.println("kakaoLogout code : " + code);
        KakaoGetTokenResponse kgtr = kakaoAPI.kakaoGetToken(code, "http://localhost:8887/newPopcornBucket_war_exploded/kakaoLogout");
        token = kgtr.getAccess_token();
        System.out.println(kakaoAPI.kakaoLogout(kgtr));
    }

    @RequestMapping("/kakaoGetTokenInfo")
    public void kakaoGetTokenInfo(){
        KakaoGetTokenResponse kgtr = new KakaoGetTokenResponse();
        kgtr.setAccess_token(token);
        System.out.println(kakaoAPI.kakaoGetTokenInfo(kgtr));
    }
}
