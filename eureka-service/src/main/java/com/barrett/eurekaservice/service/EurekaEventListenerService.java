package com.barrett.eurekaservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @program: smartsite-master
 * @description: Implement all Eureka related events
 * @author: Barrett
 * @create: 2020-07-13 19:06
 **/
@Component
public class EurekaEventListenerService {
    // Define logger:
    private final static Logger logger = LoggerFactory.getLogger(EurekaEventListenerService.class);

    @Autowired
    private JavaMailSender jms;

    // **********Define and implement event listeners: ******************
    // Service cancelled event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceCanceledEvent event){
        String msg="服务"+event.getAppName()+"\n"+event.getServerId()+"已下线";
        logger.info(msg);
        send(msg);
    }

    // Service registered event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRegisteredEvent event){
        String msg="服务"+event.getTimestamp()+"\n"+event.getClass()+"已注册";
        logger.info(msg);
        send(msg);
    }

    // Service renewed (heartbeat) event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRenewedEvent event){
        String msg="服务"+event.getAppName()+"\n"+event.getServerId()+"心跳";
        logger.info(msg);
    }

    // Eureka service launched event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaRegistryAvailableEvent event){
        String msg="服务"+event.getTimestamp()+"\n"+event.getClass()+"开启服务";
        logger.info(msg);
    }

    // Eureka service started event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaServerStartedEvent event){
        String msg="服务"+event.getTimestamp()+"\n"+event.getClass()+"服务启动了";
        logger.info(msg);
    }


    private  void send(String msg){
        //用于封装邮件信息的实例
        SimpleMailMessage smm=new SimpleMailMessage();
        //由谁来发送邮件, 这里的名字必须和发件邮箱一致，否则报错
        smm.setFrom("spgbarrett@163.com");
        //邮件主题
        smm.setSubject("Eureka-Server");
        //邮件内容
        smm.setText(msg);
        //接受邮件
        smm.setTo(new String[]{"823529172@qq.com"});
        try {
            jms.send(smm);
        } catch (Exception e) {
            logger.info(msg+"错误",e);
        }
    }



}
