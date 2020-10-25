package com.barrett.authserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    public String testAuth(){
        return "通过验证返回数据！";
    }
}
