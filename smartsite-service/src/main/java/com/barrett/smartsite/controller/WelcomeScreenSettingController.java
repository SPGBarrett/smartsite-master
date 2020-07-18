package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.WelcomeScreenSetting;
import com.barrett.smartsite.controller.WelcomeScreenSettingController;
import com.barrett.smartsite.service.WelcomeScreenSettingService;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 10:48
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"欢迎页面参数设置的接口"})
public class WelcomeScreenSettingController {
    @Autowired
    WelcomeScreenSettingService welcomeScreenSettingService;

    @RequestMapping(value = {"/WelcomeScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody WelcomeScreenSetting welcomeScreenSetting) {
        System.out.println("Input data into <welcome_screen_setting>...");
        return this.welcomeScreenSettingService.insert(welcomeScreenSetting);
    }

    @RequestMapping(value = {"/WelcomeScreenSetting"}, method = {RequestMethod.POST})
    public int updateEnvironment(@RequestBody WelcomeScreenSetting welcomeScreenSetting) {
        System.out.println("Update data of <welcome_screen_setting>...");
        return this.welcomeScreenSettingService.update(welcomeScreenSetting);
    }

    @RequestMapping(value = {"/WelcomeScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <welcome_screen_setting>...");
        return this.welcomeScreenSettingService.delete(id);
    }

    @RequestMapping(value = {"/WelcomeScreenSetting"}, method = {RequestMethod.GET})
    public List<WelcomeScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <welcome_screen_setting>...");
        return this.welcomeScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/WelcomeScreenSettingAll"}, method = {RequestMethod.GET})
    public List<WelcomeScreenSetting> getAll() {
        System.out.println("Query all data from <welcome_screen_setting>...");
        return this.welcomeScreenSettingService.getAll();
    }
}
