package com.barrett.facedetectservice.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.barrett.facedetectservice.HCNetSDK;
import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.bean.UserData;
import com.barrett.facedetectservice.http.FaceMsgSocketPush;
import com.barrett.facedetectservice.service.DeviceIPMapService;
import com.barrett.facedetectservice.service.FaceDetectDataService;
import com.barrett.facedetectservice.service.UserDataService;
import com.barrett.facedetectservice.vm.DeviceInfoInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-09 01:38
 **/
@Component
public class FaceDetectDeviceControl {
    @Autowired
    FaceDetectDataService faceDetectDataService;
    @Autowired
    UserDataService userDataService;
    @Autowired
    DeviceIPMapService deviceIPMapService;
    @Autowired
    FaceMsgSocketPush faceMsgSocketPush;

    @Value("${detect.device.ip}")
    public String deviceIP;
    @Value("${detect.device.port}")
    public String devicePort;
    @Value("${detect.device.username}")
    public String deviceUsername;
    @Value("${detect.device.password}")
    public String devicePassword;
    @Value("${getall.mis.user.url}")
    public String GET_USER_URL;
    @Value("${detect.pic.save.path}")
    public String PIC_SAVE_PATH;
    @Value("${face.detect.alert.log.path}")
    public String LOG_FILE_PATH;
    @Value("${face.detect.alarm.log.path}")
    public String LOG_FILE_PATH_ALARM;

    //public final String CHECK_DEVICE_INFO_URL= "http://223.84.191.228:8331/spc-device/zqs/checkdevice/1";
    //public final String PATROL_DEVICE_INFO_URL = "http://223.84.191.228:8331/spc-device/zqs/checkdevice/2";

    @Value("${push.worker.info}")
    public boolean PUSH_TO_SITE_SCREEN;
    @Value("${check.device.info.url}")
    public String CHECK_DEVICE_INFO_URL;
    @Value("${patrol.device.info.url}")
    public String PATROL_DEVICE_INFO_URL;
    @Value("${get.alluser.info.url}")
    public String ALL_USER_INFO_URL;
    @Value("${get.user.info.bycard.url}")
    public String USERINFO_BYCARD_URL;
    @Value("${get.worker.num.insite.url}")
    public String WORKER_NUM_URL;


    // Global variable:
    private Map userInfoMap;
    // Get HK instance:
    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    String m_sDeviceIP;//已登录设备的IP地址

    NativeLong lUserID;//用户句柄
    NativeLong lAlarmHandle;//报警布防句柄
    NativeLong lListenHandle;//报警监听句柄

    FMSGCallBack fMSFCallBack;//报警回调函数实现
    FMSGCallBack_V31 fMSFCallBack_V31;//报警回调函数实现

    FGPSDataCallback fGpsCallBack;//GPS信息查询回调函数实现
    FRemoteCfgCallBackCardSet fRemoteCfgCallBackCardSet;
    FRemoteCfgCallBackCardGet fRemoteCfgCallBackCardGet;

    FRemoteCfgCallBackFaceGet fRemoteCfgCallBackFaceGet;
    FRemoteCfgCallBackFaceSet fRemoteCfgCallBackFaceSet;
    FRemoteCfgCallBackFaceCapture fRemoteCfgCallBackFaceCapture;

    public NativeLong getlUserID() {
        return lUserID;
    }

    public NativeLong getlAlarmHandle() {
        return lAlarmHandle;
    }
// All the callback methods:

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FGPSDataCallback implements HCNetSDK.fGPSDataCallback {
        public void invoke(NativeLong nHandle, int dwState, Pointer lpBuffer, int dwBufLen, Pointer pUser) {
        }
    }

