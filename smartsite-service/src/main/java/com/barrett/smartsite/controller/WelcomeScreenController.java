package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.bean.WelcomeScreenSetting;
import com.barrett.smartsite.controller.WelcomeScreenController;
import com.barrett.smartsite.service.MultiFilesService;
import com.barrett.smartsite.service.WelcomeScreenSettingService;
import com.barrett.smartsite.vm.WelcomeScreenTotal;

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
 * @create: 2019-10-07 10:47
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"获取欢迎页面信息的接口"})
public class WelcomeScreenController {
    @Autowired
    WelcomeScreenSettingService welcomeScreenSettingService;
    @Autowired
    MultiFilesService multiFilesService;

    @RequestMapping(value = {"/WelcomeScreenTotal"}, method = {RequestMethod.GET})
    public List<WelcomeScreenTotal> getWelcomeScreenTotal() {
        System.out.println("Query all data for Welcome Screen...");

        List<WelcomeScreenTotal> resultList = new ArrayList<WelcomeScreenTotal>();

        List<WelcomeScreenSetting> settings = this.welcomeScreenSettingService.getAll();

        WelcomeScreenTotal tempData = new WelcomeScreenTotal();
        tempData.setBg_color(((WelcomeScreenSetting) settings.get(0)).getBg_color());
        tempData.setWelcome_text(((WelcomeScreenSetting) settings.get(0)).getWelcome_text());
        tempData.setPic_path(((WelcomeScreenSetting) settings.get(0)).getPic_path());
        tempData.setTxt_pos_x(((WelcomeScreenSetting) settings.get(0)).getTxt_pos_x());
        tempData.setTxt_pos_y(((WelcomeScreenSetting) settings.get(0)).getTxt_pos_y());
        tempData.setTxt_font(((WelcomeScreenSetting) settings.get(0)).getTxt_font());
        tempData.setTxt_size(((WelcomeScreenSetting) settings.get(0)).getTxt_size());

        if (((WelcomeScreenSetting) settings.get(0)).getBg_mode() == 1) {
            List<MultiFiles> tempFileList = this.multiFilesService.getAllByParent("welcome");
            tempData.setUrl(((MultiFiles) tempFileList.get(0)).getUrl_link());
        } else {
            tempData.setUrl("");
        }

        resultList.add(tempData);

        return resultList;
    }
}
