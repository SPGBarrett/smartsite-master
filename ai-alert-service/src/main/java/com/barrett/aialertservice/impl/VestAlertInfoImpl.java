package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.*;
import com.barrett.aialertservice.mapper.*;
import com.barrett.aialertservice.service.VestAlertInfoService;
import com.barrett.aialertservice.util.ImageProcessor;
import com.barrett.aialertservice.util.UniqueIDGenerator;
import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.barrett.aialertservice.vm.ClothesAlertInfoInput;
import com.barrett.aialertservice.vm.HelmetAlertInfoInput;
import com.barrett.aialertservice.vm.VestAlertInfoInput;
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
 * @create: 2020-05-16 11:30
 **/
@Service
public class VestAlertInfoImpl implements VestAlertInfoService {
    @Autowired(required = false)
    AlertMsgMapper alertMsgMapper;
    @Autowired(required = false)
    VestInfoMapper vestInfoMapper;
    @Autowired(required = false)
    VestMsgDataMapper vestMsgDataMapper;
    @Value("${ai.process.image.switch}")
    boolean processImageSwitch;
    @Value("${ai.detect.image.path}")
    String detectImageSavePath;


    @Override
    public AIAlertPushMsg insertVestAlertData(VestAlertInfoInput inputData) {
        AIAlertPushMsg resultMsgToPush = new AIAlertPushMsg();
        // extract data from input data:
        AlertMsg thisMsg = new AlertMsg();
        VestMsgData thisData = new VestMsgData();
        List<VestInfo> thisInfoList = new ArrayList<>();
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
        thisMsg.setAlert_type(2);
        // Get VestMsgData:
        thisData.setGuid(guidOfMsgData);
        thisData.setParent_id(guidOfAlertMsg);
        thisData.setAlert_flag(inputData.getData().getAlert_flag());
        thisData.setMessage("Vest Alert Reported");
        thisData.setNums_of_wrong_clothes(inputData.getData().getAlert_num());
        thisData.setTime_stamp(100000);
        // Get VestInfo:
        for(VestAlertInfoInput.Vests.VestDetail thisDetail : inputData.getData().getAlert_info()){
            VestInfo tmp = new VestInfo();
            String guidOfVestInfo = UniqueIDGenerator.getUUIDWithoutDash();
            tmp.setGuid(guidOfVestInfo);
            tmp.setParent_id(guidOfMsgData);
            tmp.setX(thisDetail.getX());
            tmp.setY(thisDetail.getY());
            tmp.setHeight(thisDetail.getHeight());
            tmp.setWidth(thisDetail.getWidth());
            tmp.setProbability(thisDetail.getConfidence());
            thisInfoList.add(tmp);
        }
        // Save data to db:
        alertMsgMapper.insert(thisMsg);
        vestMsgDataMapper.insert(thisData);
        for(VestInfo thisInfo : thisInfoList){
            vestInfoMapper.insert(thisInfo);
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
        resultMsgToPush.setAlert_type("Vest Alert");
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
        VestMsgData thisData = vestMsgDataMapper.getAllByParentID(Guid).get(0);
        List<VestInfo> thisInfoList = vestInfoMapper.getAllByParentID(thisData.getGuid());
        // Process image:
        String pBase64Img = "";
        if(processImageSwitch == true){
            ImageProcessor ip = new ImageProcessor();
            pBase64Img = ip.drawRectInImgFromBase64ForVest(thisMsg.getPic_data(), thisInfoList);
        }else{
            pBase64Img = "data:image/jpg;base64," + thisMsg.getPic_data();
        }
        // Get useful data；
        msgResult.setGuid(Guid);
        msgResult.setAlert_img_base64(pBase64Img);
        msgResult.setAlert_img_name(thisMsg.getPic_name());
        msgResult.setAlert_msg(thisMsg.getPic_name());
        msgResult.setAlert_type("Vest Alert");
        msgResult.setTime_stamp(thisMsg.getTime_stamp());
        return msgResult;
    }
}
