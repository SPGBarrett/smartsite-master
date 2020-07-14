package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.AlertMsg;
import com.barrett.aialertservice.bean.HeadInfo;
import com.barrett.aialertservice.bean.HelmetMsgData;
import com.barrett.aialertservice.mapper.AlertMsgMapper;
import com.barrett.aialertservice.mapper.HeadInfoMapper;
import com.barrett.aialertservice.mapper.HelmetMsgDataMapper;
import com.barrett.aialertservice.service.HelmetAlertInfoService;
import com.barrett.aialertservice.util.ImageProcessor;
import com.barrett.aialertservice.util.UniqueIDGenerator;
import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoOutput;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: smartsite-master
 * @description: Interact with controller, not DAO
 * @author: Barrett
 * @create: 2020-05-05 14:57
 **/
@Service
public class HelmetAlertInfoImpl implements HelmetAlertInfoService {
    @Autowired(required = false)
    AlertMsgMapper alertMsgMapper;
    @Autowired(required = false)
    HeadInfoMapper headInfoMapper;
    @Autowired(required = false)
    HelmetMsgDataMapper helmetMsgDataMapper;
    @Value("${ai.process.image.switch}")
    boolean processImageSwitch;
    @Value("${ai.detect.image.path}")
    String detectImageSavePath;


    @Override
    public List<HelmetAlertInfoOutput> getByGuid(String paramGuid) {
        // Define return type:
        List<HelmetAlertInfoOutput> resultList = new ArrayList<>();
        AlertMsg thisMsg = alertMsgMapper.getAllByGuid(paramGuid).get(0);
        HelmetMsgData thisData = helmetMsgDataMapper.getAllByParentID(thisMsg.getGuid()).get(0);
        List<HeadInfo> thisHeadInfoList = headInfoMapper.getAllByParentID(thisData.getGuid());
        // Save data to result:
        HelmetAlertInfoOutput thisOuput = new HelmetAlertInfoOutput();
        thisOuput.setId(thisMsg.getId());
        thisOuput.setGuid(thisMsg.getGuid());
        thisOuput.setAid(thisMsg.getAid());
        thisOuput.setCid(thisMsg.getCid());
        thisOuput.setCid_url(thisMsg.getCid_url());
        thisOuput.setTime_stamp(thisMsg.getTime_stamp());
        thisOuput.setStatus(thisMsg.getStatus());
        thisOuput.setSrcpic_name(thisMsg.getSrcpic_name());
        thisOuput.setSrcpic_data(thisMsg.getSrcpic_data());
        thisOuput.setPic_name(thisMsg.getPic_name());
        thisOuput.setPic_data(thisMsg.getPic_data());
        // Set data msg:
        thisOuput.getData().setId(thisData.getId());
        thisOuput.getData().setGuid(thisData.getGuid());
        thisOuput.getData().setTimeStamp(thisData.getTime_stamp());
        thisOuput.getData().setNumOfHead(thisData.getNum_of_head());
        thisOuput.getData().setAlertFlag(thisData.getAlert_flag());
        // Set head info list:
        thisOuput.getData().setHeadInfo(thisHeadInfoList);
        // Add data to list:
        resultList.add(thisOuput);
        return resultList;
    }

    @Override
    public List<HelmetAlertInfoOutput> getAll() {
        List<HelmetAlertInfoOutput> resultList = new ArrayList<>();
        List<AlertMsg> msgList = alertMsgMapper.getAll();
        for (AlertMsg msg : msgList){
            resultList.add(getByGuid(msg.getGuid()).get(0));
        }
        return resultList;
    }

