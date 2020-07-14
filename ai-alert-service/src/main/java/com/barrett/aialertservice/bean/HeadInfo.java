package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-05 11:37
 **/
@Data
public class HeadInfo {
    int id; //主键
    String guid; //唯一id
    String parent_id; //helmet_msg_data的guid
    int x; //报警框左上角坐标x
    int y; //报警框左上角坐标y
    int width; //报警框宽度
    int height; //报警框高度
    int num_of_helmet; //安全帽数量
    String color; //安全帽颜色
}
