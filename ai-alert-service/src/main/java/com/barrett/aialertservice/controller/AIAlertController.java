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
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @program: smartsite-master
 * @description: Restful API for AI alert
 * @author: Barrett
 * @create: 2020-05-05 11:12
 **/
@CrossOrigin
@RestController
@RequestMapping({"/AIAlert"})
@Api(tags = {"AI模块后端接口"})
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
    @Value("${ai.detect.helmet.log.path}")
    String helmetLogFileLocation;

    // Insert one line of data:
    @ApiOperation(value = "接收并储存安全帽预警", notes="将算法推送的数据，解析后保存至数据库")
    @ApiImplicitParams({ @ApiImplicitParam(name = "helmetAlertInfoInput", value = "安全帽预警数据", dataType = "HelmetAlertInfoInput", paramType = "body", required = true)})
    @RequestMapping(value = {"/HelmetAlert"}, method = {RequestMethod.POST})
    public String insert(@RequestBody HelmetAlertInfoInput helmetAlertInfoInput) {
        System.out.println(helmetAlertInfoInput);
        // Save data to database:
        return helmetAlertInfoService.insertHelmetAlertData(helmetAlertInfoInput).toString();
    }

    @ApiOperation(value = "接收并储存安全帽预警并推送", notes="将算法推送的数据，解析后保存至数据库，然后推送至预警中心模块")
    @ApiImplicitParams({ @ApiImplicitParam(name = "helmetAlertInfoInput", value = "安全帽预警数据", dataType = "HelmetAlertInfoInput", paramType = "body", required = true)})
    @RequestMapping(value = {"/HelmetAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushHelmet(@RequestBody HelmetAlertInfoInput helmetAlertInfoInput) {
        try{
            System.out.println(new Date());
            System.out.println(helmetAlertInfoInput);
            // Save data to database:
            AIAlertPushMsg thisMsg = helmetAlertInfoService.insertHelmetAlertData(helmetAlertInfoInput);
            alertPushService.pushHelmetAlertMsg(thisMsg);
            return thisMsg;
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
            return null;
        }
    }

    @ApiOperation(value = "接收并储存反光背心预警并推送", notes="将算法推送的数据，解析后保存至数据库，然后推送至预警中心模块")
    @ApiImplicitParams({ @ApiImplicitParam(name = "vestAlertInfoInput", value = "反光背心预警数据", dataType = "VestAlertInfoInput", paramType = "body", required = true)})
    @RequestMapping(value = {"/VestAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushVest(@RequestBody VestAlertInfoInput vestAlertInfoInput) {
        try{
            System.out.println(new Date());
            System.out.println(vestAlertInfoInput);
            // Save data to database:
            AIAlertPushMsg thisMsg = vestAlertInfoService.insertVestAlertData(vestAlertInfoInput);
            alertPushService.pushClothesAlertMsg(thisMsg);
            return thisMsg;
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
            return null;
        }
    }

    @ApiOperation(value = "接收并储存着装预警并推送", notes="将算法推送的数据，解析后保存至数据库，然后推送至预警中心模块")
    @ApiImplicitParams({ @ApiImplicitParam(name = "clothesAlertInfoInput", value = "着装预警数据", dataType = "ClothesAlertInfoInput", paramType = "body", required = true)})
    @RequestMapping(value = {"/ClothesAlert/Push"}, method = {RequestMethod.POST})
    public AIAlertPushMsg insertAndPushClothes(@RequestBody ClothesAlertInfoInput clothesAlertInfoInput) {
        try{
            System.out.println(new Date());
            System.out.println(clothesAlertInfoInput);
            // Save data to database:
            AIAlertPushMsg thisMsg = clothesAlertInfoService.insertClothesAlertData(clothesAlertInfoInput);
            alertPushService.pushClothesAlertMsg(thisMsg);
            return thisMsg;
        }catch (Exception e){
            System.out.println("推送预警消息失败！");
            return null;
        }
    }


    // Restful API availability test:
    @ApiOperation(value = "测试API存活接口")
    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        System.out.println("Testing Requesting!");
        return "This is a test from port: " + currentPort;
    }

    // Restful API availability test:
    @ApiOperation(value = "测试API存活接口", notes="测试API存活接口")
    @ApiImplicitParams({ @ApiImplicitParam(name = "strJson", value = "测试字符串", paramType = "query", required = true)})
    @RequestMapping(value = {"/test2"}, method = {RequestMethod.POST})
    public String test22(@RequestBody String strJson) {
        System.out.println(strJson);
        return "This is a test from port: " + currentPort;
    }

    // Restful API availability test for clothes:
    @ApiOperation(value = "保存着装推送字符串到文件")
    @ApiImplicitParams({ @ApiImplicitParam(name = "strJson", value = "着装推送字符串", paramType = "query", required = true)})
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

    // Restful API availability test for vest:
    @ApiOperation(value = "保存反光背心推送字符串到文件")
    @ApiImplicitParams({ @ApiImplicitParam(name = "strJson", value = "反光背心推送字符串", paramType = "query", required = true)})
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

    // Restful API availability test for helmet
    @ApiOperation(value = "保存安全帽推送字符串到文件")
    @ApiImplicitParams({ @ApiImplicitParam(name = "strJson", value = "安全帽推送字符串", paramType = "query", required = true)})
    @RequestMapping(value = {"/test5"}, method = {RequestMethod.POST})
    public String test25(@RequestBody String strJson) {
        File logFile = new File(helmetLogFileLocation);//新建一个文件对象，如果不存在则创建一个该文件
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
