package com.barrett.facedetectservice;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.config.DeviceHandleSingleton;
import com.barrett.facedetectservice.util.FaceDetectDeviceControl;
import com.barrett.facedetectservice.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class FaceDetectServiceApplication {

    public static void main(String[] args) {
        // Application entrance:
        SpringApplication.run(FaceDetectServiceApplication.class, args);
        // Test codes:
        System.out.print("Deploy HKVision face detection functions......");
        // Set up HK face detection device:
        // Get component class injected to Spring container:
        ApplicationContext context = SpringUtil.getApplicationContext();
        FaceDetectDeviceControl faceDetectDeviceControl = context.getBean(FaceDetectDeviceControl.class);// 注意是Service，不是ServiceImpl
        // Init data and SDK:
        faceDetectDeviceControl.dataAndSDKInit();
        // Get preset login data: (not used)
        String userName = faceDetectDeviceControl.deviceUsername;
        String password = faceDetectDeviceControl.devicePassword;
        // Refresh device info by requesting from other module api:
        //List<DeviceIPMap> ipList = faceDetectDeviceControl.getAllDeviceInfo();
        //faceDetectDeviceControl.getAllDeviceInfo();
        // Example for niushou substation, get all device info :
//        List<DeviceIPMap> ipList = new ArrayList<>();
//        ipList.add(new DeviceIPMap(1,"1","192.168.1.21","8000","1","aaa","aaa","aaa","aaa","admin","nsbdz2020",10,10));
//        ipList.add(new DeviceIPMap(1,"1","192.168.1.22","8000","1","aaa","aaa","aaa","aaa","admin","nsbdz2020",10,10));

        // Get all device info from local database:
        List<DeviceIPMap> ipList = faceDetectDeviceControl.getAllDeviceIPFromLocal();
        // Get all card related user info from MIS:
        faceDetectDeviceControl.getUserInfoFromMis();
        boolean queryUserInfo = true;
        // Traverse all devices and login and setup alarm:
        for(DeviceIPMap thisIP : ipList){
            if(thisIP.getUsername() != null){
                userName = thisIP.getUsername();
            }else{
                continue;
            }
            if(thisIP.getPassword() != null){
                password = thisIP.getPassword();
            }else{
                continue;
            }
            // Login to device and save login device handle:
            System.out.println(thisIP.getDevice_ip() + ":" + thisIP.getDevice_port());
            System.out.println(userName + "---" + password);
            faceDetectDeviceControl.userLogin(thisIP.getDevice_ip(), Integer.parseInt(thisIP.getDevice_port()), userName,password);
            DeviceHandleSingleton.getInstance().getlUserIDList().add(faceDetectDeviceControl.getlUserID());
            // Set up alarm callback methods and save alarm handle:
            faceDetectDeviceControl.writeLogFiles("布防："+ thisIP.getDevice_ip(), faceDetectDeviceControl.LOG_FILE_PATH);
            faceDetectDeviceControl.setupAlarmChan();
            DeviceHandleSingleton.getInstance().getlAlarmHandleList().add(faceDetectDeviceControl.getlUserID());
            // Query and update all user info:
            if(queryUserInfo){ // Query only once
                faceDetectDeviceControl.getAllUserInfoByCard();
                //queryUserInfo = false;
            }
            // Query and update all recorded face info:
            if(queryUserInfo){ // Query only once
                //faceDetectDeviceControl.getAllRecordedFaces();
            }
            // Reinit login users and handles:
            faceDetectDeviceControl.dataInit();
        }
        //
        System.out.print("Deploy Finished!");
    }
}
