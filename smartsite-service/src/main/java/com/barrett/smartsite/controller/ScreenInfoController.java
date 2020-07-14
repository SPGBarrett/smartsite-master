package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.ScreenInfo;
import com.barrett.smartsite.controller.ScreenInfoController;
import com.barrett.smartsite.service.ScreenInfoService;

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
public class ScreenInfoController {
    @Autowired
    ScreenInfoService screenInfoService;

    @RequestMapping(value = {"/ScreenInfo"}, method = {RequestMethod.POST})
    public int insert(@RequestBody ScreenInfo screenInfo) {
        System.out.println("Input data into <screen_info>...");
        return this.screenInfoService.insert(screenInfo);
    }

    @RequestMapping(value = {"/ScreenInfo"}, method = {RequestMethod.PUT})
    public int update(@RequestBody ScreenInfo screenInfo) {
        System.out.println("Update data of <screen_info>...");
        return this.screenInfoService.update(screenInfo);
    }

    @RequestMapping(value = {"/ScreenInfo"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <screen_info>...");
        return this.screenInfoService.delete(id);
    }

    @RequestMapping(value = {"/ScreenInfo"}, method = {RequestMethod.GET})
    public List<ScreenInfo> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <screen_info>...");
        return this.screenInfoService.getAllById(id);
    }


    @RequestMapping(value = {"/ScreenInfoAll"}, method = {RequestMethod.GET})
    public List<ScreenInfo> getAll() {
        System.out.println("Query all data from <screen_info>...");
        return this.screenInfoService.getAll();
    }
}
