package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.TextContent;
import com.barrett.smartsite.controller.TextContentController;
import com.barrett.smartsite.service.TextContentService;

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
 * @create: 2019-10-07 10:46
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"文字内容增删查的接口"})
public class TextContentController {
    @Autowired
    TextContentService textContentService;

    @RequestMapping(value = {"/TextContent"}, method = {RequestMethod.PUT})
    public int insert(@RequestBody TextContent textContent) {
        System.out.println("Input data into <text_content>...");
        return this.textContentService.insert(textContent);
    }


    @RequestMapping(value = {"/TextContent"}, method = {RequestMethod.POST})
    public int update(@RequestBody TextContent textContent) {
        System.out.println("Update data of <text_content>...");
        return this.textContentService.update(textContent);
    }


    @RequestMapping(value = {"/TextContent"}, method = {RequestMethod.DELETE})
    public int delete(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Delete data from <text_content>...");
        return this.textContentService.delete(id);
    }


    @RequestMapping(value = {"/TextContent"}, method = {RequestMethod.GET})
    public List<TextContent> getAllById(@RequestParam(value = "id", required = true) int id) {
        System.out.println("Query data from <text_content>...");
        return this.textContentService.getAllById(id);
    }


    @RequestMapping(value = {"/TextContentAll"}, method = {RequestMethod.GET})
    public List<TextContent> getAll() {
        System.out.println("Query all data from <text_content>...");
        return this.textContentService.getAll();
    }
}
