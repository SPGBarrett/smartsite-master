package com.barrett.eurekaservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

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
    public void listen(EurekaInstanceCanceledEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("时间：" + sdf.format(event.getTimestamp()) + " ");
        msgBuilder.append("服务: " + event.getAppName() + " ");
        msgBuilder.append("ID: " + event.getServerId() + " ");
        msgBuilder.append("已下线" + "\n");
        String msg = msgBuilder.toString();
        logger.info(msg);
        // Send Email to DevOps:
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(msg);
            }
        }).start();
    }

    // Service registered event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRegisteredEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("时间：" + sdf.format(event.getTimestamp()) + " ");
        msgBuilder.append("服务: " + event.getInstanceInfo() + " ");
        msgBuilder.append("Class: " + event.getClass() + " ");
        msgBuilder.append("已注册" + "\n");
        String msg = msgBuilder.toString();
        logger.info(msg);
    }

    // Service renewed (heartbeat) event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRenewedEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("时间：" + sdf.format(event.getTimestamp()) + " ");
        msgBuilder.append("服务: " + event.getAppName() + " ");
        msgBuilder.append("ID: " + event.getServerId() + " ");
        msgBuilder.append("心跳" + "\n");
        String msg = msgBuilder.toString();
        logger.info(msg);
    }

    // Eureka service launched event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaRegistryAvailableEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("时间：" + sdf.format(event.getTimestamp()) + " ");
        msgBuilder.append("服务: " + event.getSource() + " ");
        msgBuilder.append("Class: " + event.getClass() + " ");
        msgBuilder.append("开启服务" + "\n");
        String msg = msgBuilder.toString();
        logger.info(msg);
    }

    // Eureka service started event:
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaServerStartedEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("时间：" + sdf.format(event.getTimestamp()) + " ");
        msgBuilder.append("服务: " + event.getSource() + " ");
        msgBuilder.append("Class: " + event.getClass() + " ");
        msgBuilder.append("服务启动了!" + "\n");
        String msg = msgBuilder.toString();
        logger.info(msg);
    }

    private void send(String msg) {
        //用于封装邮件信息的实例
        SimpleMailMessage smm = new SimpleMailMessage();
        //由谁来发送邮件, 这里的名字必须和发件邮箱一致，否则报错
        smm.setFrom("spgbarrett@163.com");
        //smm.setFrom("823529172@qq.com");
        //邮件主题
        smm.setSubject("服务下线通知");
        //邮件内容
        smm.setText(msg);
        //接受邮件
        smm.setTo(new String[]{"spgbarrett@163.com", "823529172@qq.com"});
        try {
            jms.send(smm);
        } catch (Exception e) {
            logger.info(msg + "错误", e);
        }
    }
}
