package com.barrett.assistservice.http;

import com.barrett.assistservice.bean.PMWorkInfoForMapping;
import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.service.PMWorkerInfoService;
import com.barrett.assistservice.vm.PMInputBean;
import com.hs2e.common.mapper.JsonMapper;
import com.hs2e.common.reflect.BeanUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.dofuntech.spconst.utils.AesEncryptUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @program: assist-service
 * @description: calling pingming apis
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
@Service
public class CallPMApis {
    // private final static String key = "4bd9a2f07b518df4c1e785b1fa17c534";
    // private final static String key = "a58a3c0d584eda37abb2d09a1336f9fa";//桩桩key
    @Value("${pm.app.key}")
    private String key;//本地化key
    @Value("${pm.project.number}")
    private String PROJECT_NUM;//本地化key
    @Value("${pm.company.number}")
    private String CONO;//本地化企业ID
    // Get all the params to properties file:
    @Value("${pm.api.url}")
    private String url;
    @Value("${pm.api.call.modify.time}")
    private String modifyTime;
    @Autowired
    private PMWorkerInfoService pmWorkerInfoService;

    private final static String E_TYPE = "1";// 加密方式 1 AES 2 BASE64
    private final static String D_TYPE = "1";// 开发者类型:1企业开发者 2平台开发者 默认为空代表企业开发者


    // Call PM api and exit all worker in the param worker id list:
    public String allWorkerExitCall(int[] worker_ids){
        // Define API type:
        String iType = "7014";
        // Get data:
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectNum", PROJECT_NUM);
        jsonObject.put("workerIds", worker_ids);
        String strData = jsonObject.toJSONString();
        try{
            return pmHttpRequest(iType, strData);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    // Get all worker information:
    public String getAllWorkerInfo(){
        // Define API type:
        String iType = "7004";
        // Get data:
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("modifyTime", modifyTime);//20190106000000
        jsonObject.put("size", 1000);
        jsonObject.put("page", 1);
        String strData = jsonObject.toJSONString();
        try{
            return pmHttpRequest(iType, strData);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    // Get worker information by page number and page size, size cannot exceed 1000:
    public String getWorkerInfoByPage(int pageSize, int pageNumber){
        // Define API type:
        String iType = "7004";
        // Get data:
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("modifyTime", modifyTime);//20190106000000
        jsonObject.put("size", pageSize);
        jsonObject.put("page", pageNumber);
        String strData = jsonObject.toJSONString();
        try{
            return pmHttpRequest(iType, strData);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    // Recurrent function to get all data from PM by page number:
    public void getWorkerDataAndSave(int startingPage){
        // Get data:
        String requestResult = getWorkerInfoByPage(1000, startingPage);
        JSONObject jsonDetail = JSONObject.parseObject(requestResult);
        JSONArray array = jsonDetail.getJSONArray("data");
        if(array.size() <= 0){
            return;
        }
        List<?> tmp = JsonMapper.fromJsonForMapList(array.toJSONString());
        // Handling different variable name issue:
        List<PMWorkInfoForMapping> pmWorkerInfoForMappingList = BeanUtils.copyToCollection(tmp, PMWorkInfoForMapping.class);
        // Save to db:
        for(PMWorkInfoForMapping thisInfo : pmWorkerInfoForMappingList){
            PMWorkerInfo pmWorkerInfo = new PMWorkerInfo();
            pmWorkerInfo.setWorker_id(thisInfo.getWorkerId());
            pmWorkerInfo.setName(thisInfo.getName());
            pmWorkerInfo.setId_card(thisInfo.getIdcard());
            pmWorkerInfo.setTime_card(thisInfo.getTimecard());
            pmWorkerInfo.setProject_id(thisInfo.getProjectId());
            pmWorkerInfo.setCooperator_id(thisInfo.getCooperatorId());
            pmWorkerInfo.setGroup_id(thisInfo.getGroupId());
            pmWorkerInfo.setProfessional_id(thisInfo.getProfessionId());
            pmWorkerInfo.setCooperator_name(thisInfo.getCooperatorName());
            pmWorkerInfo.setGroup_name(thisInfo.getGroupName());
            pmWorkerInfo.setProfessional_name(thisInfo.getProfessionName());
            pmWorkerInfo.setStatus(thisInfo.getStatus());
            pmWorkerInfo.setModify_time(thisInfo.getModifyTime());
            pmWorkerInfoService.insert(pmWorkerInfo);
        }
        getWorkerDataAndSave(startingPage+1);
    }

    // Refresh worker info:
    public void refreshWorkerInfo(){
        try{
            pmWorkerInfoService.deleteAll();
            getWorkerDataAndSave(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // Invoke PM API according to PM's rules:
    private String pmHttpRequest(String iType, String strData){
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(currentTime);
        String sData = "";
        String dataToUse = "";
        String sign = "";
        try
        {
            sData = URLEncoder.encode(strData, "UTF-8");
            dataToUse = AesEncryptUtils.aesEncrypt(key, sData);
            sign = md5Encrypt(iType + time + CONO + dataToUse + key);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        // Send request using http form data:
        // Prepare input bean:
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<String, String>();
        requestParams.add("itype",iType);
        requestParams.add("cono",CONO);
        requestParams.add("time",time);
        requestParams.add("data",dataToUse);
        requestParams.add("sign",sign);
        requestParams.add("etype",E_TYPE);
        requestParams.add("dtype",D_TYPE);
        requestParams.add("dkey",null);
        // Invoke Http request, and send request params using form-urlencoded:
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(requestParams, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        return responseBody;
    }


    // Tools: MD5 Encoding:
    public static String md5Encrypt(String originalString)
    {
        if (isEmpty(originalString))
            throw new IllegalArgumentException("this originalString must not be empty");

        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(originalString.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        final byte[] encrypted = messageDigest.digest();
        return toHexString(encrypted);
    }

    // Tools: Hex Encoding:
    public static String toHexString(byte[] byteArray)
    {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++)
        {
            if ((byteArray[i] & 0xff) < 0x10)
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    // Check if a String is empty:
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
