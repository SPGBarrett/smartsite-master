package com.barrett.aialertservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Pushing msg of alert in json format
 * @author: Barrett
 * @create: 2020-05-06 15:40
 **/
public class AIAlertPushMsg {
    String guid;
    String alert_type;
    String alert_msg;
    String alert_img_name;
    String alert_img_base64;
    long time_stamp;
    String ip;

    public AIAlertPushMsg() {
    }

    public AIAlertPushMsg(String guid, String alert_type, String alert_msg, String alert_img_name, String alert_img_base64, long time_stamp, String ip) {
        this.guid = guid;
        this.alert_type = alert_type;
        this.alert_msg = alert_msg;
        this.alert_img_name = alert_img_name;
        this.alert_img_base64 = alert_img_base64;
        this.time_stamp = time_stamp;
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type;
    }

    public String getAlert_msg() {
        return alert_msg;
    }

    public void setAlert_msg(String alert_msg) {
        this.alert_msg = alert_msg;
    }

    public String getAlert_img_name() {
        return alert_img_name;
    }

    public void setAlert_img_name(String alert_img_name) {
        this.alert_img_name = alert_img_name;
    }

    public String getAlert_img_base64() {
        return alert_img_base64;
    }

    public void setAlert_img_base64(String alert_img_base64) {
        this.alert_img_base64 = alert_img_base64;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }
}
