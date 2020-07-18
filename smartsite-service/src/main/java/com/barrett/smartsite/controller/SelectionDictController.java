package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.SelectionDict;
import com.barrett.smartsite.controller.SelectionDictController;
import com.barrett.smartsite.service.SelectionDictService;

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
 * @create: 2019-10-07 10:44
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"前端参数字典的接口"})
public class SelectionDictController {
    @Autowired
    SelectionDictService selectionDictService;

    @RequestMapping(value = {"SelectionDict"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody SelectionDict selectionDict) {
        return this.selectionDictService.insert(selectionDict);
    }

    @RequestMapping(value = {"/SelectionDict"}, method = {RequestMethod.POST})
    public int update(@RequestBody SelectionDict selectionDict) {
        return this.selectionDictService.update(selectionDict);
    }

    @RequestMapping(value = {"/SelectionDict"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        return this.selectionDictService.delete(id);
    }

    @RequestMapping(value = {"/SelectionDict"}, method = {RequestMethod.GET})
    public List<SelectionDict> getAllById(@RequestParam(value = "id", required = true) int id) {
        return this.selectionDictService.getAllById(id);
    }

    @RequestMapping(value = {"SelectionDictAll"}, method = {RequestMethod.GET})
    public List<SelectionDict> getAll() {
        return this.selectionDictService.getAll();
    }
}
