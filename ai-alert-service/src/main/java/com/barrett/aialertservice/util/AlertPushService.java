package com.barrett.aialertservice.util;

import com.barrett.aialertservice.vm.AIAlertPushMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: smartsite-master
 * @description: Pushing service for alerts
 * @author: Barrett
 * @create: 2020-05-07 13:51
 **/
@Service
public class AlertPushService {
    @Value("${alert.push.helmet.url}")
    private String PUSH_HELMET_URL;
    @Value("${alert.push.clothes.url}")
    private String PUSH_CLOTHES_URL;

    /**
    * @Description: Push msg to alert manage server
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    public String pushHelmetAlertMsg(AIAlertPushMsg pushMsg){

        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        try{
            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            //body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("guid", pushMsg.getGuid());
            requestBody.put("alert_type", pushMsg.getAlert_type());
            requestBody.put("alert_msg", pushMsg.getAlert_msg());
            requestBody.put("alert_img_name", pushMsg.getAlert_img_name());
            requestBody.put("alert_img_base64", pushMsg.getAlert_img_base64());
            requestBody.put("time_stamp", pushMsg.getTime_stamp());
            requestBody.put("ip", pushMsg.getIp());
            //HttpEntity
            HttpEntity requestEntity = new HttpEntity(requestBody, requestHeader);
            //ResponseEntity<String> responseEntity = restTemplate.postForEntity(PUSH_URL, "{pushMsg: 2313}", String.class);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(PUSH_HELMET_URL, requestEntity, String.class);
            responseBody = responseEntity.getBody();
            System.out.println(responseBody);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return responseBody;
    }

    /**
     * @Description: Push msg to alert manage server
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public String pushClothesAlertMsg(AIAlertPushMsg pushMsg){

        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        try{
            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            //body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("guid", pushMsg.getGuid());
            requestBody.put("alert_type", pushMsg.getAlert_type());
            requestBody.put("alert_msg", pushMsg.getAlert_msg());
            requestBody.put("alert_img_name", pushMsg.getAlert_img_name());
            requestBody.put("alert_img_base64", pushMsg.getAlert_img_base64());
            requestBody.put("time_stamp", pushMsg.getTime_stamp());
            requestBody.put("ip", pushMsg.getIp());
            //HttpEntity
            HttpEntity requestEntity = new HttpEntity(requestBody, requestHeader);
            //ResponseEntity<String> responseEntity = restTemplate.postForEntity(PUSH_URL, "{pushMsg: 2313}", String.class);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(PUSH_CLOTHES_URL, requestEntity, String.class);
            responseBody = responseEntity.getBody();
            System.out.println(responseBody);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return responseBody;
    }
}