    /**
     * @Description: 设置人脸的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FRemoteCfgCallBackFaceSet implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            //System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
            switch (dwType) {
                case 0:// NET_SDK_CALLBACK_TYPE_STATUS
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCfgStatus.write();
                    Pointer pCfgStatus = struCfgStatus.getPointer();
                    pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0, struCfgStatus.size());
                    struCfgStatus.read();

                    int iStatus = 0;
                    for (int i = 0; i < 4; i++) {
                        int ioffset = i * 8;
                        int iByte = struCfgStatus.byStatus[i] & 0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }

                    switch (iStatus) {
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("下发人脸参数成功,dwStatus:" + iStatus);
                            break;
                        case 1001:
                            System.out.println("正在下发人脸参数中,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            int iErrorCode = 0;
                            for (int i = 0; i < 4; i++) {
                                int ioffset = i * 8;
                                int iByte = struCfgStatus.byErrorCode[i] & 0xff;
                                iErrorCode = iErrorCode + (iByte << ioffset);
                            }
                            System.out.println("下发人脸参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                            break;
                    }
                    break;
                case 2:// 获取状态数据
                    HCNetSDK.NET_DVR_FACE_PARAM_STATUS m_struFaceStatus = new HCNetSDK.NET_DVR_FACE_PARAM_STATUS();
                    m_struFaceStatus.write();
                    Pointer pStatusInfo = m_struFaceStatus.getPointer();
                    pStatusInfo.write(0, lpBuffer.getByteArray(0, m_struFaceStatus.size()), 0, m_struFaceStatus.size());
                    m_struFaceStatus.read();
                    String str = new String(m_struFaceStatus.byCardNo).trim();
                    System.out.println("下发人脸数据关联的卡号:" + str + ",人脸读卡器状态:" +
                            m_struFaceStatus.byCardReaderRecvStatus[0] + ",错误描述:" + new String(m_struFaceStatus.byErrorMsg).trim());
                default:
                    break;
            }
        }
    }

    /**
     * @Description: 获取到人脸的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FRemoteCfgCallBackFaceCapture implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
            switch (dwType) {
                case 0:// NET_SDK_CALLBACK_TYPE_STATUS
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCfgStatus.write();
                    Pointer pCfgStatus = struCfgStatus.getPointer();
                    pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0, struCfgStatus.size());
                    struCfgStatus.read();

                    int iStatus = 0;
                    for (int i = 0; i < 4; i++) {
                        int ioffset = i * 8;
                        int iByte = struCfgStatus.byStatus[i] & 0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }

                    switch (iStatus) {
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("采集人脸信息成功,dwStatus:" + iStatus);
                            break;
                        case 1001:
                            System.out.println("正在采集人脸信息中,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            System.out.println("采集人脸信息失败, dwStatus:" + iStatus);
                            break;
                    }
                    break;
                case 2:// 获取状态数据
                    HCNetSDK.NET_DVR_CAPTURE_FACE_CFG struFaceCfg = new HCNetSDK.NET_DVR_CAPTURE_FACE_CFG();
                    struFaceCfg.write();
                    Pointer pStatusInfo = struFaceCfg.getPointer();
                    pStatusInfo.write(0, lpBuffer.getByteArray(0, struFaceCfg.size()), 0, struFaceCfg.size());
                    struFaceCfg.read();
                    System.out.println("采集进度:" + struFaceCfg.byCaptureProgress + ",人脸图片数据大小:" + struFaceCfg.dwFacePicSize);
                    if ((struFaceCfg.byCaptureProgress == 100) && (struFaceCfg.dwFacePicSize > 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            fout = new FileOutputStream(newName + "_CaptureFacePic.jpg");
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = struFaceCfg.pFacePicBuffer.getByteBuffer(offset, struFaceCfg.dwFacePicSize);
                            byte[] bytes = new byte[struFaceCfg.dwFacePicSize];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @Description: 下发卡的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FRemoteCfgCallBackCardSet implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
            switch (dwType) {
                case 0:// NET_SDK_CALLBACK_TYPE_STATUS
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCardStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCardStatus.write();
                    Pointer pInfoV30 = struCardStatus.getPointer();
                    pInfoV30.write(0, lpBuffer.getByteArray(0, struCardStatus.size()), 0, struCardStatus.size());
                    struCardStatus.read();

                    int iStatus = 0;
                    for (int i = 0; i < 4; i++) {
                        int ioffset = i * 8;
                        int iByte = struCardStatus.byStatus[i] & 0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }
                    switch (iStatus) {
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("下发卡参数成功,dwStatus:" + iStatus);
                            break;
                        case 1001:
                            System.out.println("正在下发卡参数中,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            int iErrorCode = 0;
                            for (int i = 0; i < 4; i++) {
                                int ioffset = i * 8;
                                int iByte = struCardStatus.byErrorCode[i] & 0xff;
                                iErrorCode = iErrorCode + (iByte << ioffset);
                            }
                            System.out.println("下发卡参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @Description: 查询人脸参数的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FRemoteCfgCallBackFaceGet implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            //System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
            switch (dwType) {
                case 0:// NET_SDK_CALLBACK_TYPE_STATUS
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCfgStatus.write();
                    Pointer pCfgStatus = struCfgStatus.getPointer();
                    pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0, struCfgStatus.size());
                    struCfgStatus.read();

                    int iStatus = 0;
                    for (int i = 0; i < 4; i++) {
                        int ioffset = i * 8;
                        int iByte = struCfgStatus.byStatus[i] & 0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }

                    switch (iStatus) {
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("查询人脸参数成功,dwStatus:" + iStatus);
                            break;
                        case 1001:
                            System.out.println("正在查询人脸参数中,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            int iErrorCode = 0;
                            for (int i = 0; i < 4; i++) {
                                int ioffset = i * 8;
                                int iByte = struCfgStatus.byErrorCode[i] & 0xff;
                                iErrorCode = iErrorCode + (iByte << ioffset);
                            }
                            System.out.println("查询人脸参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                            break;
                    }
                    break;
                case 2: //NET_SDK_CALLBACK_TYPE_DATA
                    processRecordedFaces(dwType, lpBuffer, dwBufLen, pUserData);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @Description: 布防的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31 {
        public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
            AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
            return true;
        }

    }

    /**
     * @Description: 布防的回调函数-另一个版本
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FMSGCallBack implements HCNetSDK.FMSGCallBack {
        //报警信息回调函数
        public void invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
            AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
        }
    }

    /**
     * @Description: 卡参数查询的回调函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public class FRemoteCfgCallBackCardGet implements HCNetSDK.FRemoteConfigCallback {
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            HCNetSDK.MY_USER_DATA m_userData = new HCNetSDK.MY_USER_DATA();
            m_userData.write();
            Pointer pUserVData = m_userData.getPointer();
            pUserVData.write(0, pUserData.getByteArray(0, m_userData.size()), 0, m_userData.size());
            m_userData.read();

            System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
            switch (dwType) {
                case 0: //NET_SDK_CALLBACK_TYPE_STATUS, Status Mode
                    HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                    struCfgStatus.write();
                    Pointer pCfgStatus = struCfgStatus.getPointer();
                    pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0, struCfgStatus.size());
                    struCfgStatus.read();

                    int iStatus = 0;
                    for (int i = 0; i < 4; i++) {
                        int ioffset = i * 8;
                        int iByte = struCfgStatus.byStatus[i] & 0xff;
                        iStatus = iStatus + (iByte << ioffset);
                    }

                    switch (iStatus) {
                        case 1000:// NET_SDK_CALLBACK_STATUS_SUCCESS
                            System.out.println("查询卡参数成功,dwStatus:" + iStatus);
                            break;
                        case 1001:
                            System.out.println("正在查询卡参数中,dwStatus:" + iStatus);
                            break;
                        case 1002:
                            int iErrorCode = 0;
                            for (int i = 0; i < 4; i++) {
                                int ioffset = i * 8;
                                int iByte = struCfgStatus.byErrorCode[i] & 0xff;
                                iErrorCode = iErrorCode + (iByte << ioffset);
                            }
                            System.out.println("查询卡参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                            break;
                    }
                    break;
                case 2: //NET_SDK_CALLBACK_TYPE_DATA, Process Data
                    processUserData(dwType, lpBuffer, dwBufLen, pUserData);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @Description: Default 处理布防返回信息的函数
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void AlarmDataHandle(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        String sAlarmType = new String();
        String[] newRow = new String[3];
        //报警时间
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String[] sIP = new String[2];

        //  sAlarmType = new String("lCommand=") + lCommand.intValue();
        //lCommand是传的报警类型
        switch (lCommand) {
            case HCNetSDK.COMM_ALARM_V30:
                HCNetSDK.NET_DVR_ALARMINFO_V30 strAlarmInfoV30 = new HCNetSDK.NET_DVR_ALARMINFO_V30();
                strAlarmInfoV30.write();
                Pointer pInfoV30 = strAlarmInfoV30.getPointer();
                pInfoV30.write(0, pAlarmInfo.getByteArray(0, strAlarmInfoV30.size()), 0, strAlarmInfoV30.size());
                strAlarmInfoV30.read();
                switch (strAlarmInfoV30.dwAlarmType) {
                    case 0:
                        sAlarmType = sAlarmType + new String("：信号量报警") + "，" + "报警输入口：" + (strAlarmInfoV30.dwAlarmInputNumber + 1);
                        break;
                    case 1:
                        sAlarmType = sAlarmType + new String("：硬盘满");
                        break;
                    case 2:
                        sAlarmType = sAlarmType + new String("：信号丢失");
                        break;
                    case 3:
                        sAlarmType = sAlarmType + new String("：移动侦测") + "，" + "报警通道：";
                        for (int i = 0; i < 64; i++) {
                            if (strAlarmInfoV30.byChannel[i] == 1) {
                                sAlarmType = sAlarmType + "ch" + (i + 1) + " ";
                            }
                        }
                        break;
                    case 4:
                        sAlarmType = sAlarmType + new String("：硬盘未格式化");
                        break;
                    case 5:
                        sAlarmType = sAlarmType + new String("：读写硬盘出错");
                        break;
                    case 6:
                        sAlarmType = sAlarmType + new String("：遮挡报警");
                        break;
                    case 7:
                        sAlarmType = sAlarmType + new String("：制式不匹配");
                        break;
                    case 8:
                        sAlarmType = sAlarmType + new String("：非法访问");
                        break;
                }
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("布防信息：");
                for (String info : newRow) {
                    System.out.println(info);
                }
                break;
            case HCNetSDK.COMM_ALARM_RULE:
                HCNetSDK.NET_VCA_RULE_ALARM strVcaAlarm = new HCNetSDK.NET_VCA_RULE_ALARM();
                strVcaAlarm.write();
                Pointer pVcaInfo = strVcaAlarm.getPointer();
                pVcaInfo.write(0, pAlarmInfo.getByteArray(0, strVcaAlarm.size()), 0, strVcaAlarm.size());
                strVcaAlarm.read();

                switch (strVcaAlarm.struRuleInfo.wEventTypeEx) {
                    case 1:
                        sAlarmType = sAlarmType + new String("：穿越警戒面") + "，" +
                                "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                        break;
                    case 2:
                        sAlarmType = sAlarmType + new String("：目标进入区域") + "，" +
                                "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                        break;
                    case 3:
                        sAlarmType = sAlarmType + new String("：目标离开区域") + "，" +
                                "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                        break;
                    default:
                        sAlarmType = sAlarmType + new String("：其他行为分析报警") + "，" +
                                "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                "_byIvmsChannel:" + strVcaAlarm.struDevInfo.byIvmsChannel +
                                "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                        break;
                }
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("报警规则：");
                for (String info : newRow) {
                    System.out.println(info);
                }

                if (strVcaAlarm.dwPicDataLen > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        fout = new FileOutputStream(newName + "_VCA.jpg");
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strVcaAlarm.pImage.getPointer().getByteBuffer(offset, strVcaAlarm.dwPicDataLen);
                        byte[] bytes = new byte[strVcaAlarm.dwPicDataLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case HCNetSDK.COMM_UPLOAD_PLATE_RESULT:
                HCNetSDK.NET_DVR_PLATE_RESULT strPlateResult = new HCNetSDK.NET_DVR_PLATE_RESULT();
                strPlateResult.write();
                Pointer pPlateInfo = strPlateResult.getPointer();
                pPlateInfo.write(0, pAlarmInfo.getByteArray(0, strPlateResult.size()), 0, strPlateResult.size());
                strPlateResult.read();
                try {
                    String srt3 = new String(strPlateResult.struPlateInfo.sLicense, "GBK");
                    sAlarmType = sAlarmType + "：交通抓拍上传，车牌：" + srt3;
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("车牌信息：");
                for (String info : newRow) {
                    System.out.println(info);
                }

                if (strPlateResult.dwPicLen > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        fout = new FileOutputStream(newName + "_PlateResult.jpg");
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strPlateResult.pBuffer1.getByteBuffer(offset, strPlateResult.dwPicLen);
                        byte[] bytes = new byte[strPlateResult.dwPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case HCNetSDK.COMM_ITS_PLATE_RESULT:
                HCNetSDK.NET_ITS_PLATE_RESULT strItsPlateResult = new HCNetSDK.NET_ITS_PLATE_RESULT();
                strItsPlateResult.write();
                Pointer pItsPlateInfo = strItsPlateResult.getPointer();
                pItsPlateInfo.write(0, pAlarmInfo.getByteArray(0, strItsPlateResult.size()), 0, strItsPlateResult.size());
                strItsPlateResult.read();
                try {
                    String srt3 = new String(strItsPlateResult.struPlateInfo.sLicense, "GBK");
                    sAlarmType = sAlarmType + ",车辆类型：" + strItsPlateResult.byVehicleType + ",交通抓拍上传，车牌：" + srt3;
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("违章抓拍：");
                for (String info : newRow) {
                    System.out.println(info);
                }

                for (int i = 0; i < strItsPlateResult.dwPicNum; i++) {
                    if (strItsPlateResult.struPicInfo[i].dwDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = newName + "_ITSPlateResult_type" + strItsPlateResult.struPicInfo[i].byType + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strItsPlateResult.struPicInfo[i].pBuffer.getByteBuffer(offset, strItsPlateResult.struPicInfo[i].dwDataLen);
                            byte[] bytes = new byte[strItsPlateResult.struPicInfo[i].dwDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case HCNetSDK.COMM_ALARM_PDC:
                HCNetSDK.NET_DVR_PDC_ALRAM_INFO strPDCResult = new HCNetSDK.NET_DVR_PDC_ALRAM_INFO();
                strPDCResult.write();
                Pointer pPDCInfo = strPDCResult.getPointer();
                pPDCInfo.write(0, pAlarmInfo.getByteArray(0, strPDCResult.size()), 0, strPDCResult.size());
                strPDCResult.read();

                sAlarmType = sAlarmType + "：客流量统计，进入人数：" + strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum;

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(strPDCResult.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("流量统计：");
                for (String info : newRow) {
                    System.out.println(info);
                }
                break;

            case HCNetSDK.COMM_ITS_PARK_VEHICLE:
                HCNetSDK.NET_ITS_PARK_VEHICLE strItsParkVehicle = new HCNetSDK.NET_ITS_PARK_VEHICLE();
                strItsParkVehicle.write();
                Pointer pItsParkVehicle = strItsParkVehicle.getPointer();
                pItsParkVehicle.write(0, pAlarmInfo.getByteArray(0, strItsParkVehicle.size()), 0, strItsParkVehicle.size());
                strItsParkVehicle.read();
                try {
                    String srtParkingNo = new String(strItsParkVehicle.byParkingNo).trim(); //车位编号
                    String srtPlate = new String(strItsParkVehicle.struPlateInfo.sLicense, "GBK").trim(); //车牌号码
                    sAlarmType = sAlarmType + ",停产场数据,车位编号：" + srtParkingNo + ",车位状态："
                            + strItsParkVehicle.byLocationStatus + ",车牌：" + srtPlate;
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("停车情况：");
                for (String info : newRow) {
                    System.out.println(info);
                }

                for (int i = 0; i < strItsParkVehicle.dwPicNum; i++) {
                    if (strItsParkVehicle.struPicInfo[i].dwDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = newName + "_ITSPark_type" + strItsParkVehicle.struPicInfo[i].byType + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strItsParkVehicle.struPicInfo[i].pBuffer.getByteBuffer(offset, strItsParkVehicle.struPicInfo[i].dwDataLen);
                            byte[] bytes = new byte[strItsParkVehicle.struPicInfo[i].dwDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case HCNetSDK.COMM_ALARM_ACS: //门禁主机报警信息, 接收获取到的刷脸或刷卡等事件，需要封装
                processFaceDetectData(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
                break;
            case HCNetSDK.COMM_ID_INFO_ALARM: //身份证信息
                HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM();
                strIDCardInfo.write();
                Pointer pIDCardInfo = strIDCardInfo.getPointer();
                pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                strIDCardInfo.read();

                sAlarmType = sAlarmType + "：门禁身份证刷卡信息，身份证号码：" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() + "，姓名：" +
                        new String(strIDCardInfo.struIDCardCfg.byName).trim() + "，报警主类型：" + strIDCardInfo.dwMajor + "，报警次类型：" + strIDCardInfo.dwMinor;

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("身份证信息：");
                for (String info : newRow) {
                    System.out.println(info);
                }
                break;
            default:
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                System.out.println("默认信息：");
                for (String info : newRow) {
                    System.out.println(info);
                }
                break;
        }
    }

    /**
     * @Description: Data and SDK initialization
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void dataAndSDKInit() {
        lUserID = new NativeLong(-1);
        lAlarmHandle = new NativeLong(-1);
        lListenHandle = new NativeLong(-1);
        fMSFCallBack = null;
        fGpsCallBack = null;

        fRemoteCfgCallBackCardGet = null;
        fRemoteCfgCallBackCardSet = null;
        fRemoteCfgCallBackFaceGet = null;
        fRemoteCfgCallBackFaceSet = null;

        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true) {
            System.out.println("初始化失败");
        } else {
            System.out.println("初始化成功");
        }
    }

    /**
     * @Description: Only Data initialization
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void dataInit() {
        lUserID = new NativeLong(-1);
        lAlarmHandle = new NativeLong(-1);
        lListenHandle = new NativeLong(-1);
        fMSFCallBack = null;
        fGpsCallBack = null;

        fRemoteCfgCallBackCardGet = null;
        fRemoteCfgCallBackCardSet = null;
        fRemoteCfgCallBackFaceGet = null;
        fRemoteCfgCallBackFaceSet = null;
    }

    /**
     * @Description: 设置布防的触发方法
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void setupAlarmChan() {
        if (lUserID.intValue() == -1) {
            System.out.println("请先注册");
            writeLogFiles("请先注册", LOG_FILE_PATH);
            return;
        }
        if (lAlarmHandle.intValue() < 0)//尚未布防,需要布防
        {
            if (fMSFCallBack_V31 == null) {
                fMSFCallBack_V31 = new FMSGCallBack_V31();
                Pointer pUser = null;
                if (!hCNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fMSFCallBack_V31, pUser)) {
                    System.out.println("设置回调函数失败!");
                    writeLogFiles("设置回调函数失败!", LOG_FILE_PATH);
                }
            }
            HCNetSDK.NET_DVR_SETUPALARM_PARAM m_strAlarmInfo = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
            m_strAlarmInfo.dwSize = m_strAlarmInfo.size();
            m_strAlarmInfo.byLevel = 1;
            m_strAlarmInfo.byAlarmInfoType = 1;
            m_strAlarmInfo.byDeployType = 0;//布防类型：0-客户端布防，1-实时布防
            m_strAlarmInfo.write();
            lAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, m_strAlarmInfo);
            if (lAlarmHandle.intValue() == -1) {
                System.out.println("布防失败");
                writeLogFiles("布防失败", LOG_FILE_PATH_ALARM);
            } else {
                System.out.println("布防成功");
                writeLogFiles("布防成功", LOG_FILE_PATH_ALARM);
            }
        }
    }

    /**
     * @Description: 用户注册的触发方法
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void userLogin(String deviceIP, int port, String userName, String password) {
        //注册之前先注销已注册的用户,预览情况下不可注销
        if (lUserID.longValue() > -1) {
            //先注销
            hCNetSDK.NET_DVR_Logout(lUserID);
            lUserID = new NativeLong(-1);
        }
        //注册
        m_sDeviceIP = deviceIP;//设备ip地址
        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        int iPort = port;
        lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP,
                (short) iPort, userName, password, m_strDeviceInfo);
        long userID = lUserID.longValue();
        if (userID == -1) {
            System.out.println("注册失败");
        } else {
            System.out.println("注册成功");
        }
    }

    /**
     * @Description: Get all user data by card:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void getAllUserInfoByCard() {
        int iErr = 0;
        HCNetSDK.NET_DVR_CARD_CFG_COND m_struCardInputParam = new HCNetSDK.NET_DVR_CARD_CFG_COND();
        m_struCardInputParam.dwSize = m_struCardInputParam.size();
        m_struCardInputParam.dwCardNum = 0xffffffff; //查找全部
        m_struCardInputParam.byCheckCardNo = 1;

        Pointer lpInBuffer = m_struCardInputParam.getPointer();
        fRemoteCfgCallBackCardGet = new FRemoteCfgCallBackCardGet();
        m_struCardInputParam.write();

        HCNetSDK.MY_USER_DATA userData = new HCNetSDK.MY_USER_DATA();
        userData.dwSize = userData.size();
        userData.byteData = "1234567".getBytes();
        Pointer pUserData = userData.getPointer();
        userData.write();

        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_CARD_CFG_V50, lpInBuffer, m_struCardInputParam.size(), fRemoteCfgCallBackCardGet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("建立获取卡参数长连接成功!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开长连接成功!");
    }

    /**
     * @Description: Get all recorded faces from current device:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void getAllRecordedFaces(List<String> cardNoList) {
        int iErr = 0;
        // 需要配置什么参数，就设置需要的参数，不是所有参数都要的。
        HCNetSDK.NET_DVR_FACE_PARAM_COND m_struFaceInputParam = new HCNetSDK.NET_DVR_FACE_PARAM_COND();
        m_struFaceInputParam.dwSize = m_struFaceInputParam.size();
        m_struFaceInputParam.byCardNo = "ZN0612".getBytes(); //根据人脸关联的卡号获取人脸
        m_struFaceInputParam.byEnableCardReader[0] = 1;
        m_struFaceInputParam.dwFaceNum = 0xffffffff;//查找全部
        m_struFaceInputParam.byFaceID = 1;
        m_struFaceInputParam.write();

        Pointer lpInBuffer = m_struFaceInputParam.getPointer();
        Pointer pUserData = null;
        fRemoteCfgCallBackFaceGet = new FRemoteCfgCallBackFaceGet();

        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_FACE_PARAM_CFG, lpInBuffer, m_struFaceInputParam.size(), fRemoteCfgCallBackFaceGet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立获取人脸长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("建立获取人脸卡参数长连接成功!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开获取人脸长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开获取人脸长连接成功!");
    }


    /**
     * @Description: Get all recorded faces from current device:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void getRecordedFacesByCardNo(String cardNo) {
        int iErr = 0;
        // 需要配置什么参数，就设置需要的参数，不是所有参数都要的。
        HCNetSDK.NET_DVR_FACE_PARAM_COND m_struFaceInputParam = new HCNetSDK.NET_DVR_FACE_PARAM_COND();
        m_struFaceInputParam.dwSize = m_struFaceInputParam.size();
        m_struFaceInputParam.byCardNo = cardNo.getBytes(); //根据人脸关联的卡号获取人脸
        m_struFaceInputParam.byEnableCardReader[0] = 1;
        m_struFaceInputParam.dwFaceNum = 0xffffffff;//查找全部
        m_struFaceInputParam.byFaceID = 1;
        m_struFaceInputParam.write();

        Pointer lpInBuffer = m_struFaceInputParam.getPointer();
        Pointer pUserData = null;
        fRemoteCfgCallBackFaceGet = new FRemoteCfgCallBackFaceGet();

        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_FACE_PARAM_CFG, lpInBuffer, m_struFaceInputParam.size(), fRemoteCfgCallBackFaceGet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立获取人脸长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("建立获取人脸参数长连接成功!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开获取人脸长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开获取人脸长连接成功!");
    }

    // ******invoke when callback:******
    private void processUserData(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        try {
            //Receive data:
            HCNetSDK.NET_DVR_CARD_CFG_V50 m_struCardInfo = new HCNetSDK.NET_DVR_CARD_CFG_V50();
            m_struCardInfo.write();
            Pointer pInfoV30 = m_struCardInfo.getPointer();
            pInfoV30.write(0, lpBuffer.getByteArray(0, m_struCardInfo.size()), 0, m_struCardInfo.size());
            m_struCardInfo.read();
            // Get info needed:
            String strCardNo = (new String(m_struCardInfo.byCardNo)).trim();
            String strCardType = String.valueOf(m_struCardInfo.byCardType).trim();
            String scannedNum = String.valueOf(m_struCardInfo.dwSwipeTime).trim();
            String strEmNo = String.valueOf(m_struCardInfo.dwEmployeeNo).trim();
            String srtName = new String(m_struCardInfo.byName, "UTF8").trim(); //姓名
            String strIDCard = "N/A";
            if (userInfoMap == null) {
                return;
            } else {
                if (userInfoMap.get(strCardNo) != null) {
                    strIDCard = userInfoMap.get(strCardNo).toString();
                }
            }
            String showInfo = "查询到的卡号:" + strCardNo + "姓名:" + srtName + "\n" +
                    "卡类型：" + strCardType + "   " +
                    "刷卡次数：" + scannedNum + "   " +
                    "工号:" + strEmNo;
            // Write log:
            // System.out.println(showInfo);
            // Construct ds and Save data to database:
            System.out.println("更新用户数据数据库...");
            UserData thisUserData = new UserData();
            thisUserData.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
            thisUserData.setUser_name(srtName);
            thisUserData.setCard_no(strCardNo);
            thisUserData.setCard_type(Integer.parseInt(strCardType));
            thisUserData.setWork_no(strEmNo);
            thisUserData.setUpdate_date(System.currentTimeMillis());
            thisUserData.setId_card(strIDCard);
            thisUserData.setStatus(1);
            thisUserData.setCard_right(1);
            thisUserData.setAuth_type(1);
            //userDataService.insert(thisUserData);
            userDataService.insertOrUpdate(thisUserData);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            writeLogFiles("获取用户数据出错！" + e1.toString(), LOG_FILE_PATH);
        }
    }

    // ******invoke when callback:******
    private void processRecordedFaces(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        HCNetSDK.NET_DVR_FACE_PARAM_CFG m_struFaceInfo = new HCNetSDK.NET_DVR_FACE_PARAM_CFG();
        m_struFaceInfo.write();
        Pointer pInfoV30 = m_struFaceInfo.getPointer();
        pInfoV30.write(0, lpBuffer.getByteArray(0, m_struFaceInfo.size()), 0, m_struFaceInfo.size());
        m_struFaceInfo.read();
        String str = new String(m_struFaceInfo.byCardNo).trim();
        System.out.println("查询到人脸数据关联的卡号,getCardNo:" + str + ",人脸数据类型:" + m_struFaceInfo.byFaceDataType);
        if (m_struFaceInfo.dwFaceLen > 0) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newName = sf.format(new Date());
            FileOutputStream fout;
            try {
                // Create file and file stream:
                String filename = newName + "_Card[" + str + "]_ACSFaceCfg.jpg";
                filename = PIC_SAVE_PATH + filename;
                fout = new FileOutputStream(filename);
                //将字节写入文件
                long offset = 0;
                ByteBuffer buffers = m_struFaceInfo.pFaceBuffer.getByteBuffer(offset, m_struFaceInfo.dwFaceLen);
                byte[] bytes = new byte[m_struFaceInfo.dwFaceLen];
                ((Buffer) buffers).rewind();
                buffers.get(bytes);
                fout.write(bytes);
                fout.close();
                // Save base64
                // Convert byte buffer to stream:
                //base64Result = Base64.encodeBase64String(bytes);
                //base64Result = "data:image/jpg;base64," + base64Result;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: Using http request to get all userinfo from mis and generate a work_no, id_card map
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void getUserInfoFromMis() {
        userInfoMap = new HashMap();
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(GET_USER_URL, String.class);
            String responseBody = responseEntity.getBody();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            String dataString = jsonObject.getString("data");
            JSONArray dataList = JSONObject.parseArray(dataString);
            for (int i = 0; i < dataList.size(); i++) {
                String workNo = "0";
                String idCard = "0";
                if (dataList.getJSONObject(i).get("WORK_NUM") != null)
                    workNo = dataList.getJSONObject(i).get("WORK_NUM").toString().trim();
                if (dataList.getJSONObject(i).get("IDCARD") != null)
                    idCard = dataList.getJSONObject(i).get("IDCARD").toString().trim();
                userInfoMap.put(workNo, idCard);
            }
        } catch (Exception e) {
            System.out.println(e);
            userInfoMap = null;
            writeLogFiles(e.toString(), LOG_FILE_PATH);

        }
        System.out.println("User Info Requested");
    }

    /**
     * @Description: Face detection callback methods encapsulated function
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    private void processFaceDetectData(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        try {
            // Get current time:
            long dataReceivedTime = System.currentTimeMillis();
            Date dataReceivedDate = new Date(dataReceivedTime);
            // Define variables:
            String alarmType = new String();
            String[] consoleInfo = new String[3];
            Date appGetAlarmDate = new Date();
            DateFormat tmpDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String[] deviceIP = new String[2];
            // Process data:
            HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
            strACSInfo.write();
            Pointer pACSInfo = strACSInfo.getPointer();
            pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
            strACSInfo.read();
            // Get info needed:
            // Get alarm record time:
            HCNetSDK.NET_DVR_TIME alarmRecordTime = strACSInfo.struTime;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, alarmRecordTime.dwYear);
            calendar.set(Calendar.MONTH, alarmRecordTime.dwMonth - 1);
            calendar.set(Calendar.DATE, alarmRecordTime.dwDay);
            calendar.set(Calendar.HOUR_OF_DAY, alarmRecordTime.dwHour);
            calendar.set(Calendar.MINUTE, alarmRecordTime.dwMinute);
            calendar.set(Calendar.SECOND, alarmRecordTime.dwSecond);
            Date dtAlarmTime = calendar.getTime();
            long timeInMills = dtAlarmTime.getTime();
            String strCardNo = new String(strACSInfo.struAcsEventInfo.byCardNo).trim();
            String strUserInfo = new String(strACSInfo.sNetUser).trim();
            int alarmMajorType = strACSInfo.dwMajor;
            int alarmMinorType = strACSInfo.dwMinor;
            alarmType = alarmType + "***刷卡时间: " + dtAlarmTime.toString() + " 卡号：" + strCardNo + "，卡类型：" +
                    strACSInfo.struAcsEventInfo.byCardType + "，报警主类型：" + alarmMajorType + "，报警次类型：" + alarmMinorType +
                    "用户信息：" + strUserInfo + "工号：" + strACSInfo.struAcsEventInfo.dwEmployeeNo;
            String base64Result = "NA";
            deviceIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
            String deviceNO = new String(pAlarmer.sDeviceName);
            // Write log:
            consoleInfo[0] = tmpDateFormat.format(appGetAlarmDate);
            //报警类型
            consoleInfo[1] = alarmType;
            //报警设备IP地址
            consoleInfo[2] = deviceIP[0];
            if (strACSInfo.struAcsEventInfo.byCardType == 1) {
                String logMsg = "时间: " + dtAlarmTime.toString() + " 卡号：" + strCardNo + " IP:" + deviceIP[0] +
                        "，报警主类型：" + strACSInfo.dwMajor + "工号：" + strACSInfo.struAcsEventInfo.dwEmployeeNo;
                writeLogFiles(logMsg, LOG_FILE_PATH);
            }
            // Output data to console:
            System.out.println("门禁报警触发：");
            for (String info : consoleInfo) {
                System.out.println(info);
            }
            // Save img to file:
            if (strACSInfo.dwPicDataLen > 0 && alarmMajorType == 5 && alarmMinorType == 75) {
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date fileDate = new Date();
                fileDate.setTime(timeInMills);
                String newName = sf.format(fileDate);
                FileOutputStream fout;
                try {
                    // Create file and file stream:
                    String filename = newName + "_ACS_card_" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + ".jpg";
                    filename = PIC_SAVE_PATH + filename;
                    fout = new FileOutputStream(filename);
                    //将字节写入文件
                    long offset = 0;
                    ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
                    byte[] bytes = new byte[strACSInfo.dwPicDataLen];
                    ((Buffer) buffers).rewind();
                    buffers.get(bytes);
                    fout.write(bytes);
                    fout.close();
                    // Save base64
                    // Convert byte buffer to stream:
                    base64Result = Base64.encodeBase64String(bytes);
                    base64Result = "data:image/jpg;base64," + base64Result;
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    writeLogFiles(e.toString(), LOG_FILE_PATH);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    writeLogFiles(e.toString(), LOG_FILE_PATH);
                }
            }
            // Save data to DB:
            if (strACSInfo.struAcsEventInfo.byCardType == 1 && alarmMajorType == 5 && alarmMinorType == 75) {
                System.out.println("刷脸数据写入数据库...");
                FaceDetectData thisFaceDetectData = new FaceDetectData();
                thisFaceDetectData.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
                thisFaceDetectData.setTime_stamp(timeInMills);
                thisFaceDetectData.setAlert_major_type(strACSInfo.dwMajor);
                thisFaceDetectData.setAlert_minor_type(strACSInfo.dwMinor);
                thisFaceDetectData.setCard_no(strCardNo);
                thisFaceDetectData.setUser_info(strUserInfo);
                thisFaceDetectData.setWork_no(strACSInfo.struAcsEventInfo.dwEmployeeNo);
                thisFaceDetectData.setCard_alert_type(strACSInfo.struAcsEventInfo.byCardType);
                thisFaceDetectData.setDevice_no(deviceNO);
                thisFaceDetectData.setDevice_ip(deviceIP[0]);
                thisFaceDetectData.setDevice_port(String.valueOf(pAlarmer.wLinkPort));
                thisFaceDetectData.setPic_data(base64Result);
                thisFaceDetectData.setCreate_timestamp(dataReceivedTime);
                thisFaceDetectData.setCreate_date(dataReceivedDate);
                faceDetectDataService.insert(thisFaceDetectData);
            }
            // Push checkin info to LED screen:
            if (PUSH_TO_SITE_SCREEN == true) {
                if (strACSInfo.struAcsEventInfo.byCardType == 1 && alarmMajorType == 5 && alarmMinorType == 75) {
                    String workerName = "";
                    String workerPosition = "";
                    String workerInstitute = "";
                    String workerDepartment = "";
                    List<UserData> userDataList = userDataService.getByCardNo(strCardNo);
                    try {
                        // Get all required user info:
                        String jsonUserInfo = getUserInfoByCardNoFromUserModule(strCardNo);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(jsonUserInfo);
                        workerName = jsonNode.get("data").get("name").toString();
                        workerPosition = jsonNode.get("data").get("position").toString();
                        workerInstitute = jsonNode.get("data").get("company").get("name").toString();
                        workerDepartment = jsonNode.get("data").get("office").get("name").toString();
                    } catch (Exception e) {
                        workerName = "未知员工";
                        System.out.println(e);
                    }

                    if (userDataList == null || userDataList.size() == 0) {
                        //workerName = "未知员工";
                    } else {
                        //workerName = userDataList.get(0).getUser_name();
                    }
                    String deviceType = "";
                    List<String> deviceNoList = deviceIPMapService.getDeviceNoByIP(deviceIP[0]);
                    if (userDataList == null || userDataList.size() == 0) {
                        deviceType = "1";
                    } else {
                        deviceType = deviceNoList.get(0);
                    }
                    String workerInSite = String.valueOf(calculateNumOfWorkerInSite());
                    faceMsgSocketPush.pushWorkerMsgAsync(base64Result, strCardNo, workerName, workerPosition, workerDepartment, workerInstitute, deviceType, workerInSite);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            writeLogFiles(e.getMessage(), LOG_FILE_PATH);
        }
    }

    /**
     * @Description: Get all available ips from database
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public List<DeviceIPMap> getAllDeviceInfo() {
        List<DeviceIPMap> resultList = new ArrayList<>();
        // Query for data:
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponseCheck = (String) restTemplate.getForObject(CHECK_DEVICE_INFO_URL, String.class, new Object[0]);
        String jsonResponsePatrol = (String) restTemplate.getForObject(PATROL_DEVICE_INFO_URL, String.class, new Object[0]);
        // Parse data:
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        try {
            List<DeviceInfoInput> checkDeviceInfoInputList = objectMapper.readValue(jsonResponseCheck, typeFactory.constructCollectionType(List.class, DeviceInfoInput.class));
            List<DeviceInfoInput> patrolDeviceInfoInputList = objectMapper.readValue(jsonResponsePatrol, typeFactory.constructCollectionType(List.class, DeviceInfoInput.class));
            for (DeviceInfoInput dii : checkDeviceInfoInputList) {
                DeviceIPMap tmpDeviceIPMap = new DeviceIPMap();
                tmpDeviceIPMap.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
                tmpDeviceIPMap.setDevice_ip(dii.getIp());
                tmpDeviceIPMap.setDevice_port(dii.getPort());
                tmpDeviceIPMap.setDevice_type("1");
                tmpDeviceIPMap.setDescription("N/A");
                tmpDeviceIPMap.setDevice_no(dii.getSbbm());
                tmpDeviceIPMap.setDevice_name(dii.getSbmc());
                tmpDeviceIPMap.setDevice_location(dii.getSsgd());
                tmpDeviceIPMap.setUsername(dii.getUser());
                tmpDeviceIPMap.setPassword(dii.getPasserword());
                double x = 0;
                double y = 0;
                if (dii.getX() != null) x = Double.parseDouble(dii.getX());
                if (dii.getX() != null) y = Double.parseDouble(dii.getY());
                tmpDeviceIPMap.setLatitude(x);
                tmpDeviceIPMap.setLongitude(y);
                resultList.add(tmpDeviceIPMap);
            }
            for (DeviceInfoInput dii : patrolDeviceInfoInputList) {
                DeviceIPMap tmpDeviceIPMap = new DeviceIPMap();
                tmpDeviceIPMap.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
                tmpDeviceIPMap.setDevice_ip(dii.getIp());
                tmpDeviceIPMap.setDevice_port(dii.getPort());
                tmpDeviceIPMap.setDevice_type("2");
                tmpDeviceIPMap.setDescription("N/A");
                tmpDeviceIPMap.setDevice_no(dii.getSbbm());
                tmpDeviceIPMap.setDevice_name(dii.getSbmc());
                tmpDeviceIPMap.setDevice_location(dii.getSsgd());
                tmpDeviceIPMap.setUsername(dii.getUser());
                tmpDeviceIPMap.setPassword(dii.getPasserword());
                double x = 0;
                double y = 0;
                if (dii.getX() != null) x = Double.parseDouble(dii.getX());
                if (dii.getX() != null) y = Double.parseDouble(dii.getY());
                tmpDeviceIPMap.setLatitude(x);
                tmpDeviceIPMap.setLongitude(y);
                resultList.add(tmpDeviceIPMap);
            }
        } catch (JsonProcessingException e) {
            System.out.println("解析Json数组出错！无法更新设备信息！");
            e.printStackTrace();
            return null;
        }
        // Save data to database:
        deviceIPMapService.deleteAll();
        for (DeviceIPMap thisIPMap : resultList) {
            deviceIPMapService.insert(thisIPMap);
        }
        // Return result:
        return resultList;
    }

    /**
     * @Description: Get all device information from local database
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public List<DeviceIPMap> getAllDeviceIPFromLocal() {
        return deviceIPMapService.getAll();
    }


    /**
     * @Description: TImed task for updating information and restarting services
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public int deviceInfoUpdateTimedTask() {
        // Start a new thread or using quatz:
        return 0;
    }


    /**
     * @Description: Calculate the number of workers on site
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public int calculateNumOfWorkerInSite() {
        int resultNum = 0;
        //resultNum = faceDetectDataService.getWorkerInSiteData("1").size();
        resultNum = getWorkerNumInSiteFromUserModule();


//        // Get all person available most recent status:
//        List<FaceDetectData> dataList = faceDetectDataService.getMostRecentStatus();
//        for(FaceDetectData thisData : dataList){
//            List<String> deviceNoList = deviceIPMapService.getDeviceNoByIP(thisData.getDevice_ip());
//            if(deviceNoList == null || deviceNoList.size() == 0){
//                continue;
//            }
//            String deviceType = deviceNoList.get(0);
//            if(deviceType == null || deviceType == "") continue;
//            if(deviceType.equals("1"))
//            {
//                resultNum++;
//            }
//        }
        return resultNum;
    }

    /**
     * @Description: Write log msg to file
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public int writeLogFiles(String logMsg, String filePath) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = null;
            if (!file.exists()) {
                file.createNewFile();
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(logMsg + "\n");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // Http Request: Get all user info:
    private String getAllUserInfoFromUserModule() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.getForEntity(ALL_USER_INFO_URL, String.class);
        result = responseEntity.getBody().toString();
        return result;
    }


    // Http Request: Get user info by CardNo:
    private String getUserInfoByCardNoFromUserModule(String cardNo) {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        String queryUrl = USERINFO_BYCARD_URL + cardNo;
        ResponseEntity responseEntity = restTemplate.getForEntity(queryUrl, String.class);
        result = responseEntity.getBody().toString();
        return result;
    }

    private int getWorkerNumInSiteFromUserModule() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String queryUrl = WORKER_NUM_URL;
            ResponseEntity responseEntity = restTemplate.getForEntity(queryUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody().toString());
            // Get all required user info:
            return Integer.valueOf(jsonNode.get("data").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
