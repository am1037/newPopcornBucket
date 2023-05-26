package com.java4.popcorn.controllers.alarm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlarmController {
    @RequestMapping(method = RequestMethod.GET, value = "alarm/")
    public String alarm_main(Model model) {
        return "alarm/alarm/alarm_main";
    }

    @RequestMapping(method = RequestMethod.GET, value = "alarm/navbar")
    public String alarm_navbar(Model model) {
        return "alarm/navbar";
    }


}
