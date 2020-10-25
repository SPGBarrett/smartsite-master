package com.barrett.facedetectservice.util;

import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.error.LoggerService;
import com.barrett.facedetectservice.libFaceRecognition;
import com.barrett.facedetectservice.service.FaceDetectDataService;
import com.sun.javafx.collections.MappingChange;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.FloatByReference;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.text.*;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.IOException;
import java.io.*;//导入所需的包

@Component
public class SiChuanEntranceControl {
    // Public config variables for static class:
    //private static String DETECT_IMG_FILE_PATH = "E:\\FaceDetectDevice\\detectImg\\";
    private static String DETECT_IMG_FILE_PATH;
    @Value("${facedetect.device.img.path}")
    public void setDetectImgFilePath(String detectImgFilePath) {
        DETECT_IMG_FILE_PATH = detectImgFilePath;
    }

    private static String INFO_LOG_FILE_PATH;
    @Value("${facedetect.device.log.path}")
    public void setInfoLogFilePath(String infoLogFilePath) {
        INFO_LOG_FILE_PATH = infoLogFilePath;
    }

    private static FaceDetectDataService faceDetectDataService;
    @Autowired
    public void setFaceDetectDataService(FaceDetectDataService component){
        SiChuanEntranceControl.faceDetectDataService = component;
    }

    private static LoggerService loggerService;
    @Autowired
    public void setLoggerService(LoggerService component){
        SiChuanEntranceControl.loggerService = component;
    }

    public static libFaceRecognition m_FaceRecognition = libFaceRecognition.INSTANCE;
    // List of cameraPoints to store all the camera handle:
    public static List<IntByReference> cameraPointList = new ArrayList<>();
    public static HashMap<IntByReference, String> cameraAndIPMap = new HashMap<>();
    public static IntByReference cameraPoint = new IntByReference(0);
    public static boolean BOEStream;
    public static java.util.Map<String, String> mapcamreip = new HashMap<String, String>();
    byte[] m_rs485_protocal_no = new byte[1];

    private static ZBX_FaceRecoCb_t_Realize m_RecoCb_t = null;// 人脸识别回调函数
    private static ZBX_FaceQueryCb_t_Realize m_FaceQueryCb_t = null;
    private static discover_ipscan_cb_t_Realize m_discover_ipscan = null;
    private static ZBX_ConnectEventCb_t_Realize m_ConnectEventCb = null;
    private static Httpresult_Realize m_HTTPRESULT_PROCESS = null;

    SiChuanEntranceControl() {
        BOEStream = false;
    }

    public static void Init() {
        // Clear camera list:
        cameraPointList = new ArrayList<>();
        cameraAndIPMap = new HashMap<>();
        m_FaceRecognition.ZBX_Init();
        m_FaceRecognition.ZBX_SetNotifyConnected(1);
        m_ConnectEventCb = new ZBX_ConnectEventCb_t_Realize();
        m_FaceRecognition.ZBX_RegConnectEventCb(m_ConnectEventCb, 0);
    }

    public static void InitEx() {
        // Clear camera list:
        cameraPointList = new ArrayList<>();
        cameraAndIPMap = new HashMap<>();
        m_FaceRecognition.ZBX_InitEx();
        m_FaceRecognition.ZBX_SetNotifyConnected(1);
        m_ConnectEventCb = new ZBX_ConnectEventCb_t_Realize();
        m_FaceRecognition.ZBX_RegConnectEventCb(m_ConnectEventCb, 0);
    }


    public static void clsClear() {
        m_FaceRecognition.lib_clsClear();
    }

