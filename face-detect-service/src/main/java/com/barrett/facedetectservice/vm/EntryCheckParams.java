package com.barrett.facedetectservice.vm;

import lombok.Data;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-17 19:55
 **/
@Data
public class EntryCheckParams {
    long time; // 扫码的时间，单位毫秒
    long span; // 扫描后检测扫脸的时间阈值
    String user; // 用户的工号，“ZN0612”
    List<String> deviceSN; // 允许扫脸的设备清单，目前使用IP地址
}
