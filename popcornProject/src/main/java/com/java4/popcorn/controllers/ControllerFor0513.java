package com.java4.popcorn.controllers;

import com.java4.popcorn.crawling.cgv.CGV;
import com.java4.popcorn.data.screen.ScreenDAO;
import com.java4.popcorn.data.screen.ScreenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Deprecated
@Controller
public class ControllerFor0513 {
    @Autowired
    ScreenDAO screenDAO;

//    @RequestMapping(method = RequestMethod.GET, value = "/")
//    public String test() {
//        return "home";
//    }

    // 일단 count해서 정렬
    // 상영관 적은 게 앞에 오도록 정렬한 다음에
    // mov_fav.contains해서 있으면+상영관 적으면 빨강색, 있으면+상영관 보통이면 주황색
    // 남은 것들은 하얀색에 정렬하도록
    @RequestMapping(method = RequestMethod.GET, value = "/111")
    public String test2(
                    @RequestParam(value = "tfavs") String tfs,
                    @RequestParam(value = "mfavs") String mfs,
                    @RequestParam(value = "threshold") int threshold, Model model) {
        String[] tfL = tfs.split(",");
        List<String> mfL = Arrays.asList(mfs.split(","));
        List<ScreenVO> msL = new ArrayList<>();
        for(String t:tfL){
            msL.addAll(screenDAO.selectByTheater(t));
            System.out.println("msL: "+msL);
        }
        Map<String, Integer> map = CGV.count(msL);
        List<String> strings = new ArrayList<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> strings.add(e.getKey()));
        for(String s: strings){
            System.out.println(s+" "+map.get(s));
        }

        //
        List<String> redList = new ArrayList<>();
        List<String> orangeList = new ArrayList<>();
        List<String> lightList = new ArrayList<>();
        String str;
        while (strings.size()>0) {
            str = strings.remove(0);
            if (mfL.contains(str)) {
                if (map.get(str) <= threshold) {
                    redList.add(str);
                } else {
                    orangeList.add(str);
                }
            } else {
                lightList.add(str);
            }
        }

        model.addAttribute("redList", redList);
        model.addAttribute("orangeList", orangeList);
        model.addAttribute("lightList", lightList);
        System.out.println("redList: "+redList);
        System.out.println("orangeList: "+orangeList);
        System.out.println("lightList: "+lightList);
        model.addAttribute("map", map);

        return "111";
    }

}
