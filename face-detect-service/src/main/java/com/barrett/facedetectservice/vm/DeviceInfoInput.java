package com.barrett.facedetectservice.vm;

import lombok.Data;

/**
 * @program: smartsite-master
 * @description: Json entity for device information input
 * @author: Barrett
 * @create: 2020-05-28 08:24
 **/
@Data
public class DeviceInfoInput {
    String sbbm;
    int lx;
    String sbmc;
    String ip;
    String port;
    String ssgd;
    String x;
    String y;
    String user;
    String passerword;


//            "sbbm": null,
//                    "lx": 1,
//                    "sbmc": "安徽一建考勤机",
//                    "ip": "192.168.0.203",
//                    "port": "8000",
//                    "ssgd": "安徽一建办公区",
//                    "x": "115.7137233773",
//                    "y": "28.2006440150",
//                    "user": null,
//                    "passerword": null
}
