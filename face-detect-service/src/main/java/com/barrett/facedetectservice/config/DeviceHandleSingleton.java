package com.barrett.facedetectservice.config;

import com.sun.jna.NativeLong;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite-master
 * @description: A singleton for storing handles and userIDs for logged in devices
 * @author: Barrett
 * @create: 2020-06-18 22:07
 **/
public class DeviceHandleSingleton {
    // Init from the start:
    private static DeviceHandleSingleton instance = new DeviceHandleSingleton();

    // Public variables:
    List<NativeLong> lAlarmHandleList;
    List<NativeLong> lUserIDList;

    // Private constructor:
    private DeviceHandleSingleton() {
        lAlarmHandleList = new ArrayList<>();
        lUserIDList = new ArrayList<>();
    }

    public static DeviceHandleSingleton getInstance() {
        return instance;
    }

    public List<NativeLong> getlAlarmHandleList() {
        return lAlarmHandleList;
    }

    public void setlAlarmHandleList(List<NativeLong> lAlarmHandleList) {
        this.lAlarmHandleList = lAlarmHandleList;
    }

    public List<NativeLong> getlUserIDList() {
        return lUserIDList;
    }

    public void setlUserIDList(List<NativeLong> lUserIDList) {
        this.lUserIDList = lUserIDList;
    }
}
