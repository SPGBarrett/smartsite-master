package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.TextScreenSetting;
import com.barrett.smartsite.controller.TextScreenSettingController;
import com.barrett.smartsite.service.TextScreenSettingService;

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
 * @create: 2019-10-07 10:47
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class TextScreenSettingController {
    @Autowired
    TextScreenSettingService textScreenSettingService;

    @RequestMapping(value = {"/TextScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody TextScreenSetting textScreenSetting) {
        System.out.println("Input data into <text_screen_setting>...");
        return this.textScreenSettingService.insert(textScreenSetting);
    }


    @RequestMapping(value = {"/TextScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody TextScreenSetting textScreenSetting) {
        System.out.println("Update data of <text_screen_setting>...");
        return this.textScreenSettingService.update(textScreenSetting);
    }


    @RequestMapping(value = {"/TextScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <text_screen_setting>...");
        return this.textScreenSettingService.delete(id);
    }


    @RequestMapping(value = {"/TextScreenSetting"}, method = {RequestMethod.GET})
    public List<TextScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <text_screen_setting>...");
        return this.textScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/TextScreenSettingAll"}, method = {RequestMethod.GET})
    public List<TextScreenSetting> getAll() {
        System.out.println("Query all data from <text_screen_setting>...");
        return this.textScreenSettingService.getAll();
    }
}
