package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MapScreenSetting;
import com.barrett.smartsite.controller.MapScreenSettingController;
import com.barrett.smartsite.service.MapScreenSettingService;

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
 * @create: 2019-10-07 10:40
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"工地一张图参数设置接口"})
public class MapScreenSettingController {
    @Autowired
    MapScreenSettingService mapScreenSettingService;

    @RequestMapping(value = {"/MapScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody MapScreenSetting mapScreenSetting) {
        System.out.println("Input data into <map_screen_setting>...");
        return this.mapScreenSettingService.insert(mapScreenSetting);
    }


    @RequestMapping(value = {"/MapScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody MapScreenSetting mapScreenSetting) {
        System.out.println("Update data of <map_screen_setting>...");
        return this.mapScreenSettingService.update(mapScreenSetting);
    }


    @RequestMapping(value = {"/MapScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <map_screen_setting>...");
        return this.mapScreenSettingService.delete(id);
    }


    @RequestMapping(value = {"/MapScreenSetting"}, method = {RequestMethod.GET})
    public List<MapScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <map_screen_setting>...");
        return this.mapScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/MapScreenSettingAll"}, method = {RequestMethod.GET})
    public List<MapScreenSetting> getAll() {
        System.out.println("Query all data from <map_screen_setting>...");
        return this.mapScreenSettingService.getAll();
    }
}
