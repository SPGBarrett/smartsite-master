package com.barrett.logger;

import java.util.List;

/**
 * @program: smartsite
 * @description: Java bean for operation log request body
 * @author: Barrett
 * @create: 2020-02-13 09:00
 **/
public class OperateLogRequestBody {

    public List<OperateLogData> data;
    public long timestamp;
    public String sign;

    public OperateLogRequestBody(List<OperateLogData> data, long timestamp, String sign) {
        this.data = data;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public OperateLogRequestBody() {
    }

    public List<OperateLogData> getData() {
        return data;
    }

    public void setData(List<OperateLogData> data) {
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

    public static class OperateLogData{
        String operUser;//操作人
        String operModule;//操作模块
        String operType;//操作类型
        String operAct;//操作方法
        String dataId;//操作数据编号
        String operData;//操作数据内容
        String operDesc;//操作描述
        long operTime;//操作时间

        public OperateLogData(String operUser, String operModule, String operType, String operAct, String dataId, String operData, String operDesc, long operTime) {
            this.operUser = operUser;
            this.operModule = operModule;
            this.operType = operType;
            this.operAct = operAct;
            this.dataId = dataId;
            this.operData = operData;
            this.operDesc = operDesc;
            this.operTime = operTime;
        }

        public OperateLogData() {
        }

        public String getOperUser() {
            return operUser;
        }

        public void setOperUser(String operUser) {
            this.operUser = operUser;
        }

        public String getOperModule() {
            return operModule;
        }

        public void setOperModule(String operModule) {
            this.operModule = operModule;
        }

        public String getOperType() {
            return operType;
        }

        public void setOperType(String operType) {
            this.operType = operType;
        }

        public String getOperAct() {
            return operAct;
        }

        public void setOperAct(String operAct) {
            this.operAct = operAct;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getOperData() {
            return operData;
        }

        public void setOperData(String operData) {
            this.operData = operData;
        }

        public String getOperDesc() {
            return operDesc;
        }

        public void setOperDesc(String operDesc) {
            this.operDesc = operDesc;
        }

        public long getOperTime() {
            return operTime;
        }

        public void setOperTime(long operTime) {
            this.operTime = operTime;
        }
    }
}
