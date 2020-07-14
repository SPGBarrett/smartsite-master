package com.barrett.facedetectservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-06-21 15:05
 **/
@Data
public class UserInfoQueryParams {
    String modifyTime;//上一次同步时间 时间戳
    int size;//每页记录数
    int page;//当前页码
}
