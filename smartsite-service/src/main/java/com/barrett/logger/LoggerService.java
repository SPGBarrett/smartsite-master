package com.barrett.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite
 * @description: Encapsulated functions that renders logging service using http requests
 * @author: Barrett
 * @create: 2020-02-13 08:59
 **/
@Service
public class LoggerService {
    @Value("${log.logger.link}")
    private String loggerUrl;

    private final String ACCESS_LOGGER_URL = "/spc-hc/report/log/access";
    private final String EXCEPTION_LOGGER_URL = "/spc-hc/report/log/exception";
    private final String OPERATE_LOGGER_URL = "/spc-hc/report/log/operate";


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public String writeAccessLogToServer(AccessLogRequestBody accessLogRequestBody) {
        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ACCESS_LOGGER_URL, accessLogRequestBody, String.class);
        responseBody = responseEntity.getBody();
        return responseBody;
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public String writeOperateLogToServer(OperateLogRequestBody operateLogRequestBody) {
        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(OPERATE_LOGGER_URL, operateLogRequestBody, String.class);
        responseBody = responseEntity.getBody();
        return responseBody;
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public String writeExceptionLogToServer(ExceptionLogRequestBody exceptionLogRequestBody) {
        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(EXCEPTION_LOGGER_URL, exceptionLogRequestBody, String.class);
        responseBody = responseEntity.getBody();
        return responseBody;
    }

    /**
     * @Description: Generic function that send logger http request
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public <T> String writeLogToServer(T logRequestBody) {
        String responseBody = "";
        //Check type to define url:
        String url = "";
        if (logRequestBody instanceof OperateLogRequestBody) {
            url = loggerUrl + OPERATE_LOGGER_URL;
        } else if (logRequestBody instanceof AccessLogRequestBody) {
            url = loggerUrl + ACCESS_LOGGER_URL;
        } else if (logRequestBody instanceof ExceptionLogRequestBody) {
            url = loggerUrl + EXCEPTION_LOGGER_URL;
        } else {

        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, logRequestBody, String.class);
        responseBody = responseEntity.getBody();
        return responseBody;
    }


    /**
     * @Description: Get user information from server
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public static String getUserInfoFromServer() {
        String result = "";

        return result;
    }

    /**
     * @Description: Construct default params for operate log
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public OperateLogRequestBody constructDefaultOperateLogData(String user, String module, String operType, String operDesc) {
        OperateLogRequestBody.OperateLogData logData = new OperateLogRequestBody.OperateLogData();
        // Get current time:
        long currentTime = System.currentTimeMillis();
        // Get log data:
        logData.operUser = user;
        logData.operModule = module;
        logData.operType = operType;
        logData.operAct = "other";
        logData.dataId = "N/A";
        logData.operData = "后端数据库数据";
        logData.operDesc = operDesc;
        logData.operTime = currentTime;
        List<OperateLogRequestBody.OperateLogData> dataList = new ArrayList<>();
        dataList.add(logData);
        //Get return result:
        OperateLogRequestBody result = new OperateLogRequestBody(dataList, currentTime, "xxx");
        return result;
    }

    /**
     * @Description: Construct default params for exception log
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public ExceptionLogRequestBody constructDefaultExceptionLogData(String user, String module, String requestUrl, String logMsg) {
        ExceptionLogRequestBody.ExceptionLogData logData = new ExceptionLogRequestBody.ExceptionLogData();
        // Get current time:
        long currentTime = System.currentTimeMillis();
        // Get log data:
        logData.logUser = user;
        logData.logTitle = "Exception";
        logData.logModule = module;
        logData.requestUrl = requestUrl;
        logData.requestParams = "N/A";
        logData.logTime = currentTime;
        logData.logMessage = logMsg;
        logData.statckInfo = "N/A";
        logData.dataId = "N/A";
        logData.logDesc = "后端服务调用";
        List<ExceptionLogRequestBody.ExceptionLogData> dataList = new ArrayList<>();
        dataList.add(logData);
        //Get return result:
        ExceptionLogRequestBody result = new ExceptionLogRequestBody(dataList, currentTime, "xxx");
        return result;
    }

}
