package com.barrett.logger;

import java.util.List;

/**
 * @program: smartsite
 * @description: Java bean for exception log request body
 * @author: Barrett
 * @create: 2020-02-13 09:01
 **/
public class ExceptionLogRequestBody {

    public List<ExceptionLogData> data;
    public long timestamp;
    public String sign;

    public ExceptionLogRequestBody(List<ExceptionLogData> data, long timestamp, String sign) {
        this.data = data;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public ExceptionLogRequestBody() {
    }

    public List<ExceptionLogData> getData() {
        return data;
    }

    public void setData(List<ExceptionLogData> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class ExceptionLogData{
        String logUser;//日志关系人
        String logTitle;//日志标题
        String logModule;//日志模块
        String requestUrl;//访问地址
        String requestParams;//提交的数据
        long logTime;//日志时间
        String logMessage;//异常信息
        String statckInfo;//异常堆栈信息
        String dataId;//业务主键
        String logDesc;//操作描述

        public ExceptionLogData(String logUser, String logTitle, String logModule, String requestUrl, String requestParams, long logTime, String logMessage, String statckInfo, String dataId, String logDesc) {
            this.logUser = logUser;
            this.logTitle = logTitle;
            this.logModule = logModule;
            this.requestUrl = requestUrl;
            this.requestParams = requestParams;
            this.logTime = logTime;
            this.logMessage = logMessage;
            this.statckInfo = statckInfo;
            this.dataId = dataId;
            this.logDesc = logDesc;
        }

        public ExceptionLogData() {
        }

        public String getLogUser() {
            return logUser;
        }

        public void setLogUser(String logUser) {
            this.logUser = logUser;
        }

        public String getLogTitle() {
            return logTitle;
        }

        public void setLogTitle(String logTitle) {
            this.logTitle = logTitle;
        }

        public String getLogModule() {
            return logModule;
        }

        public void setLogModule(String logModule) {
            this.logModule = logModule;
        }

        public String getRequestUrl() {
            return requestUrl;
        }

        public void setRequestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
        }

        public String getRequestParams() {
            return requestParams;
        }

        public void setRequestParams(String requestParams) {
            this.requestParams = requestParams;
        }

        public long getLogTime() {
            return logTime;
        }

        public void setLogTime(long logTime) {
            this.logTime = logTime;
        }

        public String getLogMessage() {
            return logMessage;
        }

        public void setLogMessage(String logMessage) {
            this.logMessage = logMessage;
        }

        public String getStatckInfo() {
            return statckInfo;
        }

        public void setStatckInfo(String statckInfo) {
            this.statckInfo = statckInfo;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getLogDesc() {
            return logDesc;
        }

        public void setLogDesc(String logDesc) {
            this.logDesc = logDesc;
        }
    }
}
