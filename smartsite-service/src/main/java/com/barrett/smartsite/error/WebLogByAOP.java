package com.barrett.smartsite.error;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import com.barrett.logger.LoggerService;
import com.barrett.logger.OperateLogRequestBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 10:59
 **/
@Aspect
@Component
public class WebLogByAOP {
    private final Logger logger = LoggerFactory.getLogger(WebLogByAOP.class);

    @Autowired
    private LoggerService loggerService;

    // Specify point cut:
    @Pointcut("execution(public * com.barrett.smartsite.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        this.logger.info("URL : " + request.getRequestURL().toString());
        this.logger.info("HTTP_METHOD : " + request.getMethod());
        this.logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        this.logger.error("test error log outside");
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            this.logger.info("name:{},value:{}", name, request.getParameter(name));
            this.logger.error("test error log");
        }
        // Write log to server using http request:
        Thread logThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String operType = request.getMethod();
                    String operDesc = request.getRequestURL().toString();
                    OperateLogRequestBody operateLogRequestBody = loggerService.constructDefaultOperateLogData("管理员", "spc-screen", operType, operDesc);
                    loggerService.writeLogToServer(operateLogRequestBody);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        });
        logThread.start();

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        this.logger.info("Response: " + ret);
    }
}
