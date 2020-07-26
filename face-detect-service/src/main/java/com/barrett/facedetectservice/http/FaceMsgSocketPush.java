package com.barrett.facedetectservice.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: smartsite-master
 * @description: http request for face msg
 * @author: Barrett
 * @create: 2020-06-20 16:14
 **/
@Service
public class FaceMsgSocketPush {
    @Value("${worker.info.push.url}")
    private String WORKER_INFO_PUSH_URL;

    /**
     * @Description: Push msg to alert manage server
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public String pushWorkerMsg(String base64Img, String cardNo, String workerName, String workerPosition, String workerDepartment, String workerInstitute, String deviceType, String workerInSite) {
        String responseBody = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            //body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("base64Img", base64Img);
            requestBody.put("cardNo", cardNo);
            requestBody.put("workerName", workerName);
            requestBody.put("workerPosition", workerPosition);
            requestBody.put("workerDepartment", workerDepartment);
            requestBody.put("workerInstitute", workerInstitute);
            requestBody.put("time", new Date());
            requestBody.put("workerInSite", workerInSite);
            requestBody.put("type", "人员进出场");
            requestBody.put("deviceType", deviceType); //1=进, 2=出
            //HttpEntity
            HttpEntity requestEntity = new HttpEntity(requestBody, requestHeader);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(WORKER_INFO_PUSH_URL, requestEntity, String.class);
            responseBody = responseEntity.getBody();
            System.out.println(responseBody);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return responseBody;
    }

    /**
     * @Description: Async method
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void pushWorkerMsgAsync(String base64Img, String cardNo, String workerName, String workerPosition, String workerDepartment, String workerInstitute, String deviceType, String workerInSite) {
        Thread pushThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pushWorkerMsg(base64Img, cardNo, workerName, workerPosition, workerDepartment, workerInstitute, deviceType, workerInSite);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        pushThread.start();
    }
}
