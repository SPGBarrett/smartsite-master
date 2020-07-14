package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-05 11:37
 **/
@Data
public class HelmetMsgData {
    int id; //主键
    String guid; //唯一id
    String parent_id; //alert_helmet_msg的guid
    long time_stamp; //时间戳
    int num_of_head; //检测到未戴安全帽的数量
    int alert_flag; // 报警标记
}
