package com.java4.popcorn.line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestControllerForLine {
    @RequestMapping(method = RequestMethod.POST, value = "/line")
    public void test(@RequestBody String body){
        System.out.println("body: " + body);
        WebhookData wd = new WebhookData(body);
        System.out.println("wd: " + wd);
        System.out.println(wd.getEvents().get(0).getType().equals("follow"));
        System.out.println(wd.getEvents().get(0).getSource().getUserId());
        if(wd.getEvents().get(0).getType().equals("follow")){
            System.out.println(lineAPI.sendMessageTest("안녕하세요, 영화 알림 서비스 입니다.", wd.getEvents().get(0).getSource().getUserId()));
        }
    }

    @Autowired
    private LineAPI lineAPI;

    @RequestMapping(method = RequestMethod.GET, value = "/line2")
    public void messagetest(@RequestParam(value = "message") String message){
        System.out.println("lineAPI: " + lineAPI);
        //lineAPI.sendMessageTest(message);
    }

}
