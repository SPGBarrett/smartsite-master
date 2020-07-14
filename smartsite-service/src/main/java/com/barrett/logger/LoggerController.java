package com.barrett.logger;

import com.barrett.smartsite.bean.MapScreenSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite
 * @description: Rest controller for logger
 * @author: Barrett
 * @create: 2020-02-13 08:59
 **/
@CrossOrigin
@RestController
@RequestMapping({"/Logger"})
public class LoggerController {
    @Autowired
    private LoggerService loggerService;
    /**
     * @Description: Online test for writing access log to database using provided API
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @RequestMapping(value = {"/AccessLoggerTest"}, method = {RequestMethod.GET})
    public String accessLoggerTest() {
        System.out.println("Writing access log using http request...");
        // Construct demo entity for access log:
        AccessLogRequestBody.AccessLogData data = new AccessLogRequestBody.AccessLogData("周冰66","spc-screen","用户列表访问测试测试","http://223.84.191.228:2200/SmartSitePatrolManage/index.html","testing params"
                ,338288333,"好像啥也没有做",348282353,"123","192.168.0.1", "Ubuntu", "Firefox", "Mozilla/5.0");
        List<AccessLogRequestBody.AccessLogData> dataList = new ArrayList<>();
        dataList.add(data);
        AccessLogRequestBody dataBody = new AccessLogRequestBody(dataList, 338288333, "zhoubingtest");
        // Send request:
        LoggerService loggerService = new LoggerService();
        String responseResult = loggerService.writeLogToServer(dataBody);
        // Test operate log:
        OperateLogRequestBody operateLogRequestBody = loggerService.constructDefaultOperateLogData("CurrentUser", "spc-screen", "测试", "测试");
        loggerService.writeLogToServer(operateLogRequestBody);
        return responseResult;
    }

    /**
     * @Description: Online test for writing access log to database using provided API
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @RequestMapping(value = {"/AccessLogger"}, method = {RequestMethod.POST})
    public String accessLogger(@RequestBody AccessLogRequestBody accessLogRequestBody) {
        System.out.println("Writing access log using http request...");
        // Get current time:
        long currentTime = System.currentTimeMillis();
        accessLogRequestBody.data.get(0).setExecuteTime(currentTime);
        accessLogRequestBody.data.get(0).setLogTime(currentTime);
        accessLogRequestBody.setTimestamp(currentTime);
        // Send request:
        String responseResult = loggerService.writeLogToServer(accessLogRequestBody);
        return responseResult;
    }


}
