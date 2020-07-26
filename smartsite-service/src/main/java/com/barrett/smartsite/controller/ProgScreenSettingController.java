package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MainScreenSetting;
import com.barrett.smartsite.bean.MultiScreenSetting;
import com.barrett.smartsite.bean.ProgScreenSetting;
import com.barrett.smartsite.bean.ProgScreenSettingParams;
import com.barrett.smartsite.service.MainScreenSettingService;
import com.barrett.smartsite.service.ProgScreenSettingService;
import com.barrett.smartsite.service.ScreenInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite
 * @description: User controller for program screen settings
 * @author: Barrett
 * @create: 2020-01-02 19:51
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"程序定时播放模板参数设置的接口"})
public class ProgScreenSettingController {

    @Autowired
    ProgScreenSettingService progScreenSettingService;
    @Autowired
    MainScreenSettingService mainScreenSettingService;

    @RequestMapping(value = {"/ProgScreenSetting"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody ProgScreenSetting progScreenSetting) {
        System.out.println("Input data into <prog_screen_setting>...");
        return this.progScreenSettingService.insert(progScreenSetting);
    }

    @RequestMapping(value = {"/ProgScreenSettingAll"}, method = {RequestMethod.PUT})
    public int insertAll(@RequestBody List<ProgScreenSetting> progScreenSettingList) {
        int reuslt = 0;
        System.out.println("Input list data into <prog_screen_setting>...");
        for (ProgScreenSetting pss : progScreenSettingList) {
            int tmp = this.progScreenSettingService.insert(pss);
            reuslt += tmp;
        }
        return reuslt;
    }

    @RequestMapping(value = {"/ProgScreenSetting"}, method = {RequestMethod.POST})
    public int update(@RequestBody ProgScreenSetting progScreenSetting) {
        System.out.println("Update data of <prog_screen_setting>...");
        return this.progScreenSettingService.update(progScreenSetting);
    }

    @RequestMapping(value = {"/ProgScreenSetting"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <prog_screen_setting>...");
        return this.progScreenSettingService.delete(id);
    }

    @RequestMapping(value = {"/ProgScreenSettingAll"}, method = {RequestMethod.DELETE})
    public int deleteAll() {
        System.out.println("Delete all data from <prog_screen_setting>...");
        return this.progScreenSettingService.deleteAll();
    }

    @RequestMapping(value = {"/ProgScreenSetting"}, method = {RequestMethod.GET})
    public List<ProgScreenSetting> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <prog_screen_setting>...");
        return this.progScreenSettingService.getAllById(id);
    }


    @RequestMapping(value = {"/ProgScreenSettingAll"}, method = {RequestMethod.GET})
    public List<ProgScreenSetting> getAll() {
        System.out.println("Query all data from <prog_screen_setting>...");
        return this.progScreenSettingService.getAll();
    }

    // Get all required params for program screen display:
    @RequestMapping(value = {"/ProgScreenSettingParamsAll"}, method = {RequestMethod.GET})
    public List<ProgScreenSettingParams> getParamsAll() {
        System.out.println("Query all data from <prog_screen_setting> and <main_screen_setting>...");
        // Get required params:
        List<MainScreenSetting> mainScreenSettingList = mainScreenSettingService.getAll();
        List<ProgScreenSetting> progScreenSettingList = progScreenSettingService.getAll();
        // Construct return types:
        ProgScreenSettingParams result = new ProgScreenSettingParams();
        result.setLoop_screen(mainScreenSettingList.get(0).getLoop_screen());
        result.setMain_screen(mainScreenSettingList.get(0).getMain_screen());
        result.setLoop_interval(mainScreenSettingList.get(0).getLoop_interval());
        result.setProgScreenSettingList(progScreenSettingList);
        List<ProgScreenSettingParams> resultList = new ArrayList<>();
        resultList.add(result);
        return resultList;
    }


}
