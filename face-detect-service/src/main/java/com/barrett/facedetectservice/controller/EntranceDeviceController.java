package com.barrett.facedetectservice.controller;


import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.mapper.DeviceIPMapMapper;
import com.barrett.facedetectservice.service.DeviceIPMapService;
import com.barrett.facedetectservice.service.FaceDetectDataService;
import com.barrett.facedetectservice.util.FaceDetectDeviceControl;
import com.barrett.facedetectservice.util.SiChuanEntranceControl;
import com.barrett.facedetectservice.util.UniqueIDGenerator;
import com.netflix.discovery.converters.Auto;
import com.sun.jna.ptr.IntByReference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @program: smartsite-master
 * @description: RestController that handles entrance devices
 * @author: Barrett
 * @create: 2020-10-16 10:09
 **/
@CrossOrigin
@RestController
@RequestMapping({"/EntranceDevice"})
@Api(tags = {"门禁设备后端操控接口"})
public class EntranceDeviceController {
    @Autowired
    SiChuanEntranceControl siChuanEntranceControl;
    @Autowired
    DeviceIPMapService deviceIPMapService;
    @Autowired
    FaceDetectDataService faceDetectDataService;

    /**
     * @Description: Get history data from device
     * @Param: 时间格式为 String "2020-09-14 22:40:32"
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取某一时间点起的人脸识别设备的历史数据", notes = "获取某一时间点起的人脸识别设备的历史数据")
    @RequestMapping(value = {"/AllHistoryDataTillNow"}, method = {RequestMethod.GET})
    public String getHistoryDataTillNow(@RequestParam(required = true, value = "startTime") String startTime) throws UnsupportedEncodingException {
        System.out.println("Getting history data from all devices");
        try{
            siChuanEntranceControl.getHistoryDataFromAllDevices(startTime, null);
        }catch (Exception e){
            return e.toString();
        }
        return "Success - Querying Ended";
    }

    /**
     * @Description: Get history data from one device
     * @Param: 时间格式为 String "2020-09-14 22:40:32"
     * @Param: 设备格式为： "118.122.246.56:54"
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取某一个设备一段时间的数据", notes = "获取某一时间点起的人脸识别设备的历史数据")
    @RequestMapping(value = {"/HistoryDataFromOneDevice"}, method = {RequestMethod.GET})
    public String getHistoryDataFromOneDevice(@RequestParam(required = true, value = "startTime") String startTime, @RequestParam(required = true, value = "device") String ipAndPort) throws UnsupportedEncodingException {
        System.out.println("Getting history data from all devices");
        try{
            siChuanEntranceControl.getHistoryDataFromOneDevices(startTime, null, ipAndPort);
        }catch (Exception e){
            return e.toString();
        }
        return "Success - Querying Ended";
    }

    /**
     * @Description: Get history data from device
     * @Param: 时间格式为 String "2020-09-14 22:40:32"
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取某一时间段内人脸识别设备的历史数据", notes = "获取某一时间段内人脸识别设备的历史数据")
    @RequestMapping(value = {"/AllHistoryData"}, method = {RequestMethod.GET})
    public String getHistoryData(@RequestParam(required = true, value = "startTime") String startTime,
     @RequestParam(required = true, value = "endTime") String endTime) throws UnsupportedEncodingException {
        System.out.println("Getting history data from all devices");
        try{
            siChuanEntranceControl.getHistoryDataFromAllDevices(startTime, endTime);
        }catch (Exception e){
            return e.toString();
        }
        return "Success - Querying Ended";
    }

    /**
     * @Description: Disconnect all devices
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "断开所有相机", notes = "断开所有相机")
    @RequestMapping(value = {"/Disconnect"}, method = {RequestMethod.GET})
    public String disconnect()  {
        System.out.println("Disconnecting all devices...");
        return siChuanEntranceControl.disconnectAllCamera();
    }

    /**
     * @Description: reconnect all devices
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "断开并重新连接所有设备", notes = "断开并重新连接所有设备")
    @RequestMapping(value = {"/Reconnect"}, method = {RequestMethod.GET})
    public String reconnect()  {
        StringBuilder sb = new StringBuilder();
        System.out.println("Disconnect and reconnect all devices");
        // Disconnect all devices:
        String disconRes = disconnect();
        sb.append(disconRes + "\n");
        // Get all available devices:
        List<DeviceIPMap> deviceIPMapList = deviceIPMapService.getAll();
        // reconnect devices:
        for(DeviceIPMap thisDevice : deviceIPMapList){
            String connectRes = siChuanEntranceControl.connectCamera(thisDevice.getDevice_ip(), Short.valueOf(thisDevice.getDevice_port()));
            sb.append(connectRes + "\n");
        }
        return sb.toString();
    }

    /**
     * @Description: current devices
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取当前连接的所有设备", notes = "获取当前连接的所有设备")
    @RequestMapping(value = {"/CurrentConnectedDevices"}, method = {RequestMethod.GET})
    public List<String> getCurrentConnectedDevices()  {
        System.out.println("Getting current connected devices");
        List<String> resultList = new ArrayList<>();
        for(IntByReference thisCameraPoint : siChuanEntranceControl.cameraPointList){
            if(siChuanEntranceControl.checkCameraConnection(thisCameraPoint)){
                resultList.add(siChuanEntranceControl.cameraAndIPMap.get(thisCameraPoint));
            }
        }
        return resultList;
    }

    /**
     * @Description: Data facker
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "生成一些假的示例数据", notes = "生成一些假的示例数据")
    @RequestMapping(value = {"/DataFaker"}, method = {RequestMethod.GET})
    public String createFakeData()  {
        System.out.println("Generating Fake Data...");
        // Fake data:
        HashMap<String, String> nameAndID = new HashMap<>();
        nameAndID.put("赵渝", "511621198807138000");
        nameAndID.put("赖传柯", "510107199411190000");
        nameAndID.put("杨琦", "513124199201064917");
        nameAndID.put("付克武", "513101196909024816");
        nameAndID.put("饶宗健", "513101196903105017");
        nameAndID.put("许雅萍", "513101197203284821");
        nameAndID.put("任成军", "513101198911044815");
        nameAndID.put("陈彪", "430903198801222493");
        nameAndID.put("杨清海", "653130198403062000");
        nameAndID.put("黄远琼", "440825198812251737");
        nameAndID.put("陈朝松", "513021197407277818");
        nameAndID.put("段腊梅", "511011199412056547");
        nameAndID.put("冷建", "510521196709267999");
        nameAndID.put("鲁向坚", "510521197705128196");
        nameAndID.put("苏先华", "511011198712192933");
        nameAndID.put("王用华", "513021198710122936");
        nameAndID.put("彭成红", "51302119830208167X");
        nameAndID.put("兰天鹏", "510124199701184314");
        nameAndID.put("卿年军", "513021198611082895");
        nameAndID.put("周代伟", "513021197307122915");
        nameAndID.put("吴大波", "513021197703224219");
        nameAndID.put("陈亮", "513021198604055493");
        nameAndID.put("魏方荣", "513021197005244570");
        nameAndID.put("李术亮", "513021196508227858");
        nameAndID.put("王青", "513228196005080810");
        nameAndID.put("叶红", "510727196804241615");
        String ip = "118.122.246.56";
        String[] ports = new String[]{"53","54","55","62"};
        Random random = new Random();
        // Save data:
        Set<String> allKeys = nameAndID.keySet();
        for(String thisKey : allKeys){
            long dataProcessingTime = System.currentTimeMillis();
            Date dataProcessingDate = new Date(dataProcessingTime);
            int portIndex = random.nextInt(ports.length);
            FaceDetectData faceDetectData = new FaceDetectData();
            faceDetectData.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
            faceDetectData.setTime_stamp(dataProcessingTime);
            faceDetectData.setAlert_major_type(96);
            faceDetectData.setAlert_minor_type(500);
            faceDetectData.setCard_no(nameAndID.get(thisKey));
            faceDetectData.setUser_info(thisKey);
            faceDetectData.setWork_no(0);
            faceDetectData.setCard_alert_type(1);
            faceDetectData.setDevice_no("123123455");
            faceDetectData.setDevice_ip(ip);
            faceDetectData.setDevice_port(ports[portIndex]);
            faceDetectData.setPic_data("errorImg");
            faceDetectData.setCreate_timestamp(dataProcessingTime);
            faceDetectData.setCreate_date(dataProcessingDate);
            System.out.println("Writing fake data...");
            faceDetectDataService.insert(faceDetectData);
        }
        return "Fake data generated!";
    }


}
