package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MainScreenSetting;
import com.barrett.smartsite.controller.MainScreenSettingController;
import com.barrett.smartsite.service.MainScreenSettingService;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
 * @create: 2019-10-07 10:38
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class MainScreenSettingController {
    @Autowired
    MainScreenSettingService mainScreenSettingService;

    @RequestMapping(value = {"/MainScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody MainScreenSetting mainScreenSetting) {
        System.out.println("Input data into <main_screen_setting>...");
        return this.mainScreenSettingService.insert(mainScreenSetting);
    }

    @RequestMapping(value = {"/MainScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody MainScreenSetting mainScreenSetting) {
        System.out.println("Update data of <main_screen_setting>...");
        return this.mainScreenSettingService.update(mainScreenSetting);
    }


    @RequestMapping(value = {"/MainScreenSettingLoop"}, method = {RequestMethod.POST})
    public int updateIsloop(@RequestParam("isloop") int isloop) {
        return this.mainScreenSettingService.updateIsloop(isloop);
    }

    @RequestMapping(value = {"/MainScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <main_screen_setting>...");
        return this.mainScreenSettingService.delete(id);
    }

    @RequestMapping(value = {"/MainScreenSetting"}, method = {RequestMethod.GET})
    public List<MainScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <main_screen_setting>...");
        return this.mainScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/MainScreenSettingAll"}, method = {RequestMethod.GET})
    public List<MainScreenSetting> getAll() {
        System.out.println("Query all data from <main_screen_setting>...");
        return this.mainScreenSettingService.getAll();
    }
}
