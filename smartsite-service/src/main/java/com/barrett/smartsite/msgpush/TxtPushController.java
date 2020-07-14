package com.barrett.smartsite.msgpush;

import com.barrett.smartsite.bean.MapScreenSetting;
import org.springframework.web.bind.annotation.*;

/**
 * @program: smartsite
 * @description: User controller for pushing msg
 * @author: Barrett
 * @create: 2019-10-14 15:32
 **/
@CrossOrigin
@RestController
@RequestMapping({"/txtPush"})
public class TxtPushController {

    @RequestMapping(value = {"/singlePush"}, method = {RequestMethod.POST})
    public int push(String msg) {
        System.out.println("Push msg...");
        TxtMsgPusher.testSendValidSMSCode();
        //testSendSMSCode();
        //testSendVoiceSMSCode();
        //testSendTemplateSMS();
        return 0;
    }

    @RequestMapping(value = {"/singlePushTest"}, method = {RequestMethod.GET})
    public int get() {
        System.out.println("Push msg...");
        TxtMsgPusher.testSendSMSCode();
        return 0;
    }
}
