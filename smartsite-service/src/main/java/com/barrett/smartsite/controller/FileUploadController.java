package com.barrett.smartsite.controller;
import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.controller.FileUploadController;
import com.barrett.smartsite.factory.FileUtil;
import com.barrett.smartsite.factory.UniqueIDUtil;
import com.barrett.smartsite.service.FileUploadService;
import com.barrett.smartsite.service.MultiFilesService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 10:36
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
@Api(tags = {"多媒体文件上传的接口"})
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    MultiFilesService multiFilesService;
    @Autowired
    FileUtil fileUtil;
    @Value("${web.upload-path}")
    private String path;
    @Value("${multimedia.link}")
    private String defaultUrl;

    private final String appendUrl = "/external/";

    public final String FILE_PARENT_WELCOME = "欢迎界面";
    public final String FILE_PARENT_MULTI = "多媒体界面";

    @ApiOperation(value = "上传欢迎页面多媒体文件", notes="上传欢迎页面多媒体文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "多媒体文件", dataType = "MultipartFile", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/UploadWelcomeFile"}, method = {RequestMethod.POST})
    public String uploadWelcomeFile(@RequestParam("file") MultipartFile file) {
        clearHistoryData();
        return fileUtil.uploadFile(file, "欢迎界面");
    }

    @RequestMapping(value = {"/UploadFile"}, method = {RequestMethod.POST})
    public String uploadMultiFile(@RequestParam("file") MultipartFile file) { return fileUtil.uploadFile(file, "多媒体界面"); }


    @RequestMapping(value = {"/ClearServerFiles"}, method = {RequestMethod.POST})
    public String clearServerFiles() {
        clearHistoryData();
        return "Clear Success";
    }

    @RequestMapping(value = {"/ClearServerFilesByParams"}, method = {RequestMethod.POST})
    public String clearServerFilesByParams(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "parent", required = true) String parent) {
        clearTargetedHistory(type, parent);
        return "Clear Success";
    }

    private void clearHistoryData() {
        this.fileUploadService.deleteAllFilesFromLocalFolder(this.path);
        this.multiFilesService.deleteAll();
    }

    private void clearTargetedHistory(String type, String parent){
        // Get all file name:
        List<MultiFiles> tmpList = this.multiFilesService.getAllByParams(type, parent);
        // Delete files:
        for(MultiFiles thisFile : tmpList){
            String[] fileNames = thisFile.getUrl_link().split("/");
            String fileName = fileNames[fileNames.length - 1];
            String filePath = thisFile.getPath() + fileName;
            fileUploadService.deleteFile(filePath);
        }
        // Delete database data:
        this.multiFilesService.deleteAllByParams(type, parent);
    }

    private void clearOnlyDB() { this.multiFilesService.deleteAll(); }

    // ****The one in use !!! *********************
    // Upload common multimedia files
    @ApiOperation(value = "上传多媒体文件", notes="上传多媒体文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "多媒体文件列表", dataType = "MultipartFile[]", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/UploadFiles"}, method = {RequestMethod.POST})
    public String uploadFiles(@RequestParam("files") MultipartFile[] files) {
        // Loop and upload files:
        String result = "";
        for (int i = 0; i < files.length; i++) {
            result = result + fileUtil.uploadFile(files[i], "多媒体界面");
        }
        // Async method: convert Video to U3M8 format:
        fileUtil.convertFilesAsync();
        // Must return as a Json string for JS Fileinput Component:
        return "{\"result\" : \"" + result + "\"}";
    }

    // Upload welcome screen related media files:
    @ApiOperation(value = "上传欢迎页面多媒体文件", notes="上传欢迎页面多媒体文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "多媒体文件列表", dataType = "MultipartFile[]", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/UploadWelcomeFiles"}, method = {RequestMethod.POST})
    public String uploadWelcomeFiles(@RequestParam("files") MultipartFile[] files) {
        String result = "";
        for (int i = 0; i < files.length; i++) {
            result = result + fileUtil.uploadFile(files[i], "欢迎界面");
        }
        // Must return as a Json string for JS Fileinput Component:
        return "{\"result\" : \"" + result + "\"}";
    }

    // Upload party home related media files:
    @ApiOperation(value = "上传党建家页面多媒体文件", notes="上传党建家页面多媒体文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "多媒体文件列表", dataType = "MultipartFile[]", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/UploadPartyHomeFiles"}, method = {RequestMethod.POST})
    public String uploadPartyHomeFiles(@RequestParam("files") MultipartFile[] files) {
        String result = "";
        for (int i = 0; i < files.length; i++) {
            result = result + fileUtil.uploadFile(files[i], "党建家");
        }
        // Async method: convert Video to U3M8 format:
        fileUtil.convertFilesAsync();
        // Must return as a Json string for JS Fileinput Component:
        return "{\"result\" : \"" + result + "\"}";
    }
}
