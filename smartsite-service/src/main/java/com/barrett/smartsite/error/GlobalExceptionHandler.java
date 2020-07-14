package com.barrett.smartsite.error;

import java.util.HashMap;
import java.util.Map;

import com.barrett.logger.ExceptionLogRequestBody;
import com.barrett.logger.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 10:53
 **/
@ControllerAdvice(basePackages = {"com.barrett.smartsite.controller"})
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private LoggerService loggerService;

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception ex) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        String msg = ex.getMessage();
        mapResult.put("Info: ", "Exception caught by Global Handler!");
        mapResult.put("ErrorCode: ", "500");
        mapResult.put("ErrorMsg: ", msg);
        logger.info(mapResult.toString());
        // Write log to server using http request:
        Thread logThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExceptionLogRequestBody exceptionLogRequestBody = loggerService.constructDefaultExceptionLogData("CurrentUser", "Global Exception Handler", "N/A", msg);
                    loggerService.writeExceptionLogToServer(exceptionLogRequestBody);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        });
        logThread.start();
        return mapResult;
    }

    public ModelAndView exceptionPageHandler() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("xxx");
        return mav;
    }
}
