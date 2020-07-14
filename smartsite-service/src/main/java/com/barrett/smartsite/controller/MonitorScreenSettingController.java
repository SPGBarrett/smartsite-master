package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MonitorScreenSetting;
import com.barrett.smartsite.controller.MonitorScreenSettingController;
import com.barrett.smartsite.service.MonitorScreenSettingService;

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
 * @create: 2019-10-07 10:41
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class MonitorScreenSettingController {
    @Autowired
    MonitorScreenSettingService monitorScreenSettingService;

    @RequestMapping(value = {"/MonitorScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody MonitorScreenSetting monitorScreenSetting) {
        System.out.println("Input data into <monitor_screen_setting>...");
        return this.monitorScreenSettingService.insert(monitorScreenSetting);
    }

    @RequestMapping(value = {"/MonitorScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody MonitorScreenSetting monitorScreenSetting) {
        System.out.println("Update data of <monitor_screen_setting>...");
        return this.monitorScreenSettingService.update(monitorScreenSetting);
    }

    @RequestMapping(value = {"/MonitorScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <monitor_screen_setting>...");
        return this.monitorScreenSettingService.delete(id);
    }

    @RequestMapping(value = {"/MonitorScreenSetting"}, method = {RequestMethod.GET})
    public List<MonitorScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <monitor_screen_setting>...");
        return this.monitorScreenSettingService.getAllById(id);
    }

    @RequestMapping(value = {"/MonitorScreenSettingAll"}, method = {RequestMethod.GET})
    public List<MonitorScreenSetting> getAll() {
        System.out.println("Query all data from <monitor_screen_setting>...");
        return this.monitorScreenSettingService.getAll();
    }
}
