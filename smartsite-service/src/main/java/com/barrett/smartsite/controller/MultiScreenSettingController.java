package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MultiScreenSetting;
import com.barrett.smartsite.controller.MultiScreenSettingController;
import com.barrett.smartsite.service.MultiScreenSettingService;

import java.util.List;

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
 * @create: 2019-10-07 10:43
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class MultiScreenSettingController {
    @Autowired
    MultiScreenSettingService multiScreenSettingService;

    @RequestMapping(value = {"/MultiScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody MultiScreenSetting multiScreenSetting) {
        System.out.println("Input data into <multi_screen_setting>...");
        return this.multiScreenSettingService.insert(multiScreenSetting);
    }


    @RequestMapping(value = {"/MultiScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody MultiScreenSetting multiScreenSetting) {
        System.out.println("Update data of <multi_screen_setting>...");
        return this.multiScreenSettingService.update(multiScreenSetting);
    }


    @RequestMapping(value = {"/MultiScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <multi_screen_setting>...");
        return this.multiScreenSettingService.delete(id);
    }


    @RequestMapping(value = {"/MultiScreenSetting"}, method = {RequestMethod.GET})
    public List<MultiScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <multi_screen_setting>...");
        return this.multiScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/MultiScreenSettingAll"}, method = {RequestMethod.GET})
    public List<MultiScreenSetting> getAll() {
        System.out.println("Query all data from <multi_screen_setting>...");
        return this.multiScreenSettingService.getAll();
    }
}
