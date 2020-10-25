package com.barrett.facedetectservice.vm;

import lombok.Data;

@Data
public class AttendanceReturnParamsSc {
    int workerId;            //劳务人员id
    int projectId;            //项目id
    String photo;                //打卡抓拍照片
    long recordTime;        //打卡时间
    Byte type;            //打卡方向 1.进 2出
    String cardNo; //工号既卡号
    String deviceSn; //该考勤的考勤设备编号
}
