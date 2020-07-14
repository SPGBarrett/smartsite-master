package com.barrett.facedetectservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-11 17:08
 **/
@Data
public class UserOutput {
    String workerId; // 员工的一个唯一ID，目前使用的是工号
    String idcard; // 员工的身份证号
    int status; // 员工是否在职
}
