package com.java4.popcorn.controllers;

@Deprecated
public class KakaoTestController {
//
//    @Autowired
//    MyLittleKakaoAPI kakaoAPI;
//
//    String baseUrl;
//    String token;
//
//    @RequestMapping("/kakaoTest")
//    public String kakaoTest(HttpServletRequest request,
//                            Model model){
//
//        baseUrl = request.getRequestURL().toString();
//        System.out.println("baseUrl : " + baseUrl);
//        String host = "https://kauth.kakao.com";
//        String restAPIKey = "04b500e325879415373f7b3fa5ff0e86";
//        String str = "/oauth/authorize?response_type=code&client_id=" + restAPIKey + "&redirect_uri=" + baseUrl;
//        model.addAttribute("targetUrl", host+str);
//        //ex)
//        //https://kauth.kakao.com/oauth/authorize?client_id=04b500e325879415373f7b3fa5ff0e86&response_type=code&redirect_uri=http://localhost:8080/kakaoTest
//        return "kakaoTest";
//    }
//
//    @RequestMapping("/kakaoTest/kakaoLogin")
//    public String kakaoGetToken(@RequestParam(value = "code", required = false) String code,
//                              Model model){
//        if(code == null){
//            return "kakaoTest";
//        }
//        System.out.println("kakaoLogin code : " + code);
//        System.out.println(baseUrl + "kakaoLogin");
//        KakaoGetTokenResponse kgtr = kakaoAPI.kakaoGetToken(code, baseUrl + "kakaoLogin");
//        token = kgtr.getAccess_token();
//        System.out.println("access_token : " + kgtr.getAccess_token());
//
//        return "kakaoTest";
//    }
//
//    @RequestMapping("/kakaoTest/kakaoLogout")
//    public String kakaoLogout(@RequestParam(value = "code", required = false) String code,
//                              HttpServletRequest request,
//                              Model model){
//        if(code == null){
//            return "kakaoTest";
//        }
//        System.out.println("kakaoLogout code : " + code);
//        System.out.println(baseUrl + "kakaoLogout");
//        KakaoGetTokenResponse kgtr = kakaoAPI.kakaoGetToken(code, baseUrl + "kakaoLogout");
//        token = kgtr.getAccess_token();
//        System.out.println(kakaoAPI.kakaoLogout(kgtr));
//        request.getSession().removeAttribute("kakaoId");
//
//        return "kakaoTest";
//    }
//
//    @RequestMapping("/kakaoTest/kakaoLine")
//    public String kakaoGetTokenFromLine(@RequestParam(value = "code", required = false) String code,
//                                Model model){
//        if(code == null){
//            return "kakaoTest";
//        }
//        System.out.println("kakaoLogin code : " + code);
//        System.out.println(baseUrl + "kakaoLogin");
//        KakaoGetTokenResponse kgtr = kakaoAPI.kakaoGetToken(code, baseUrl + "kakaoLogin");
//        System.out.println("kakaoToken : "+kgtr.getAccess_token());
//        KakaoTokenInfoResponse ktir = kakaoAPI.kakaoGetTokenInfo(kgtr);
//        System.out.println("kakaoId : "+ktir.getId());
//
//        return "kakaoTest";
//    }
//    @RequestMapping("/kakaoTest/kakaoGetTokenInfo")
//    public void kakaoGetTokenInfo(HttpServletRequest request){
//        KakaoGetTokenResponse kgtr = new KakaoGetTokenResponse();
//        kgtr.setAccess_token(token);
//        System.out.println(kgtr.getAccess_token());
//        KakaoTokenInfoResponse kakaoTokenInfoResponse = kakaoAPI.kakaoGetTokenInfo(kgtr);
//        System.out.println(kakaoTokenInfoResponse.getId());
//        request.getSession().setAttribute("kakaoId", kakaoTokenInfoResponse.getId());
//    }
}
