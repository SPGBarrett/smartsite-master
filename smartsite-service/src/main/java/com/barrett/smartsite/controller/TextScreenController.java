package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.TextContent;
import com.barrett.smartsite.bean.TextScreenSetting;
import com.barrett.smartsite.controller.TextScreenController;
import com.barrett.smartsite.service.TextContentService;
import com.barrett.smartsite.service.TextScreenSettingService;
import com.barrett.smartsite.vm.TextScreenTotal;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 10:46
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"文字显示页面参数设置的接口"})
public class TextScreenController {
    @Autowired
    TextScreenSettingService textScreenSettingService;
    @Autowired
    TextContentService textContentService;

    @RequestMapping(value = {"/TextScreenTotal"}, method = {RequestMethod.GET})
    public List<TextScreenTotal> getTextScreenTotal() {
        System.out.println("Get all data required for Text Screen...");

        List<TextScreenTotal> resultList = new ArrayList<TextScreenTotal>();

        List<TextScreenSetting> setting = this.textScreenSettingService.getAll();

        List<TextContent> textContent = this.textContentService.getAll();

        TextScreenTotal returnParams = new TextScreenTotal();
        returnParams.setTotal_num(((TextScreenSetting) setting.get(0)).getTotal_num());
        returnParams.setLoop_data(((TextScreenSetting) setting.get(0)).getLoop_data());
        returnParams.setLoop_interval(((TextScreenSetting) setting.get(0)).getLoop_interval());
        returnParams.setTrans_style(((TextScreenSetting) setting.get(0)).getTrans_style());
        returnParams.setTextContents(textContent);

        resultList.clear();
        resultList.add(returnParams);

        return resultList;
    }
}