    //连接相机
    public static String connectCamera(String cameraIp, short port) {
        StringBuilder sb = new StringBuilder();
        IntByReference err_code = new IntByReference(0);
        // Connect camera:
        cameraPoint = m_FaceRecognition.ZBX_ConnectEx(cameraIp, port, "", "", err_code, 0, 1);
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            sb.append("Fail ");
            sb.append(cameraIp + ":" + port + " -- 连接相机失败");
            System.out.println(cameraIp + ":" + port + " -- 连接相机失败");
        } else {
            // Storing cameraPoints and setting up IP relations:
            cameraPointList.add(cameraPoint);
            String ipAndPort = cameraIp + ":" + port;
            cameraAndIPMap.put(cameraPoint, ipAndPort);
            // Setting up callback functions:
            m_RecoCb_t = new ZBX_FaceRecoCb_t_Realize();
            // Setting current data callback:
            m_FaceRecognition.ZBX_RegFaceRecoCb(cameraPoint, m_RecoCb_t, Pointer.NULL);
            // Setting history data callback: (seems not working)
            m_FaceRecognition.ZBX_RegFaceOffLineRecoCb(cameraPoint, m_RecoCb_t, Pointer.NULL);
            sb.append("Success ");
            sb.append(cameraIp + ":" + port + " -- 连接相机成功" + "该相机句柄：" + cameraPoint.getValue());
            System.out.println(cameraIp + ":" + port + " -- 连接相机成功");
            System.out.println("该相机句柄：" + cameraPoint.getValue());
        }
        // Write info log:
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, sb.toString());
        return sb.toString();
    }

    //断开相机
    public static void disConnectCurrentCamera() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) == 0) {
            System.out.println("相机已经断开，无需重复操作");
            return;
        }
        m_FaceRecognition.ZBX_DisConnect(cameraPoint);
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) == 0) {
            System.out.println("断开成功");
            return;
        } else System.out.println("断开失败请稍后重试");
    }

    //断开相机
    public static String disconnectAllCamera() {
        StringBuilder sb = new StringBuilder();
        for(IntByReference thisCameraPoint : cameraPointList){
            String ipPort = cameraAndIPMap.get(thisCameraPoint);
            sb.append(ipPort);
            if (m_FaceRecognition.ZBX_Connected(thisCameraPoint) == 0) {
                System.out.println("相机已经断开，无需重复操作");
                sb.append("相机已经断开，无需重复操作");
                continue;
            }
            m_FaceRecognition.ZBX_DisConnect(thisCameraPoint);
            if (m_FaceRecognition.ZBX_Connected(thisCameraPoint) == 0) {
                System.out.println("断开成功");
                sb.append("断开成功");
            } else {
                System.out.println("断开失败请稍后重试");
                sb.append("断开失败请稍后重试");
            }
            sb.append("\n");
        }
        // Clear lists:
        cameraPointList.clear();
        cameraPoint = null;
        cameraAndIPMap.clear();
        // Write info log:
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, sb.toString());
        return sb.toString();
    }

    //断开某个相机
    public static String disconnectCameraByHandle(IntByReference cam) {
        StringBuilder sb = new StringBuilder();
        String ipPort = cameraAndIPMap.get(cam);
        sb.append(ipPort);
        if (m_FaceRecognition.ZBX_Connected(cam) == 0) {
            System.out.println("相机已经断开，无需重复操作");
            sb.append("相机已经断开，无需重复操作");
        }else{
            m_FaceRecognition.ZBX_DisConnect(cam);
            if (m_FaceRecognition.ZBX_Connected(cam) == 0) {
                System.out.println("断开成功");
                sb.append("断开成功");
            } else {
                System.out.println("断开失败请稍后重试");
                sb.append("断开失败请稍后重试");
            }
            sb.append("\n");
        }
        // Remove this camera from list:
        sb.append("从当前列表中清除该相机");
        cameraPointList.remove(cam);
        cameraAndIPMap.remove(cam);
        // Write info log:
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, sb.toString());
        return sb.toString();
    }


    /*****************************************************************************************
     * Custom methods here:
     */

    /** 获取历史数据编号: 输入设备句柄、起始时间、终止时间，返回时间范围内所有刷脸squence。如果endTime为空，则获取到当前时间
     *   时间格式为String "2020-09-14 22:40:32"
     */
    public static int[] getHistoryDataSeqByTime(IntByReference camHandle, String startTime, String endTime){
        int[] result = new int[2];
        if (m_FaceRecognition.ZBX_Connected(camHandle) != 1) {
            System.out.println("相机未连接");
            return null;
        }
        System.out.println("当前相机句柄：" + camHandle.getValue());
        if(endTime == null || endTime.equals("")){
            Calendar calendar = Calendar.getInstance();
            endTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        }
        System.out.println("开始时间：" + startTime);
        System.out.println("结束时间：" + endTime);
        // Start querying:
        IntByReference startSeq = new IntByReference();
        IntByReference endSeq = new IntByReference();
        int queryRes = m_FaceRecognition.ZBX_GetResByTime(camHandle, startTime, endTime, startSeq, endSeq);
        System.out.println("获取历史数据结果：" + String.valueOf(queryRes));
        System.out.println("获取历史数据开始位置：" + String.valueOf(startSeq.getValue()));
        System.out.println("获取历史数据结束位置：" + String.valueOf(endSeq.getValue()));
        result[0] = startSeq.getValue();
        result[1] = endSeq.getValue();
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, "Seq:" + result[0] + "-" + result[1]);
        return result;
    }

    // 获取某个设备中某个sequence的数据，返回该数据并将数据储存至数据库中：
    public static libFaceRecognition.FaceRecoInfo getHistoryDataBySequence(IntByReference camHandle, int sequence) throws UnsupportedEncodingException {
        if (m_FaceRecognition.ZBX_Connected(camHandle) != 1) {
            System.out.println("相机未连接");
            return null;
        }
        libFaceRecognition.FaceRecoInfo faceRecoInfo = new libFaceRecognition.FaceRecoInfo();
        int queryResult = m_FaceRecognition.ZBX_QueryFaceOffLineReco(camHandle, sequence, faceRecoInfo);
        // Process and save data to database:
        processFaceRecoInfo(camHandle, faceRecoInfo);
        return faceRecoInfo;
    }

    /** 获取所有设备某个时间段内的数据，如果endTime为空，则获取到当前时间
     *   时间格式为String "2020-09-14 22:40:32"
     */
    public static void getHistoryDataFromAllDevices(String startTime, String endTime) throws UnsupportedEncodingException {
        // Check camera existence and connection:
        // Write info log:
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, "GetHistoryData");
        if(cameraPointList == null || cameraPointList.size() <= 0){
            return;
        }
        for(IntByReference thisCameraHandle : cameraPointList){
            if (m_FaceRecognition.ZBX_Connected(thisCameraHandle) != 1) {
                System.out.println(thisCameraHandle.getValue() + "该相机未连接");
                continue;
            }
            // Get sequence range from this camera:
            int[] startAndEndSeq = getHistoryDataSeqByTime(thisCameraHandle, startTime, endTime);
            // Get data and save data:
            for(int i = startAndEndSeq[0]; i<startAndEndSeq[1]; i++){
                getHistoryDataBySequence(thisCameraHandle, i);
            }
        }
    }

    // Get data from one device:
    public static void getHistoryDataFromOneDevices(String startTime, String endTime, String ipAndPort) throws UnsupportedEncodingException {
        // Check camera existence and connection:
        // Write info log:
        loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, "GetHistoryData");
        if(cameraPointList == null || cameraPointList.size() <= 0){
            return;
        }
        for(IntByReference thisCameraHandle : cameraPointList){
            if(cameraAndIPMap.get(thisCameraHandle).equals(ipAndPort)){
                if (m_FaceRecognition.ZBX_Connected(thisCameraHandle) != 1) {
                    System.out.println(thisCameraHandle.getValue() + "该相机未连接");
                    continue;
                }
                // Get sequence range from this camera:
                int[] startAndEndSeq = getHistoryDataSeqByTime(thisCameraHandle, startTime, endTime);
                // Get data and save data:
                for(int i = startAndEndSeq[0]; i<startAndEndSeq[1]; i++){
                    getHistoryDataBySequence(thisCameraHandle, i);
                }
            }
        }
    }

    // Check camera connection:
    public static boolean checkCameraConnection(IntByReference cam){
        if(m_FaceRecognition.ZBX_Connected(cam) != 1){
            return false;
        }else{
            return true;
        }
    }


    /*****************************************************************************************
     * Original Demo Methods Below:
     */

    private static WinDef.HWND createHWNDByComponent(JLabel jlabel) {
        return new WinDef.HWND(Native.getComponentPointer(jlabel));
    }
    // public static JFrame jf;

    public static SiChuanDemoOne d1;

    // 开始/断开视频流
    public static void StartStream() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }

        if (!BOEStream) {
            d1 = new SiChuanDemoOne();
            //传入窗口句柄
            m_FaceRecognition.ZBX_StartStream(cameraPoint, createHWNDByComponent(d1.jl1));
            System.out.println("连接视频流成功");
            BOEStream = true;
        } else {
            //传入窗口句柄
            m_FaceRecognition.ZBX_StopStreamEx(cameraPoint, createHWNDByComponent(d1.jl1));
            System.out.println("断开视频流成功");
            BOEStream = true;
        }
    }

    public static void AddFace() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        libFaceRecognition.FaceFlags.ByValue flag = new libFaceRecognition.FaceFlags.ByValue();
        libFaceRecognition.FaceImage.ByValue faceimg = new libFaceRecognition.FaceImage.ByValue();
        libFaceRecognition.FaceImage.ByValue[] faceimgarray = (libFaceRecognition.FaceImage.ByValue[]) faceimg.toArray(1);
        libFaceRecognition.FaceFlags.ByValue[] flagarray = (libFaceRecognition.FaceFlags.ByValue[]) flag.toArray(1);
        System.out.println("请输入人员姓名");
        Scanner sc = new Scanner(System.in);
        flagarray[0].faceName = sc.nextLine().getBytes();
        System.out.println("姓名：" + flagarray[0].faceName);
        System.out.println("请输入人员id");
        flagarray[0].faceID = sc.nextLine().getBytes();

        System.out.println("faceIDid" + flagarray[0].faceID);
        flagarray[0].usr_type = 0;
        flagarray[0].wgCardNO = 1234;

        //flagarray[0].effectTime = 290000000;
        //flagarray[0].effectStartTime = 250000000;
        //flagarray[0].resv = "";
        System.out.println("请输入人脸路径+图片名称");
        String filepath = sc.nextLine();
        if (filepath.isEmpty()) {
            System.out.println("输入为空");
        } else {
            byte[] imagedata = imageToByteArray(filepath);
            faceimgarray[0].img_len = imagedata.length;
            Memory a = new Memory(imagedata.length);
            a.write(0, imagedata, 0, imagedata.length);
            faceimgarray[0].img.setPointer(a);
            faceimgarray[0].img_fmt = 0;
            try {
                byte[] imagedata1 = new byte[imagedata.length];
                a.read(0, imagedata1, 0, imagedata.length);
                String filename = "D:\\fgs" + ".jpg";
                // 转换成图片
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(imagedata1));
                //保存图片
                writeImageFile(bi, filename);
            } catch (Exception e) {
                System.out.println("数据有误");
                return;
            }


            int k = m_FaceRecognition.ZBX_AddJpgFaces(cameraPoint, flagarray,
                    faceimgarray, 1, 0);

            if (k == 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败" + k);
            }
        }
    }


    //查询删除实现
    public static void degregmng() {
        System.out.println("请输入操作:  1：查询 2：删除 3：删除所有  ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        if (chooice == 1) {
            m_FaceQueryCb_t = new ZBX_FaceQueryCb_t_Realize();
            m_FaceRecognition.ZBX_RegFaceQueryCb(cameraPoint, m_FaceQueryCb_t, Pointer.NULL);

            int idx = 1;//查询页码
            int num = 10;//每页条数
            m_FaceRecognition.ZBX_QueryByRole(cameraPoint, -1, idx, num, '0', '0');
        } else if (chooice == 2) {
            System.out.println("请输入删除人员id  ");
            String personid = sc.nextLine();
            m_FaceRecognition.ZBX_DeleteFaceDataByPersonID(cameraPoint, personid);
        } else if (chooice == 3) {
            int tag = m_FaceRecognition.ZBX_DeleteFaceDataAll(cameraPoint);
            if (tag == 0) System.out.println("删除所有成功  ");
            else System.out.println("删除所有失败  ");
        } else System.out.println("输入有误");

    }

    //搜索相机
    public static void searchcerme() {
        m_discover_ipscan = new discover_ipscan_cb_t_Realize();
        m_FaceRecognition.ZBX_RegDiscoverIpscanCb(m_discover_ipscan, 0);
        m_FaceRecognition.ZBX_DiscoverIpscan();
    }

    //设置韦根
    public static void SettingWG() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：设置 2：获取  ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
            return;
        }
        if (chooice == 1) {
            System.out.println("请输入操作韦根号  ");
            int temp = 0;
            try {
                temp = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入有误");
                return;
            }
            int tag = m_FaceRecognition.ZBX_SetWiegandType(cameraPoint, temp);
            if (tag == 0) System.out.println("设置成功");
            else System.out.println("设置失败");
        } else if (chooice == 2) {
            IntByReference type = new IntByReference(0);
            if (m_FaceRecognition.ZBX_GetWiegandType(cameraPoint, type) == 0) {
                System.out.println(type.getValue());

            } else {
                System.out.println("获取失败");
            }

        } else {
            System.out.println("输入有误");
            return;
        }
    }

    //灯控设置
    public void SettingLight() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：设置 2：获取  ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
            return;
        }
        if (chooice == 1) {
            System.out.println("请输入灯控模式 ");
            sc = new Scanner(System.in);
            byte led_mode = (byte) sc.nextInt();
            m_FaceRecognition.ZBX_SetLedMode(cameraPoint, led_mode);
            System.out.println("请输入灯亮度 ");
            sc = new Scanner(System.in);
            byte led_level = (byte) sc.nextInt();
            m_FaceRecognition.ZBX_SetLedLevel(cameraPoint, led_level);
        } else if (chooice == 2) {
            Pointer a = new Memory(2);
            Pointer led_mode = new Memory(10);

            Pointer led_level = new Memory(10);
            m_FaceRecognition.ZBX_GetLedMode(cameraPoint, led_mode);
            for (int i = 0; i < 10; ++i) {
                if ((char) led_mode.getByteArray(0, 10)[i] == '\0') break;
                System.out.print((char) led_mode.getByteArray(0, 10)[i]);
            }
            System.out.println("\n");
            System.out.println(led_mode);
            m_FaceRecognition.ZBX_GetLedLevel(cameraPoint, led_level);
            for (int i = 0; i < 10; ++i) {
                if ((char) led_level.getByteArray(0, 10)[i] == '\0') break;
                System.out.print((char) led_level.getByteArray(0, 10)[i]);
            }
            System.out.println("\n");
        } else {
            System.out.println("输入有误");
            return;
        }
    }

    //去重复设置
    public void Repetition() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：查询 2：设置 ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {
            IntByReference interval = new IntByReference(-1);
            int succ = m_FaceRecognition.ZBX_GetClusterTimesInterval(cameraPoint, interval);
            if (succ == 0) {
                System.out.println("查询成功 ");
                System.out.println(interval.getValue());
            } else {
                System.out.println("查询失败 ");
            }
        } else if (chooice == 2) {
            int interval = -1;
            System.out.println("请输入重复人员间隔");
            try {
                interval = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入有误");
            }
            int succ = m_FaceRecognition.ZBX_SetClusterTimesInterval(cameraPoint, interval);
            if (succ == 0) {
                System.out.println("设置成功 ");
            } else {
                System.out.println("设置失败 ");
            }
        } else {
            System.out.println("输入有误");
            return;
        }

    }

    //相似度设置
    public void SimilaritySetting() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：查询 2：设置 ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {
            IntByReference score = new IntByReference(-1);
            int succ = m_FaceRecognition.ZBX_GetMatchScore(cameraPoint, score);
            if (succ == 0) {
                System.out.println("查询成功 ");
                System.out.println(score.getValue());
            } else {
                System.out.println("查询失败 ");
            }
        } else if (chooice == 2) {
            int score = -1;
            System.out.println("请输入相似度");
            try {
                score = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入有误");
            }
            if (score < 0 || score > 100) {
                System.out.println("请输入正确的相似度 应为0~100");
            }
            int succ = m_FaceRecognition.ZBX_SetMatchScore(cameraPoint, score);
            if (succ == 0) {
                System.out.println("设置成功 ");
            } else {
                System.out.println("设置失败 ");
            }
        } else {
            System.out.println("输入有误");
            return;
        }

    }

    //标题设置
    public void TagSetting() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：查询 2：设置 ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {
            byte[] screen_title = new byte[64];

            int succ = m_FaceRecognition.ZBX_GetScreenOsdTitle(cameraPoint, screen_title);
            if (succ == 0) {
                try {
                    String title = new String(getfullbyte(screen_title), "gb2312");
                    System.out.println(title);
                } catch (Exception e) {
                    System.out.println("数据有误");
                }
                System.out.println("查询成功 ");

            } else {
                System.out.println("查询失败 ");
            }
        } else if (chooice == 2) {
            byte[] title = new byte[64];
            System.out.println("请输入相标题");
            try {
                Scanner sc1 = new Scanner(System.in);
                String t = sc1.nextLine();
                title = (t).getBytes();
            } catch (Exception e) {
                System.out.println("输入有误");
            }

            int succ = m_FaceRecognition.ZBX_SetScreenOsdTitle(cameraPoint, title);
            if (succ == 0) {
                System.out.println("设置成功 ");
            } else {
                System.out.println("设置失败 ");
            }
        } else {
            System.out.println("输入有误");
            return;
        }

    }


    //时间设置
    public static void TimeSetting() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：获取相机时间 2：设置 ");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {

            IntByReference second = new IntByReference();
            int succ = m_FaceRecognition.ZBX_GetSysTime(cameraPoint, second);
            if (succ == 0) {
                Date date2 = new Date();
                long t = 1000;
                date2.setTime(second.getValue() * t);
                System.out.print(date2);
                System.out.println('\n');
            } else {
                System.out.println("查询失败 ");
            }
        } else if (chooice == 2) {

            System.out.println("下面是一个设置时间示例");

            int succ = m_FaceRecognition.ZBX_SetSysTimeEx(cameraPoint, 2019, 4, 1, 8, 0, 0);
            if (succ == 0) {
                System.out.println("设置成功 ");
            } else {
                System.out.println("设置失败 ");
            }
        } else {
            System.out.println("输入有误");
            return;
        }

    }

    //系统升级
    public void upgrade() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("系统升级文件路径+文件名 ");

        String filename = null;
        try {
            Scanner sc = new Scanner(System.in);
            filename = sc.nextLine();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        Pointer user_data = null;
        IntByReference second = new IntByReference();
        m_HTTPRESULT_PROCESS = new Httpresult_Realize();
        m_FaceRecognition.ZBX_SetUpdateSystem_CB(cameraPoint, m_HTTPRESULT_PROCESS
                , user_data);
        int iRet = m_FaceRecognition.ZBX_UpdateSystem(cameraPoint,
                filename.getBytes());

        if (iRet == 0) {
            System.out.println("升级完成 设备即将重启");
            if (m_FaceRecognition.ZBX_Connected(cameraPoint) == 1)
                m_FaceRecognition.ZBX_DisConnect(cameraPoint);
            cameraPoint = (null);
        } else {
            System.out.println("升级失败 请重试");
        }
    }


    //串口设置
    public void GorgelineSetting() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }
        System.out.println("请输入操作:  1：获取参数 2：设置 ，3： 发送串口消息");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {

            int succ = m_FaceRecognition.ZBX_GetRS485ProtocalNo(cameraPoint, m_rs485_protocal_no);
            System.out.print(deCode(new String(getfullbyte(m_rs485_protocal_no))).trim());
            System.out.println(' ');
            IntByReference baudrate = new IntByReference();
            IntByReference parity = new IntByReference();
            IntByReference databit = new IntByReference();
            IntByReference stopbit = new IntByReference();

            succ = m_FaceRecognition.ZBX_GetTSerial(cameraPoint, 0, baudrate,
                    parity, databit, stopbit);
            if (succ == 0) {
                System.out.print(baudrate.getValue());
                System.out.println(' ');
                System.out.print(parity.getValue());
                System.out.println(' ');
                System.out.print(databit.getValue());
                System.out.println(' ');
                System.out.print(stopbit.getValue());
                System.out.println(' ');

            } else {
                System.out.println("查询失败 ");
            }
        } else if (chooice == 2) {

            //输入示例
            int index = 0, baudrate = 115200,
                    parity = 0, databit = 8, stopbit = 1;
            char rs485_protocal_no = '0';
            int tag = m_FaceRecognition.ZBX_OpenTSerial(cameraPoint, index, baudrate,
                    parity, databit, stopbit);
            if (tag == 0) {
                System.out.println("设置串口成功");
            } else {
                System.out.println("设置串口失败");
            }
            tag = m_FaceRecognition.ZBX_SetRS485ProtocalNo(cameraPoint, rs485_protocal_no);
            if (tag == 0) {
                System.out.println("设置串口协议成功");
            } else {
                System.out.println("设置串口协议失败");
            }
        } else if (chooice == 3) {
            String t = "";
            System.out.println("请输入要发送的串口消息");
            try {
                Scanner sc1 = new Scanner(System.in);
                t = sc1.nextLine();

            } catch (Exception e) {
                System.out.println("输入有误");
            }
            int succ = m_FaceRecognition.ZBX_WriteTSerial(cameraPoint, (int) m_rs485_protocal_no[0],
                    t.getBytes(), t.length());
            if (succ == 0) {
                System.out.println("发送成功");
            } else {
                System.out.println("发送失败");
            }
        } else {
            System.out.println("输入有误");
            return;
        }

    }

    public void webSetting() {
        if (m_FaceRecognition.ZBX_Connected(cameraPoint) != 1) {
            System.out.println("相机未连接");
            return;
        }

        System.out.println("请输入操作:  1：获取网络信息 2：设置，3：修改ip");
        Scanner sc = new Scanner(System.in);
        int chooice = -1;
        try {
            chooice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        if (chooice == 1) {

            libFaceRecognition.SystemNetInfo.ByReference SystemNet = new libFaceRecognition.SystemNetInfo.ByReference();
            int succ = m_FaceRecognition.ZBX_GetNetConfig(cameraPoint, SystemNet);
            if (succ == 0) {
                System.out.println("获取网络信息成功");
                System.out.println(deCode(new String(getfullbyte((SystemNet.ip_addr))).trim()));
                System.out.println(deCode(new String(((SystemNet.mac_addr))).trim()));
                System.out.println(deCode(new String(getfullbyte((SystemNet.netmask))).trim()));
                System.out.println(deCode(new String(((SystemNet.gateway))).trim()));
            } else {
                System.out.println("获取网络信息失败 ");
            }
        } else if (chooice == 2) {

            //输入示例
            libFaceRecognition.SystemNetInfo.ByReference netInfo = new libFaceRecognition.SystemNetInfo.ByReference();
            System.out.println("请输入ip");
            Scanner sc1 = new Scanner(System.in);
            netInfo.ip_addr = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入mac");
            netInfo.mac_addr = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入子网掩码");
            netInfo.netmask = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入默认网关");
            netInfo.gateway = sc1.nextLine().getBytes();//.getBytes();
            int tag = m_FaceRecognition.ZBX_SetNetConfig(cameraPoint, netInfo);

            if (tag == 0) {
                System.out.println("设置成功");
            } else {
                System.out.println("设置失败");
            }

        } else if (chooice == 3) {
            System.out.println("请输入原相机的mac");
            Scanner sc1 = new Scanner(System.in);
            byte[] mac_addr = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入原相机的netmask");
            byte[] netmask = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入原相机的gateway");
            byte[] gateway = sc1.nextLine().getBytes();//.getBytes();
            System.out.println("请输入新ip");
            byte[] ip_addr = sc1.nextLine().getBytes();//.getBytes();
            m_FaceRecognition.ZBX_SetIpBymac(mac_addr, ip_addr,
                    netmask, gateway);

        } else {
            System.out.println("输入有误");
            return;
        }

    }

    public void DecodeDemo() throws UnsupportedEncodingException {
        try {
            System.out.println("请输入需要解码的内容：");
            Scanner sc1 = new Scanner(System.in);
            byte[] decoded_data = sc1.nextLine().getBytes();
            System.out.println("请输入密码：");
            byte[] key = sc1.nextLine().getBytes();//.getBytes();
            m_FaceRecognition.ZBX_DecodeByKey(decoded_data, decoded_data.length,
                    sc1.nextLine(), sc1.nextLine().length());

            String decoded = new String(decoded_data, "gb2312");
            System.out.println("解码内容为：" + decoded);
        } catch (Exception e) {
            System.out.println("输入有误" + e.toString());
        }

    }


    /*****************************************************************************************
     * Callback and supporting Methods Below:
     */

    //抓拍回调实现
    public static class ZBX_FaceRecoCb_t_Realize implements libFaceRecognition.ZBX_FaceRecoCb_t {
        public void Status(IntByReference cam, libFaceRecognition.FaceRecoInfo.ByReference cb,
                           Pointer usrParam) {
            processFaceRecoInfo(cam, cb);
            return;
        }
    }

    public static void processFaceRecoInfo(IntByReference cam, libFaceRecognition.FaceRecoInfo faceRecoInfo){
        // Ensure the file path is created:
        File myPath = new File(DETECT_IMG_FILE_PATH);
        if (!myPath.exists()) {
            myPath.mkdirs();
        }
        // Get data processing time:
        long dataProcessingTime = System.currentTimeMillis();
        Date dataProcessingDate = new Date(dataProcessingTime);
        // Get data received time:
        long dataRecTime = faceRecoInfo.tvSec; // second
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date2 = new Date(dataRecTime * 1000);
        String datetimeStr = sdf.format(date2);
        System.out.println("数据接收时间：" + datetimeStr);

        if (faceRecoInfo.matched == -1) {
            System.out.println("未识别人员");
            String filepath = DETECT_IMG_FILE_PATH + "\\unidentified\\";
            File myfilepath = new File(filepath);
            if (!myfilepath.exists()) {
                myfilepath.mkdirs();
            }
            String filename = filepath + datetimeStr + ".jpg";
            byte[] data = faceRecoInfo.faceImg.getPointer().getByteArray(0, faceRecoInfo.faceImgLen);
            try {
                // 转换成图片
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
                //保存图片
                writeImageFile(bi, filename);
            } catch (Exception e) {
                System.out.println("数据有误");
                return;
            }
        } else {
            //保存人员信息
            try {
                // Get info:
                String personName = new String(getfullbyte(faceRecoInfo.matchPersonName), "utf-8");
                String personId = new String(getfullbyte(faceRecoInfo.matchPersonId), "utf-8");
                String deviceNO = String.valueOf(cam.getValue());
                String[] ipAndPort = cameraAndIPMap.get(cam).split(":");
                String deviceIP = ipAndPort[0];
                String devicePort = ipAndPort[1];
                int matchConfidence = faceRecoInfo.matched;
                if(personName == null || personName.equals("") || personId == null || personId.equals("")
                || personId.equals("N") || personName.equals("N") || matchConfidence < 10){
                    System.out.println("该条数据不符合要求");
                    return;
                }
                int matchedRole = faceRecoInfo.matchRole;
                String base64Result = "";
                try{
                    // Try to get image:
                    byte[] imgBytes = faceRecoInfo.faceImg.getPointer().getByteArray(0, faceRecoInfo.faceImgLen);
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imgBytes));
                    // Image to byte buffer:
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "jpg", bos);
                    // Byte buffer to Base64 String:
                    base64Result = Base64.encodeBase64String(bos.toByteArray());
                    // Add Base64 head:
                    base64Result = "data:image/jpg;base64" + "," + base64Result;
                    bos.close();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("刷脸记录图片数据有误！");
                    // Write info log:
                    loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, "刷脸记录图片数据有误！");
                    base64Result = "errorImg";
                }
                // Get required info from structure and save it to database:
                FaceDetectData thisFaceDetectData = new FaceDetectData();
                thisFaceDetectData.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
                thisFaceDetectData.setTime_stamp(dataRecTime * 1000);
                thisFaceDetectData.setAlert_major_type(matchConfidence);
                thisFaceDetectData.setAlert_minor_type(500);
                thisFaceDetectData.setCard_no(personId);
                thisFaceDetectData.setUser_info(personName);
                thisFaceDetectData.setWork_no(0);
                thisFaceDetectData.setCard_alert_type(matchedRole);
                thisFaceDetectData.setDevice_no(deviceNO);
                thisFaceDetectData.setDevice_ip(deviceIP);
                thisFaceDetectData.setDevice_port(devicePort);
                thisFaceDetectData.setPic_data(base64Result);
                thisFaceDetectData.setCreate_timestamp(dataProcessingTime);
                thisFaceDetectData.setCreate_date(dataProcessingDate);
                // Write log and print data to console:
                StringBuilder consoleDisplaySB = new StringBuilder();
                consoleDisplaySB.append("识别用户：" + personName + "  ID:" + personId + "\n");
                consoleDisplaySB.append("设备句柄：" + deviceNO + "\n");
                consoleDisplaySB.append("设备IP：" + deviceIP + "端口：" + devicePort + "\n");
                consoleDisplaySB.append("识别置信度：" + matchConfidence + "\n");
                String consoleDisplayStr = consoleDisplaySB.toString();
                System.out.println(consoleDisplayStr);
                // Write info log:
                loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, consoleDisplayStr);
                // Write to DB:
                System.out.println("刷脸数据写入数据库中...");
                faceDetectDataService.insert(thisFaceDetectData);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("刷脸数据有误");
                // Write info log:
                loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, e.getMessage());
            }
            // Try to Save img to file:
            try{
                String filepath = DETECT_IMG_FILE_PATH + "\\identified\\";
                File myfilepath = new File(filepath);
                if (!myfilepath.exists()) {
                    myfilepath.mkdirs();
                }
                String filename = filepath + datetimeStr + ".jpg";
                byte[] data = faceRecoInfo.faceImg.getPointer().getByteArray(0, faceRecoInfo.faceImgLen);
                // 转换成图片
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
                //保存图片
                writeImageFile(bi, filename);
            }catch (Exception e){
                System.out.println("刷脸图片数据有误");
                System.out.println(e.toString());
                return;
            }
        }
        return;
    }


    //人员查询回调实现
    public static class ZBX_FaceQueryCb_t_Realize implements libFaceRecognition.ZBX_FaceQueryCb_t {
        public void Status(IntByReference cam, libFaceRecognition.QueryFaceInfo.ByReference QueryFaceIn,
                           Pointer usrParam) {
            try {
                String personname = new String(getfullbyte(QueryFaceIn.personName), "gb2312");
                System.out.println(personname);
            } catch (Exception e) {
                System.out.println("数据有误");
            }

            String personId = deCode(new String(QueryFaceIn.personID)).trim();// deCode(matchPersonID).trim();
            System.out.println(personId);
            //将data 数据转成byte[]
            byte[] data = QueryFaceIn.imgBuff[0].getPointer().getByteArray(0, QueryFaceIn.imgSize[0]);

            //
            SimpleDateFormat lFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            date.getTime();
            String path = "D:\\camera";   //保存文件路径
            File myPath = new File(path);
            if (!myPath.exists()) {
                myPath.mkdir();
            }
            String filename = path + lFormat.format(date) + ".jpg";

            System.out.println(filename);

            try {
                // 转换成图片
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
                //保存图片
                writeImageFile(bi, filename);
            } catch (Exception e) {
                System.out.println("数据有误");
                return;
            }
        }
    }


    //搜索相机回调函数
    public static class discover_ipscan_cb_t_Realize implements libFaceRecognition.discover_ipscan_cb_t {
        public void Status(libFaceRecognition.ipscan_t.ByReference ips, int usr_param) {
            if (mapcamreip.containsKey(deCode(new String(ips.mac)).trim()) == false) {
                // 输出查到的ip
                System.out.println(deCode(new String(ips.ip)).trim());
                System.out.println(deCode(new String(ips.mac)).trim());
                System.out.println(deCode(new String(ips.manufacturer)).trim());
            } else {
                mapcamreip.put(deCode(new String(ips.mac)).trim(), deCode(new String(ips.ip)).trim());
            }
        }
    }


    /* 连接事件回调函数 */
    // event 1为已连接 0为连接中断
    public static class ZBX_ConnectEventCb_t_Realize implements libFaceRecognition.ZBX_ConnectEventCb_t {
        public void Status(IntByReference cam, String ip, short port, int event, int usrParam) {
            switch (event){
                case 0: // 连接中断
                    StringBuilder sb = new StringBuilder();
                    sb.append("case 0 triggered: \n");
                    sb.append(cam + ":" + ip + ":" + port + "设备连接中断...");
                    System.out.println(sb.toString());
                    // Write info log:
                    loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, sb.toString());
                    if (m_FaceRecognition.ZBX_Connected(cam) == 1) {
                        disconnectCameraByHandle(cam);
                    }else{
                        disconnectCameraByHandle(cam);
                        System.out.println("重新连接...");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    int count = 0;
                                    String thisIP = ip;
                                    short thisPort = port;
                                    while(true){
                                        if(count >= 10){
                                            break;
                                        }
                                        count++;
                                        String res = connectCamera(thisIP, thisPort);
                                        if(res.startsWith("Success")){
                                            break;
                                        }else{
                                            System.out.println("Retrying..." + thisIP + thisPort);
                                        }
                                        Thread.sleep(15000);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                    break;
                case 1: // 已连接
                    //System.out.println("case 1");
                    // Write info log:
                    //loggerService.writeInfoLogs(INFO_LOG_FILE_PATH, "case 1");
                    break;
                default:
                    break;

            }
//            if (event == 0) {
//                if (m_FaceRecognition.ZBX_IsServer(cameraPoint) == 1) {
//                    m_FaceRecognition.ZBX_DisConnect(cameraPoint);
//                }
//                return;
//            }
        }
    }

    //系统升级进度
    //注意：如果注册此回调 该回调必须返回0 否则会终止传输
    public static class Httpresult_Realize implements libFaceRecognition.ZBX_HTTPRESULT_PROCESS {
        public int Status(Pointer user_data, double rDlTotal, double rDlNow, double rUlTotal, double rUlNow) {
            if (!(rUlTotal == 0) && !(rUlNow == 0)) {
                return 0;
            }
            int process = (int) (rDlNow * 100 / rUlTotal);
            System.out.println("系统升级进度为： ");
            System.out.println(process);
            return 0;
        }
    }

/***************************************************************************************************************/
    //byte数组到图片
    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) return;
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }


    public static String deCode(String str) {
        try {
            return java.net.URLDecoder.decode(str, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String deCodeUtf_8(String str) {
        try {
            return java.net.URLDecoder.decode(str, "Unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    //  读取图片转换成byte[]
    public static byte[] imageToByteArray(String imgPath) {
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(imgPath));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int size = 0;
            byte[] temp = new byte[1024];
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //图像读取
    public static BufferedImage readImageFile(String filename) {
        File file = new File(filename);
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //图像保存
    public static void writeImageFile(BufferedImage bi, String filename) throws IOException {
        File outputfile = new File(filename);
        ImageIO.write(bi, "jpg", outputfile);
    }

    public static byte[] bufferedImageToByteArray(BufferedImage img)
            throws ImageFormatException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
        encoder.encode(img);
        return os.toByteArray();
    }

    public static byte[] getfullbyte(byte[] orgbyte) {
        int length = 0;
        for (int i = 0; i < orgbyte.length; ++i) {
            if (orgbyte[i] != '\0') ++length;
            else break;
        }
        //byte[] temp = new byte[length + 1];
        byte[] temp = new byte[length];
        for (int i = 0; i < length; ++i) {
            temp[i] = orgbyte[i];
        }
        //temp[length] = '\0';
        return temp;
    }
}
