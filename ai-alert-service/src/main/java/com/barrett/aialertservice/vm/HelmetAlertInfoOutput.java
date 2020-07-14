package com.barrett.aialertservice.vm;

import com.barrett.aialertservice.bean.HeadInfo;
import lombok.Data;

import java.util.List;

/**
 * @program: smartsite-master
 * @description: Helmet alert info output data structure from querying db
 * @author: Barrett
 * @create: 2020-05-05 13:59
 **/
@Data
public class HelmetAlertInfoOutput {
    int id;
    String guid;
    String aid; //算法id
    String cid; //摄像头id
    String cid_url; //摄像头拉流地址
    long time_stamp; //时间戳
    int status; //状态值
    String srcpic_name; //报警输出图片关联的原始分析图片
    String srcpic_data; //原始分析图片，base64格式编码
    String pic_name; //报警图片命名
    String pic_data; //报警结果图片，base64格式编码
    Heads data;

    @Data
    public static class Heads{
        int id;
        String guid;
        long timeStamp; //时间戳
        int numOfHead; //检测到未戴安全帽的数量
        int alertFlag;
        List<HeadInfo> headInfo;
    }
}
