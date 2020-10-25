package com.barrett.facedetectservice.bean;

import lombok.Data;

import java.util.Date;

/**
 * @program: smartsite-master
 * @description: Java bean for table face_detect_data
 * @author: Barrett
 * @create: 2020-05-10 16:05
 **/
@Data
public class FaceDetectData {
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
    long create_timestamp; // 记录数据时的时间戳
    Date create_date; // 记录数据时的时间
}
