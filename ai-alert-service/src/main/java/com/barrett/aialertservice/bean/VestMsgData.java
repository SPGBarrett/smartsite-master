package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Java bean for table vest_msg_data
 * @author: Barrett
 * @create: 2020-05-16 09:36
 **/
@Data
public class VestMsgData {
    int id; // 主键
    String guid; // 唯一ID
    String parent_id; // alert_msg的guid
    String message; // 报警信息
    int nums_of_wrong_clothes; // 反光背心违规数量
    int alert_flag; // 标记
    long time_stamp; // 时间戳
}
