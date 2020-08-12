package com.barrett.assistservice.vm;

import lombok.Data;

import java.util.List;

@Data
public class PMWorkerTree {
    String cooperator_id;
    String cooperator_name;
    List<GroupInfo> group_infos;

}
