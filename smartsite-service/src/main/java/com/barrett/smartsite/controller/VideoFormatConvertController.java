package com.barrett.smartsite.controller;

import com.barrett.smartsite.factory.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * @program: smartsite
 * @description: User controller for executing video format convert
 * @author: Barrett
 * @create: 2019-10-31 10:10
 **/
@RestController
@RequestMapping("video")
public class VideoFormatConvertController {
    @Autowired
    FileUtil fileUtil;

    @RequestMapping("")
    public String main(){
        return "hello, I'm skyjilygao";
    }

    @RequestMapping("convert")
    public String convertVideo(){
        String strResult = fileUtil.convertFileToM3U8("E:\\uploadFiles\\12314.mp4","E:\\convertFiles\\12314.m3u8");
        return strResult;
    }

    @RequestMapping("convertCurrentFiles")
    public String convertCurrentVideo(){
        String strResult = fileUtil.convertFilesAsync();
        return strResult;
    }
}
