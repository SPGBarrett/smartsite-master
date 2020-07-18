package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.bean.MultiScreenSetting;
import com.barrett.smartsite.controller.MultiScreenController;
import com.barrett.smartsite.service.MultiFilesService;
import com.barrett.smartsite.service.MultiScreenSettingService;
import com.barrett.smartsite.vm.MultiScreenTotal;

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
 * @create: 2019-10-07 10:42
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"多媒体页面参数设置的接口"})
public class MultiScreenController {
    @Autowired
    MultiFilesService multiFilesService;
    @Autowired
    MultiScreenSettingService multiScreenSettingService;

    @RequestMapping(value = {"/MultiScreenTotal"}, method = {RequestMethod.GET})
    public List<MultiScreenTotal> getWelcomeScreenTotal() {
        System.out.println("Get all data required for Multi Screen...");

        List<MultiScreenTotal> resultList = new ArrayList<MultiScreenTotal>();

        List<MultiScreenSetting> settings = this.multiScreenSettingService.getAll();

        MultiScreenTotal tempData = new MultiScreenTotal();
        tempData.setDisplay_type(((MultiScreenSetting) settings.get(0)).getDisplay_type());
        tempData.setLoop_interval(((MultiScreenSetting) settings.get(0)).getLoop_interval());
        tempData.setMulti_path(((MultiScreenSetting) settings.get(0)).getMulti_path());
        tempData.setLoop_interval(((MultiScreenSetting) settings.get(0)).getLoop_interval());

        if (((MultiScreenSetting) settings.get(0)).getDisplay_type() == 0) {
            List<MultiFiles> imageFiles = this.multiFilesService.getAllByType("IMAGE");
            tempData.setMulti_files(imageFiles);
        } else if (((MultiScreenSetting) settings.get(0)).getDisplay_type() == 1) {
            List<MultiFiles> videoFiles = this.multiFilesService.getAllByType("VIDEO");
            tempData.setMulti_files(videoFiles);
        } else {
            tempData.setMulti_files(null);
        }

        resultList.add(tempData);

        return resultList;
    }
}
