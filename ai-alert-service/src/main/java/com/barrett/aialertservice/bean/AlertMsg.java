package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 09:27
 **/
@Data
public class AlertMsg {
    int id; //主键
    String guid; //唯一id
    String aid; //算法id
    String cid; //摄像头id
    String cid_url; //摄像头拉流地址
    long time_stamp; //时间戳
    int status; //状态值
    String srcpic_name; //报警输出图片关联的原始分析图片
    String srcpic_data; //原始分析图片，base64格式编码的longtext
    String pic_name; //报警图片命名
    String pic_data; //报警结果图片，加上了bounding box, base64格式编码
    int alert_type; // 1=安全帽，2=反光背心，3=着装
}
