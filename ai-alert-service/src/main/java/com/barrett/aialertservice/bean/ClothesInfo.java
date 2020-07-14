package com.barrett.aialertservice.bean;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Java bean for table clothes_info
 * @author: Barrett
 * @create: 2020-05-16 09:31
 **/
@Data
public class ClothesInfo {
    int id; //主键
    String guid; //唯一ID
    String parent_id; //AlertMsg的GUID
    int x; //左上x
    int y; //左上y
    int width; //宽度
    int height; //高度
}
