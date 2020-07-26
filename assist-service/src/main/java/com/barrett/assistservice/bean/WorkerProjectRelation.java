package com.barrett.assistservice.bean;

import lombok.Data;

/**
 * @program: assist-service
 * @description: Java bean for table worker_project_relation
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
@Data
public class WorkerProjectRelation {
    int id; // 主键
    int project_id; // 项目ID
    String project_guid; // 项目GUID
    String project_name; // 项目名称
    int worker_id; // 员工ID
    String worker_guid; // 员工GUID
    String worker_name; // 员工姓名
}
