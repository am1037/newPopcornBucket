package com.java4.popcorn;

import com.java4.popcorn.database.theater.TheaterDAO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NaverMapController {
    @Autowired
    TheaterDAO theaterDAO;
    @Autowired
    NaverMapTest naverMapTest;

    @RequestMapping("/naverMap")
    public String crawlingAllTheaterCodes(@RequestParam String address,
                                          Model model) throws Exception{
        String result = naverMapTest.byAddress(address);
        model.addAttribute("result", result);
        return result;
    }

}
