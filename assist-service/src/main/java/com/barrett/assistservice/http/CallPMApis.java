package com.barrett.assistservice.http;

import com.barrett.assistservice.vm.PMInputBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    //private final static String key = "a58a3c0d584eda37abb2d09a1336f9fa";//桩桩key
    private final static String key = "1af7994a0bb11262ad6747aea13c0569";//本地化key
    private final static String PROJECT_NUM = "1000409";//本地化key
    private final static String CONO = "1000113";//本地化企业ID
    private final static int E_TYPE = 1;// 加密方式 1 AES 2 BASE64
    private final static int D_TYPE = 1;// 开发者类型:1企业开发者 2平台开发者 默认为空代表企业开发者
    // Get all the params to properties file:
    String url = "http://223.84.191.228:8380/openapi/openapi.do";

    public String allWorkerExitCall(int[] worker_ids){
        int iType = 7014;
        // Define input bean for PM Api:
        PMInputBean pmInputBean = new PMInputBean();
        // Get data:
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(currentTime);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectNum", PROJECT_NUM);
        jsonObject.put("workerIds", worker_ids);
        String strData = jsonObject.toJSONString();;
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
        // Prepare input bean:
        pmInputBean.setItype(iType);
        pmInputBean.setTime(time);
        pmInputBean.setCono(CONO);
        pmInputBean.setData(dataToUse);
        pmInputBean.setEtype(E_TYPE);
        pmInputBean.setSign(sign);
        pmInputBean.setDtype(D_TYPE);
        pmInputBean.setDkey(null);
        // Invoke Http request:
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, pmInputBean, String.class);
        String responseBody = responseEntity.getBody();
        return responseBody;
    }


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
