package com.barrett.facedetectservice;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.config.DeviceHandleSingleton;
import com.barrett.facedetectservice.util.FaceDetectDeviceControl;
import com.barrett.facedetectservice.util.SiChuanEntranceControl;
import com.barrett.facedetectservice.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class FaceDetectServiceApplication {
    static String APP_MODE;
    @Value("${app.mode.selection}")
    public void setAppMode(String appMode) {
        FaceDetectServiceApplication.APP_MODE = appMode;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // Application entrance:
        SpringApplication.run(FaceDetectServiceApplication.class, args);
        // Inject services:
        ApplicationContext context = SpringUtil.getApplicationContext();
        FaceDetectDeviceControl faceDetectDeviceControl = context.getBean(FaceDetectDeviceControl.class);
        SiChuanEntranceControl siChuanEntranceControl = context.getBean(SiChuanEntranceControl.class);

        // Define globla variables:
        String userName = "";
        String password = "";
        List<DeviceIPMap> ipList = null;
        boolean queryUserInfo = true;

        // Start application according to different modes:
        System.out.print("Deploy FaceDetectDevice, Mode: " + APP_MODE + "......");
        switch (APP_MODE){
            case "FengCheng":
                // Set up HK face detection device:
                // Init data and SDK:
                faceDetectDeviceControl.dataAndSDKInit();
                // Get preset login data: (not used)
                userName = faceDetectDeviceControl.deviceUsername;
                password = faceDetectDeviceControl.devicePassword;
                // Refresh device info by requesting from other module api:
                //List<DeviceIPMap> ipList = faceDetectDeviceControl.getAllDeviceInfo();
                //faceDetectDeviceControl.getAllDeviceInfo();
                // Get all device info from local database:
                ipList = faceDetectDeviceControl.getAllDeviceIPFromLocal();
                // Get all card related user info from MIS:
                faceDetectDeviceControl.getUserInfoFromMis();
                queryUserInfo = true;
                // Traverse all devices and login and setup alarm:
                for (DeviceIPMap thisIP : ipList) {
                    if (thisIP.getUsername() != null) {
                        userName = thisIP.getUsername();
                    } else {
                        continue;
                    }
                    if (thisIP.getPassword() != null) {
                        password = thisIP.getPassword();
                    } else {
                        continue;
                    }
                    // Login to device and save login device handle:
                    System.out.println(thisIP.getDevice_ip() + ":" + thisIP.getDevice_port());
                    System.out.println(userName + "---" + password);
                    faceDetectDeviceControl.userLogin(thisIP.getDevice_ip(), Integer.parseInt(thisIP.getDevice_port()), userName, password);
                    DeviceHandleSingleton.getInstance().getlUserIDList().add(faceDetectDeviceControl.getlUserID());
                    // Set up alarm callback methods and save alarm handle:
                    faceDetectDeviceControl.writeLogFiles("布防：" + thisIP.getDevice_ip(), faceDetectDeviceControl.LOG_FILE_PATH);
                    faceDetectDeviceControl.setupAlarmChan();
                    DeviceHandleSingleton.getInstance().getlAlarmHandleList().add(faceDetectDeviceControl.getlUserID());
                    // Query and update all user info:
                    if (queryUserInfo) { // Query only once
                        faceDetectDeviceControl.getAllUserInfoByCard();
                        //queryUserInfo = false;
                    }
                    // Query and update all recorded face info:
                    if (queryUserInfo) { // Query only once
                        //faceDetectDeviceControl.getAllRecordedFaces();
                    }
                    // Reinit login users and handles:
                    faceDetectDeviceControl.dataInit();
                }
                break;
            case "Niushou":
                // Set up HK face detection device:
                // Init data and SDK:
                faceDetectDeviceControl.dataAndSDKInit();
                // Get preset login data: (not used)
                userName = faceDetectDeviceControl.deviceUsername;
                password = faceDetectDeviceControl.devicePassword;
                // Refresh device info by requesting from other module api:
                //List<DeviceIPMap> ipList = faceDetectDeviceControl.getAllDeviceInfo();
                //faceDetectDeviceControl.getAllDeviceInfo();
                // Example for niushou substation, get all device info :
                ipList = new ArrayList<>();
                ipList.add(new DeviceIPMap(1,"1","192.168.1.21","8000","1","aaa","aaa","aaa","aaa","admin","nsbdz2020",10,10));
                ipList.add(new DeviceIPMap(1,"1","192.168.1.22","8000","1","aaa","aaa","aaa","aaa","admin","nsbdz2020",10,10));
                // Get all device info from local database:
                //ipList = faceDetectDeviceControl.getAllDeviceIPFromLocal();
                // Get all card related user info from MIS:
                faceDetectDeviceControl.getUserInfoFromMis();
                queryUserInfo = true;
                // Traverse all devices and login and setup alarm:
                for (DeviceIPMap thisIP : ipList) {
                    if (thisIP.getUsername() != null) {
                        userName = thisIP.getUsername();
                    } else {
                        continue;
                    }
                    if (thisIP.getPassword() != null) {
                        password = thisIP.getPassword();
                    } else {
                        continue;
                    }
                    // Login to device and save login device handle:
                    System.out.println(thisIP.getDevice_ip() + ":" + thisIP.getDevice_port());
                    System.out.println(userName + "---" + password);
                    faceDetectDeviceControl.userLogin(thisIP.getDevice_ip(), Integer.parseInt(thisIP.getDevice_port()), userName, password);
                    DeviceHandleSingleton.getInstance().getlUserIDList().add(faceDetectDeviceControl.getlUserID());
                    // Set up alarm callback methods and save alarm handle:
                    faceDetectDeviceControl.writeLogFiles("布防：" + thisIP.getDevice_ip(), faceDetectDeviceControl.LOG_FILE_PATH);
                    faceDetectDeviceControl.setupAlarmChan();
                    DeviceHandleSingleton.getInstance().getlAlarmHandleList().add(faceDetectDeviceControl.getlUserID());
                    // Query and update all user info:
                    if (queryUserInfo) { // Query only once
                        faceDetectDeviceControl.getAllUserInfoByCard();
                        //queryUserInfo = false;
                    }
                    // Query and update all recorded face info:
                    if (queryUserInfo) { // Query only once
                        //faceDetectDeviceControl.getAllRecordedFaces();
                    }
                    // Reinit login users and handles:
                    faceDetectDeviceControl.dataInit();
                }
                break;
            case "SiChuan":
                siChuanEntranceControl.InitEx();
                // Get all device info from database:
                ipList = faceDetectDeviceControl.getAllDeviceIPFromLocal();
                // Connect to all device:
                for(DeviceIPMap thisDevice : ipList){
                    siChuanEntranceControl.connectCamera(thisDevice.getDevice_ip(), Short.valueOf(thisDevice.getDevice_port()));
                }
                // Test codes:
                if(false){
                    String ip = "118.122.246.56";
                    short[] ports = new short[]{50,51,52,53,54,55,56,57,58,59,60,61,62};
                    ports = new short[]{62};
                    // Connect to all cameras:
                    for(int i = 0; i<ports.length; i++){
                        siChuanEntranceControl.connectCamera(ip, ports[i]);
                    }
                    // Get all history data from all cameras:
                    String startTime = "2020-10-16 18:00:32";
                    siChuanEntranceControl.getHistoryDataFromAllDevices(startTime, null);
                }
                break;
            case "apiOnly":
                System.out.println("No device connection deployed, running only restful APIs...");
                break;
            default:
                break;
        }
        System.out.print("\n Deploy Finished!");
    }
}
