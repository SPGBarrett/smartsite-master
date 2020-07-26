package com.barrett.facedetectservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-06-21 15:06
 **/
@Data
public class AttendanceQueryParams {
    String projectNum;        //项目编号  测试编号：gddntest
    String modifyTime;        //同步时间 时间戳
    int size;                //每页记录数
    int page;                //当前页码
}
