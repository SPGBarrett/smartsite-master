package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Java bean for table clothes_msg_data
 * @author: Barrett
 * @create: 2020-05-16 09:29
 **/
@Data
public class ClothesMsgData {
    int id; //主键
    String guid; //唯一ID
    String parent_id; //AlertMsg的GUID
    long time_stamp; //时间戳
    int alert_flag; //是否有着装问题
    int report_num; //不符合要求的总数
}
