package com.barrett.facedetectservice.vm;

import lombok.Data;

import java.util.Date;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-06-21 15:05
 **/
@Data
public class UserInfoReturnParams {
    int workerId;  			//劳务人员id
    String name;      			//劳务人员姓名
    String idcard;     			//劳务人员身份证号 隐藏后4位
    String timecard;     			//劳务人员考勤卡号
    int projectId;					//项目id
    int cooperatorId;  			//劳务公司id
    int groupId;      			//班组id
    int professionId;          	//工种id
    String cooperatorName;		//劳务公司名称
    String groupName;			//班组名称
    String professionName;		//工种名称
    Byte status;	     		//劳务人员状态 1：在职 2：离职 3：删除
    Date modifyTime;    //最后修改时间
}
