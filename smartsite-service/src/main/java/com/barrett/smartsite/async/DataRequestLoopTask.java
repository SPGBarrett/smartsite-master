package com.barrett.smartsite.async;

import java.util.TimerTask;

import org.springframework.web.client.RestTemplate;

/**
 * @program: smartsite
 * @description: Async task for loop request
 * @author: Barrett
 * @create: 2019-10-07 09:46
 **/
public class DataRequestLoopTask
        extends TimerTask {
    public void restTemplateGetTest() {
        RestTemplate restTemplate = new RestTemplate();
        String dat = (String) restTemplate.getForObject("http://192.0.163.78:8896/SiteScreenParams/TextScreenTotal", String.class, new Object[0]);
        System.out.println(dat);
    }

    public void restTemplateGetTestCross() {
        RestTemplate restTemplate = new RestTemplate();
        String dat = (String) restTemplate.getForObject("http://192.0.163.78:8896/Index", String.class, new Object[0]);
        System.out.println(dat);
    }

    public void run() {
        System.out.println("Requesting Data From Server...");
        try {
            restTemplateGetTest();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            restTemplateGetTestCross();
        } catch (Exception e) {
            restTemplateGetTestCross();
        }
    }
}