    /** 
    * @Description: insert data from helmet info input 
    * @Param:  
    * @return:
    * @Author: Barrett
    * @Date:  
    */ 
    @Override
    public AIAlertPushMsg insertHelmetAlertData(HelmetAlertInfoInput inputData) {
        AIAlertPushMsg resultMsgToPush = new AIAlertPushMsg();
        // extract data from input data:
        AlertMsg thisMsg = new AlertMsg();
        HelmetMsgData thisData = new HelmetMsgData();
        List<HeadInfo> thisHeadInfoList = new ArrayList<>();
        // Get GUID:
        String guidOfAlertMsg = UniqueIDGenerator.getUUIDWithoutDash();
        String guidOfHelmetMsgData = UniqueIDGenerator.getUUIDWithoutDash();
        String srcPicFileName = UniqueIDGenerator.getUUIDWithoutDash() + ".jpg";
        String detectPicFileName = UniqueIDGenerator.getUUIDWithoutDash() + ".jpg";
        // Save images to file:
        ImageProcessor ip = new ImageProcessor();
        try{
            ip.saveBase64StringToFile(inputData.getPic_data(), detectImageSavePath + detectPicFileName);
            ip.saveBase64StringToFile(inputData.getSrcpic_data(), detectImageSavePath + srcPicFileName);
        }catch (Exception e){
            System.out.println("写图像文件失败！");
            e.printStackTrace();
        }
        // Get AlertHelmetMsg:
        thisMsg.setGuid(guidOfAlertMsg);
        thisMsg.setAid(inputData.getAid());
        thisMsg.setCid(inputData.getCid());
        thisMsg.setCid_url(inputData.getCid_url());
        thisMsg.setPic_data(detectPicFileName);
        thisMsg.setPic_name(inputData.getPic_name());
        thisMsg.setSrcpic_data(srcPicFileName);
        thisMsg.setSrcpic_name(inputData.getSrcpic_name());
        thisMsg.setStatus(inputData.getStatus());
        thisMsg.setTime_stamp(inputData.getTime_stamp()*1000);
        thisMsg.setAlert_type(1);
        // Get HelmetMsgData:
        thisData.setGuid(guidOfHelmetMsgData);
        thisData.setParent_id(guidOfAlertMsg);
        thisData.setAlert_flag(inputData.getData().getAlertFlag());
        thisData.setNum_of_head(inputData.getData().getNumOfHead());
        thisData.setTime_stamp(inputData.getData().getTimeStamp()*1000);
        // Get HeadInfo:
        for(HelmetAlertInfoInput.Heads.HeadDetail thisDetail : inputData.getData().getHeadInfo()){
            HeadInfo tmp = new HeadInfo();
            String guidOfHeadInfo = UniqueIDGenerator.getUUIDWithoutDash();
            tmp.setGuid(guidOfHeadInfo);
            tmp.setParent_id(guidOfHelmetMsgData);
            tmp.setNum_of_helmet(thisDetail.getNumOfHelmet());
            tmp.setX(thisDetail.getX());
            tmp.setY(thisDetail.getY());
            tmp.setHeight(thisDetail.getHeight());
            tmp.setWidth(thisDetail.getWidth());
            tmp.setColor(thisDetail.getColor());
            thisHeadInfoList.add(tmp);
        }
        // Save data to db:
        alertMsgMapper.insert(thisMsg);
        helmetMsgDataMapper.insert(thisData);
        for(HeadInfo thisInfo : thisHeadInfoList){
            headInfoMapper.insert(thisInfo);
        }
        // Construct output files:
        String deviceUrl = inputData.getCid_url();
        String deviceIP = "device_ip"; // rtsp://admin:admin@ 192.168.1.27:554/ch1/main/h264
        // Get IP using regular expression:
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(deviceUrl);
        if (matcher.find()) {
            deviceIP = matcher.group();
        }
        String pushImgBase64Str = "data:image/jpg;base64," + inputData.getPic_data();
        resultMsgToPush.setGuid(guidOfAlertMsg);
        resultMsgToPush.setAlert_img_base64(pushImgBase64Str);
        resultMsgToPush.setAlert_img_name(inputData.getPic_name());
        resultMsgToPush.setAlert_msg(detectPicFileName);
        resultMsgToPush.setAlert_type("Helmet Alert");
        resultMsgToPush.setTime_stamp(inputData.getTime_stamp()*1000);
        resultMsgToPush.setIp(deviceIP);
        //# First save all data to database,
        //# Then, build new img and create a new json file for pushing msg, in Base64 format.
        return resultMsgToPush;
    }

    /**
    * @Description: Push the data under a GUID
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    @Override
    public AIAlertPushMsg filterAlertPushData(String Guid) {
        AIAlertPushMsg msgResult = new AIAlertPushMsg();
        // Get data:
        AlertMsg thisMsg = alertMsgMapper.getAllByGuid(Guid).get(0);
        HelmetMsgData thisData = helmetMsgDataMapper.getAllByParentID(Guid).get(0);
        List<HeadInfo> thisHeadInfoList = headInfoMapper.getAllByParentID(thisData.getGuid());
        // Process image:
        String pBase64Img = "";
        if(processImageSwitch == true){
            ImageProcessor ip = new ImageProcessor();
            pBase64Img = ip.drawRectInImgFromBase64ForHelmet(thisMsg.getPic_data(), thisHeadInfoList);
        }else{
            pBase64Img = "data:image/jpg;base64," + thisMsg.getPic_data();
        }
        // Get useful data；
        msgResult.setGuid(Guid);
        msgResult.setAlert_img_base64(pBase64Img);
        msgResult.setAlert_img_name(thisMsg.getPic_name());
        msgResult.setAlert_msg(thisMsg.getPic_name());
        msgResult.setAlert_type("AI Alert");
        msgResult.setTime_stamp(thisMsg.getTime_stamp());
        return msgResult;
    }
}
