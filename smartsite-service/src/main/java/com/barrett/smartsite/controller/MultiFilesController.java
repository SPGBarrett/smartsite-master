package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.controller.MultiFilesController;
import com.barrett.smartsite.service.MultiFilesService;

import java.util.List;

import com.barrett.smartsite.service.MultiScreenSettingService;
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
 * @create: 2019-10-07 10:42
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"多媒体文件增删查接口"})
public class MultiFilesController {
    @Autowired
    MultiFilesService multiFilesService;
    @Autowired
    MultiScreenSettingService multiScreenSettingService;

    @RequestMapping(value = {"/MultiFiles"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody MultiFiles multiFiles) {
        System.out.println("Input data into <multi_files>...");
        return this.multiFilesService.insert(multiFiles);
    }


    @RequestMapping(value = {"/MultiFiles"}, method = {RequestMethod.POST})
    public int update(@RequestBody MultiFiles multiFiles) {
        System.out.println("Update data of <multi_files>...");
        return this.multiFilesService.update(multiFiles);
    }


    @RequestMapping(value = {"/MultiFiles"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <multi_files>...");
        return this.multiFilesService.delete(id);
    }


    @RequestMapping(value = {"/MultiFiles"}, method = {RequestMethod.GET})
    public List<MultiFiles> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <multi_files>...");
        return this.multiFilesService.getAllById(id);
    }


    @RequestMapping(value = {"/MultiFilesAll"}, method = {RequestMethod.GET})
    public List<MultiFiles> getAll() {
        System.out.println("Query all data from <multi_files>...");
        return this.multiFilesService.getAll();
    }

    @RequestMapping(value = {"/MultiFilesByParams"}, method = {RequestMethod.GET})
    public List<MultiFiles> getAllByParams(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "parent", required = true) String parent) {
        System.out.println("Query all data from <multi_files> by params...");
        return this.multiFilesService.getAllByParams(type, parent);
    }

    @RequestMapping(value = {"/MultiFilesSelected"}, method = {RequestMethod.GET})
    public List<MultiFiles> getAllBySelection() {
        System.out.println("Query data from <multi_files> by type selection...");
        // Get type selection；
        int displayType = multiScreenSettingService.getAll().get(0).getDisplay_type();
        if(displayType == 0){
            return this.multiFilesService.getAllByParams("IMAGE", "多媒体界面");
        }else{
            return this.multiFilesService.getAllByParams("VIDEO", "多媒体界面");
        }
    }


}
