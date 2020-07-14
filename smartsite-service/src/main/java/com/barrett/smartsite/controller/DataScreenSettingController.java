package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.DataScreenSetting;
import com.barrett.smartsite.controller.DataScreenSettingController;
import com.barrett.smartsite.service.DataScreenSettingService;

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
 * @create: 2019-10-07 10:23
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class DataScreenSettingController {
    @Autowired
    DataScreenSettingService dataScreenSettingService;

    @RequestMapping(value = {"/DataScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody DataScreenSetting dataScreenSetting) {
        System.out.println("Inserting data into database...");
        return this.dataScreenSettingService.insert(dataScreenSetting);
    }


    @RequestMapping(value = {"/DataScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody DataScreenSetting dataScreenSetting) {
        System.out.println("Updating data in database...");
        return this.dataScreenSettingService.update(dataScreenSetting);
    }


    @RequestMapping(value = {"/DataScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Deleting data from database...");
        return this.dataScreenSettingService.delete(id);
    }


    @RequestMapping(value = {"/DataScreenSetting"}, method = {RequestMethod.GET})
    public List<DataScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from database by Id...");
        return this.dataScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/DataScreenSettingAll"}, method = {RequestMethod.GET})
    public List<DataScreenSetting> getAll() {
        System.out.println("Query all data from database...");
        return this.dataScreenSettingService.getAll();
    }
}
