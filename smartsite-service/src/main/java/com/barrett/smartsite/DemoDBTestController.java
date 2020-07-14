package com.barrett.smartsite;


import com.barrett.smartsite.DemoDBTestController;
import com.barrett.smartsite.bean.DemoTest;
import com.barrett.smartsite.service.DemoTestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:26
 **/
@RestController
public class DemoDBTestController {
    @Autowired
    DemoTestService demoTestService;

    @RequestMapping({"/insert"})
    public int insertData(String p1, String p2, String d1) {
        return this.demoTestService.insert(p1, p2, d1);
    }

    @RequestMapping({"/getall"})
    public List<DemoTest> getAllData() {
        return this.demoTestService.findAll();
    }
}
