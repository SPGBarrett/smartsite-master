package com.barrett.springbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-16 16:23
 **/
@Controller
public class TestStaticPage {

    @RequestMapping({"/WebPageTestIndex"})
    public String WebPageTest(Map<String, Object> map){
        // Put data into map:
        map.put("name", "Barrett");
        map.put("age", "30");
        map.put("info", "To get real good offer!");
        return "WebPageTest";

    }
}
