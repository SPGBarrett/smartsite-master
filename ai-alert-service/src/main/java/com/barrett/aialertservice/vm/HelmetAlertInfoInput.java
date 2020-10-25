package com.barrett.aialertservice.vm;

import lombok.Data;

import java.util.List;

/**
 * @program: smartsite-master
 * @description: Helmet alert info from AI module
 * @author: Barrett
 * @create: 2020-05-05 13:59
 **/
@Data
public class HelmetAlertInfoInput {
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
    public static class Heads {
        int alert_flag; //检测到未戴安全帽的数量
        int alert_num;
        List<HeadDetail> info;

        @Data
        public static class HeadDetail {
            int x; //报警框左上角坐标x
            int y; //报警框左上角坐标y
            int width; //报警框宽度
            int height; //报警框高度
            double confidence; //置信度
            String name; // Head
            String color; //安全帽颜色
            int alert_tag;
        }
    }
}
