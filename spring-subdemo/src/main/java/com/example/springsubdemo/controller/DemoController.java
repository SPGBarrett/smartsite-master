package com.example.springsubdemo.controller;

import io.swagger.annotations.Api;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-07-03 10:39
 **/
@EnableDiscoveryClient
@CrossOrigin
@RestController
@RequestMapping({"/SpringDemo"})
public class DemoController {
    // Restful API availability test:
    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test2(@RequestBody String strJson) {
        return "This is a test from port: " + 6005;
    }
}
