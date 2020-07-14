package com.barrett.aialertservice.controller;

import com.barrett.aialertservice.service.ClothesAlertInfoService;
import com.barrett.aialertservice.service.HelmetAlertInfoService;
import com.barrett.aialertservice.service.VestAlertInfoService;
import com.barrett.aialertservice.util.AlertPushService;
import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.ClothesAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.VestAlertInfoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @program: smartsite-master
 * @description: Restful API for AI alert
 * @author: Barrett
 * @create: 2020-05-05 11:12
 **/

@EnableDiscoveryClient
@CrossOrigin
@RestController
@RequestMapping({"/AIAlert"})
@Api(tags = {"登录/注销接口"})
public class AIAlertController {
    @Autowired
    HelmetAlertInfoService helmetAlertInfoService;
    @Autowired
    VestAlertInfoService vestAlertInfoService;
    @Autowired
    ClothesAlertInfoService clothesAlertInfoService;
    @Autowired
    AlertPushService alertPushService;
    @Value("${server.port}")
    String currentPort;
    @Value("${ai.detect.vest.log.path}")
    String vestLogFileLocation;
    @Value("${ai.detect.clothes.log.path}")
    String clotheLogFileLocation;

    // Insert one line of data:
    @RequestMapping(value = {"/HelmetAlert"}, method = {RequestMethod.POST})
    public String insert(@RequestBody HelmetAlertInfoInput helmetAlertInfoInput) {
        System.out.println(helmetAlertInfoInput);
        // Save data to database:
        return helmetAlertInfoService.insertHelmetAlertData(helmetAlertInfoInput).toString();
    }

    @RequestMapping(value = {"/HelmetAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushHelmet(@RequestBody HelmetAlertInfoInput helmetAlertInfoInput) {
        System.out.println(new Date());
        System.out.println(helmetAlertInfoInput);
        // Save data to database:
        AIAlertPushMsg thisMsg = helmetAlertInfoService.insertHelmetAlertData(helmetAlertInfoInput);
        try{
            alertPushService.pushHelmetAlertMsg(thisMsg);
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                alertPushService.pushAlertMsg(thisMsg);
//            }
//        }).start();
        return thisMsg;
    }

    @RequestMapping(value = {"/VestAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushVest(@RequestBody VestAlertInfoInput vestAlertInfoInput) {
        System.out.println(new Date());
        System.out.println(vestAlertInfoInput);
        // Save data to database:
        AIAlertPushMsg thisMsg = vestAlertInfoService.insertVestAlertData(vestAlertInfoInput);
        try{
            alertPushService.pushClothesAlertMsg(thisMsg);
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
        }
        return thisMsg;
    }

    @RequestMapping(value = {"/ClothesAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushClothes(@RequestBody ClothesAlertInfoInput clothesAlertInfoInput) {
        System.out.println(new Date());
        System.out.println(clothesAlertInfoInput);
        // Save data to database:
        AIAlertPushMsg thisMsg = clothesAlertInfoService.insertClothesAlertData(clothesAlertInfoInput);
        try{
            alertPushService.pushClothesAlertMsg(thisMsg);
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
        }
        return thisMsg;
    }



    // Restful API availability test:
    @ApiOperation(value = "查询用户接口")
    @ApiImplicitParams({ @ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", paramType = "query", required = true)})
    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        System.out.println("Testing Requesting!");
        return "This is a test from port: " + currentPort;
    }

    // Restful API availability test:
    @RequestMapping(value = {"/test2"}, method = {RequestMethod.POST})
    public String test22(@RequestBody String strJson) {
        System.out.println(strJson);
        return "This is a test from port: " + currentPort;
    }

    // Restful API availability test:
    @RequestMapping(value = {"/test3"}, method = {RequestMethod.POST})
    public String test23(@RequestBody String strJson) {
        File logFile = new File(clotheLogFileLocation);//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(logFile);
            fw.write(strJson);//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "This is a test from port: " + currentPort;
    }

    // Restful API availability test:
    @RequestMapping(value = {"/test4"}, method = {RequestMethod.POST})
    public String test24(@RequestBody String strJson) {
        File logFile = new File(vestLogFileLocation);//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(logFile);
            fw.write(strJson);//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "This is a test from port: " + currentPort;
    }

}
