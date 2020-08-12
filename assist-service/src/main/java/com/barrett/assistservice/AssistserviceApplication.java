package com.barrett.assistservice;

import com.barrett.assistservice.http.CallPMApis;
import com.barrett.assistservice.util.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableDiscoveryClient
public class AssistserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistserviceApplication.class, args);
        // Get component class injected to Spring container:
        ApplicationContext context = SpringUtils.getApplicationContext();
        CallPMApis callPMApis = context.getBean(CallPMApis.class);// 注意是Service，不是ServiceImpl
        // Start a timer task to query new worker info from PingMing:
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask running..." + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                callPMApis.refreshWorkerInfo();
                System.out.println("WorkerInfo Updated...");
            }
        },1000,14400000);//延时1s，之后每隔3H运行一次
    }

}
