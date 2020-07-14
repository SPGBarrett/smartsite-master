package com.barrett.logger;

import java.util.List;

/**
 * @program: smartsite
 * @description: Java bean for access log request body
 * @author: Barrett
 * @create: 2020-02-13 09:01
 **/
public class AccessLogRequestBody {


    public List<AccessLogData> data;
    public long timestamp;
    public String sign;

    public AccessLogRequestBody(List<AccessLogData> data, long timestamp, String sign) {
        this.data = data;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public AccessLogRequestBody() {
    }

    public List<AccessLogData> getData() {
        return data;
    }

    public void setData(List<AccessLogData> data) {
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

    public static class AccessLogData{
        String logUser;//日志操作人
        String logModule;//日志模块
        String logTitle;//日志标题
        String requestUrl;//访问地址
        String requestParams;//提交的数据
        long logTime;//日志时间
        String logDesc;//操作描述
        long executeTime;//响应时间
        String dataId;//业务数据主键
        String remoteAddr;//客户端IP
        String deviceName;//设备名称
        String browserName;//浏览器名称
        String userAgent;//用户代理

        public AccessLogData(String logUser, String logModule, String logTitle, String requestUrl, String requestParams, long logTime, String logDesc, long executeTime, String dataId, String remoteAddr, String deviceName, String browserName, String userAgent) {
            this.logUser = logUser;
            this.logModule = logModule;
            this.logTitle = logTitle;
            this.requestUrl = requestUrl;
            this.requestParams = requestParams;
            this.logTime = logTime;
            this.logDesc = logDesc;
            this.executeTime = executeTime;
            this.dataId = dataId;
            this.remoteAddr = remoteAddr;
            this.deviceName = deviceName;
            this.browserName = browserName;
            this.userAgent = userAgent;
        }

        public AccessLogData() {
        }

        public String getLogUser() {
            return logUser;
        }

        public void setLogUser(String logUser) {
            this.logUser = logUser;
        }

        public String getLogModule() {
            return logModule;
        }

        public void setLogModule(String logModule) {
            this.logModule = logModule;
        }

        public String getLogTitle() {
            return logTitle;
        }

        public void setLogTitle(String logTitle) {
            this.logTitle = logTitle;
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

        public String getLogDesc() {
            return logDesc;
        }

        public void setLogDesc(String logDesc) {
            this.logDesc = logDesc;
        }

        public long getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(long executeTime) {
            this.executeTime = executeTime;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getRemoteAddr() {
            return remoteAddr;
        }

        public void setRemoteAddr(String remoteAddr) {
            this.remoteAddr = remoteAddr;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getBrowserName() {
            return browserName;
        }

        public void setBrowserName(String browserName) {
            this.browserName = browserName;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }
    }

}
