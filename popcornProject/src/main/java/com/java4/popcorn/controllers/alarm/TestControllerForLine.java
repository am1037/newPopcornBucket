package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.api.account.kakao.KakaoLineBridgeController;
import com.java4.popcorn.api.line.message.LineAPI;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.screen.ScreenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestControllerForLine {

    @Autowired
    private MongoMemberDAO mongoMemberDAO;

    @Autowired
    private LineAPI lineAPI;

    @Autowired
    private ScreenDAO screenDAO;

    @Autowired
    private KakaoLineBridgeController kakaoLineBridgeController;

//    @RequestMapping(method = RequestMethod.POST, value = "/line")
//    public void test(@RequestBody String body){
//        System.out.println("body: " + body);
//        WebhookData wd = new WebhookData(body);
//        String userId = wd.getEvents().get(0).getSource().getUserId();
//        System.out.println("wd: " + wd);
//        if(wd.getEvents().get(0).getType().equals("follow")){
//            String str = "안녕하세요, 영화 알림 서비스 입니다. 다음 링크를 통하여 개인 식별용 카카오톡 계정을 등록해주세요.\n" + kakaoLineBridgeController.getTargetURL();
//            System.out.println(lineAPI.sendMessageTest(str, userId));
//            mongoMemberDAO.insertOne(userId);
//        }else if(wd.getEvents().get(0).getType().equals("unfollow")){
//            mongoMemberDAO.deleteOne(userId);
//        }else {
//            //System.out.println(lineAPI.sendMessageTest("아직 아무 기능도 없답니다.", userId));
//            String message = wd.getEvents().get(0).getMessage().getText();
//            String str = screenDAO.test2(message);
//            System.out.println("str: " + str);
//            System.out.println("userId: " + userId);
//            System.out.println(lineAPI.sendMessageTest(str, userId));
//        }
//    }


    @RequestMapping(method = RequestMethod.GET, value = "/line2")
    public void messagetest(@RequestParam(value = "message") String message){
        System.out.println("lineAPI: " + lineAPI);
        //lineAPI.sendMessageTest(message);
    }

}
