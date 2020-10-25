package com.barrett.facedetectservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Java bean for table device_ip_map
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/

public class DeviceIPMap {
    int id;//主键
    String guid;//唯一编号
    String device_ip;//设备IP
    String device_port;//设备端口
    String device_type;//设备类型 - 1=考勤 2=刷脸巡检
    String description;//设备的一些描述 -- 当前用于储存考勤设备的编号
    String device_no;//设备编号 1=进 2=出
    String device_name;//设备名称
    String device_location;//设备所属区域
    String username;//设备登录用户名
    String password;//设备登录密码
    double latitude;//设备经度
    double longitude;//设备纬度

    public DeviceIPMap() {
    }

    public DeviceIPMap(int id, String guid, String device_ip, String device_port, String device_type, String description, String device_no, String device_name, String device_location, String username, String password, double latitude, double longitude) {
        this.id = id;
        this.guid = guid;
        this.device_ip = device_ip;
        this.device_port = device_port;
        this.device_type = device_type;
        this.description = description;
        this.device_no = device_no;
        this.device_name = device_name;
        this.device_location = device_location;
        this.username = username;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public String getDevice_port() {
        return device_port;
    }

    public void setDevice_port(String device_port) {
        this.device_port = device_port;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevice_no() {
        return device_no;
    }

    public void setDevice_no(String device_no) {
        this.device_no = device_no;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
