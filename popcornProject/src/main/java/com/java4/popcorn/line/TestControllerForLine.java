package com.java4.popcorn.line;

import com.java4.popcorn.dbtest.MongoMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestControllerForLine {

    @Autowired
    private MongoMemberDAO mongoMemberDAO;

    @Autowired
    private LineAPI lineAPI;
    @RequestMapping(method = RequestMethod.POST, value = "/line")
    public void test(@RequestBody String body){
        System.out.println("body: " + body);
        WebhookData wd = new WebhookData(body);
        String userId = wd.getEvents().get(0).getSource().getUserId();
        System.out.println("wd: " + wd);
        if(wd.getEvents().get(0).getType().equals("follow")){
            System.out.println(lineAPI.sendMessageTest("안녕하세요, 영화 알림 서비스 입니다.", userId));
            mongoMemberDAO.insertOne(userId);
        }else if(wd.getEvents().get(0).getType().equals("unfollow")){
            mongoMemberDAO.deleteOne(userId);
        }else {
            System.out.println(lineAPI.sendMessageTest("아직 아무 기능도 없답니다.", userId));
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/line2")
    public void messagetest(@RequestParam(value = "message") String message){
        System.out.println("lineAPI: " + lineAPI);
        //lineAPI.sendMessageTest(message);
    }

}
