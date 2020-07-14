package com.barrett.facedetectservice.vm;

import lombok.Data;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-06-21 19:02
 **/
@Data
public class FaceDetectDataQuery {
    int id; // 主键
    String guid;// 唯一编号
    long time_stamp;// 时间戳
    int alert_major_type;// 大报警类型
    int alert_minor_type;// 小报警类型
    String card_no;// 卡号
    String user_info;// 用户信息
    int work_no;// 工号
    int card_alert_type;// 是否识别通过
    String device_no;// 打卡的设备编号
    String device_ip;// 设备ip
    String device_port;// 设备端口
    String pic_data;// base64格式的图片
    List<String> device_type;
    List<Integer> workId;
}
