package com.barrett.facedetectservice.vm;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-25 23:35
 **/
public class FaceDetectDeviceInfo {
    int id;//主键
    String guid;//唯一编号
    String device_ip;//设备IP
    String device_port;//设备端口
    String device_type;//设备类型 - 1=考勤 2=刷脸巡检
    String description;//设备的一些描述
    String device_no;//设备编号
    String device_name;//设备名称
    long latitude;
    long longitude;
    String login_username;
    String login_password;
}
