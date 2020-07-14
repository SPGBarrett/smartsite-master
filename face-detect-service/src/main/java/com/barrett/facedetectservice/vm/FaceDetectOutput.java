package com.barrett.facedetectservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-11 19:31
 **/
@Data
public class FaceDetectOutput {
    long recordTime; // 打卡刷脸的时间
    String workerId; // 员工的一个唯一id，现在使用的是工号
    String deviceSn; // 卡机的序列号，现在使用的是卡机的IP
}
