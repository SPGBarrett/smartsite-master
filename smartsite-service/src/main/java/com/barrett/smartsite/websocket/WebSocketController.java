package com.barrett.smartsite.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * @program: smartsite
 * @description: User controller for web socket
 * @author: Barrett
 * @create: 2019-10-14 11:23
 **/
@CrossOrigin
@Controller
@RequestMapping("/socket")
public class WebSocketController {
    //页面请求
    @GetMapping("/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    // Data pusher
    @ResponseBody
    @RequestMapping("/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "Success!";
    }

    // Json Data pusher
    @ResponseBody
    @RequestMapping("/pushJson/{cid}")
    public String pushJsonToWeb(@PathVariable String cid, @RequestBody String jsonMsg) {
        try {
            WebSocketServer.sendInfo(jsonMsg,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "Success!";
    }





    // Data pusher test
    @ResponseBody
    @RequestMapping("/pushTest/{cid}")
    public String pushTest(@PathVariable String cid) {
        try {
            WebSocketServer.sendInfo("工地产生预警消息！！！" ,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "Success!";
    }

    // Data pusher test
    @ResponseBody
    @RequestMapping("/pushUpdate/{cid}")
    public String pushUpdate(@PathVariable String cid) {
        try {
            WebSocketServer.sendInfo("UpdateParams" ,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "Success!";
    }

    // Data pusher test
    @ResponseBody
    @RequestMapping("/pushIndexUpdate/{cid}")
    public String pushIndexUpdate(@PathVariable String cid) {
        try {
            WebSocketServer.sendInfo("UpdateIndexParams" ,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "Success!";
    }
}
