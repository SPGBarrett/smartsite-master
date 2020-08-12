package com.barrett.assistservice.bean;

import lombok.Data;

@Data
public class PMWorkerInfo {
    int id; //主键
    int worker_id; //劳务人员id
    String name; //劳务人员姓名
    String id_card; //劳务人员身份证号 隐藏后4位
    String time_card; //劳务人员考勤卡号
    int project_id; //项目id
    int cooperator_id; //劳务公司id
    int group_id; //班组id
    int professional_id; //工种id
    String cooperator_name; //劳务公司名称
    String group_name; //班组名称
    String professional_name; //工种名称
    Byte status; //劳务人员状态 1：在职 2：离职 3：删除
    long modify_time; //最后修改时间
}
