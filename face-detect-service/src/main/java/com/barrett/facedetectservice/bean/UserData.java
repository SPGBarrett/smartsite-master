package com.barrett.facedetectservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Java bean for table user_data
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
@Data
public class UserData {
    int id;//主键
    String guid;//唯一编号
    String user_name;//用户名
    String card_no;//卡号 - 现在用于储存每个人唯一的工号
    int card_type;//卡类型
    String work_no;//工号 - 仅储存工号的数字部分
    long update_date;//数据更新时间
    String id_card;//身份证号
    int status;//是否在职
    int card_right;//卡的权限
    int auth_type;//刷卡还是人脸或者两者
}
