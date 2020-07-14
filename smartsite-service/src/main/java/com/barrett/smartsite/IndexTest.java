package com.barrett.smartsite;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:27
 **/
@RestController
public class IndexTest {
    @RequestMapping({"/Index"})
    public String testIndex() {
        return "This is a demo of SpringBoot Application using component scan!";
    }

    @RequestMapping({"/errorTest"})
    public String errorTest(@RequestParam(value = "input", required = true) int input) {
        int j = 1 / input;
        return "Result: " + j;
    }
}
