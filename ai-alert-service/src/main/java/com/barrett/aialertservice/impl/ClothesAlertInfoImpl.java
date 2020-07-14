package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.*;
import com.barrett.aialertservice.mapper.*;
import com.barrett.aialertservice.service.ClothesAlertInfoService;
import com.barrett.aialertservice.util.ImageProcessor;
import com.barrett.aialertservice.util.UniqueIDGenerator;
import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.ClothesAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 11:28
 **/
@Service
public class ClothesAlertInfoImpl implements ClothesAlertInfoService {
    @Autowired(required = false)
    AlertMsgMapper alertMsgMapper;
    @Autowired(required = false)
    ClothesInfoMapper clothesInfoMapper;
    @Autowired(required = false)
    ClothesMsgDataMapper clothesMsgDataMapper;
    @Value("${ai.process.image.switch}")
    boolean processImageSwitch;
    @Value("${ai.detect.image.path}")
    String detectImageSavePath;


    @Override
    public AIAlertPushMsg insertClothesAlertData(ClothesAlertInfoInput inputData) {
        AIAlertPushMsg resultMsgToPush = new AIAlertPushMsg();
        // extract data from input data:
        AlertMsg thisMsg = new AlertMsg();
        ClothesMsgData thisData = new ClothesMsgData();
        List<ClothesInfo> thisInfoList = new ArrayList<>();
        // Get GUID:
        String guidOfAlertMsg = UniqueIDGenerator.getUUIDWithoutDash();
        String guidOfMsgData = UniqueIDGenerator.getUUIDWithoutDash();
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
        // Get AlertMsg:
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
        // Get ClothesMsgData:
        thisData.setGuid(guidOfMsgData);
        thisData.setParent_id(guidOfAlertMsg);
        thisData.setAlert_flag(inputData.getData().getAlert_flag());
        thisData.setReport_num(inputData.getData().getReport_num());
        thisData.setTime_stamp(1000000);
        thisMsg.setAlert_type(3);
        // Get BodyInfo:
        for(ClothesAlertInfoInput.Clothes.ClothesDetail thisDetail : inputData.getData().getBody_info()){
            ClothesInfo tmp = new ClothesInfo();
            String guidOfClothesInfo = UniqueIDGenerator.getUUIDWithoutDash();
            tmp.setGuid(guidOfClothesInfo);
            tmp.setParent_id(guidOfMsgData);
            tmp.setX(thisDetail.getX());
            tmp.setY(thisDetail.getY());
            tmp.setHeight(thisDetail.getHeight());
            tmp.setWidth(thisDetail.getWidth());
            thisInfoList.add(tmp);
        }
        // Save data to db:
        alertMsgMapper.insert(thisMsg);
        clothesMsgDataMapper.insert(thisData);
        for(ClothesInfo thisInfo : thisInfoList){
            clothesInfoMapper.insert(thisInfo);
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
        resultMsgToPush.setAlert_type("Clothes Alert");
        resultMsgToPush.setTime_stamp(inputData.getTime_stamp()*1000);
        resultMsgToPush.setIp(deviceIP);
        //# First save all data to database,
        //# Then, build new img and create a new json file for pushing msg, in Base64 format.
        return resultMsgToPush;
    }

    @Override
    public AIAlertPushMsg filterAlertPushData(String Guid) {
        AIAlertPushMsg msgResult = new AIAlertPushMsg();
        // Get data:
        AlertMsg thisMsg = alertMsgMapper.getAllByGuid(Guid).get(0);
        ClothesMsgData thisData = clothesMsgDataMapper.getAllByParentID(Guid).get(0);
        List<ClothesInfo> thisInfoList = clothesInfoMapper.getAllByParentID(thisData.getGuid());
        // Process image:
        String pBase64Img = "";
        if(processImageSwitch == true){
            ImageProcessor ip = new ImageProcessor();
            pBase64Img = ip.drawRectInImgFromBase64ForClothes(thisMsg.getPic_data(), thisInfoList);
        }else{
            pBase64Img = "data:image/jpg;base64," + thisMsg.getPic_data();
        }
        // Get useful data；
        msgResult.setGuid(Guid);
        msgResult.setAlert_img_base64(pBase64Img);
        msgResult.setAlert_img_name(thisMsg.getPic_name());
        msgResult.setAlert_msg(thisMsg.getPic_name());
        msgResult.setAlert_type("Clothes Alert");
        msgResult.setTime_stamp(thisMsg.getTime_stamp());
        return msgResult;
    }
}
