package com.java4.popcorn.api.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginTest {

    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public String test(@RequestBody String body,
                       Model model) {
        //System.out.println("body: " + body);
        String[] strs = body.split("&");
        String[] strss = strs[0].split("\\.");
        for (String str : strss) {
            System.out.println(str);
        }
        System.out.println(strss[1]);

        return "test";
    }

}
