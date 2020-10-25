package com.barrett.assistservice;

import com.barrett.assistservice.http.CallPMApis;
import com.barrett.assistservice.util.SpringUtils;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
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

    // Setting up hystrix route configuration:
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
