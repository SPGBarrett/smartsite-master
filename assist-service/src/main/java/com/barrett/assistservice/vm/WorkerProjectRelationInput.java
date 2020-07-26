package com.barrett.assistservice.vm;

import lombok.Data;

import java.util.List;

@Data
public class WorkerProjectRelationInput {
    int projectId;
    String projectGuid;
    String projectName;
    List<WorkerInfo> workerInfo;

    @Data
    public static class WorkerInfo{
        int workerId;
        String workerGuid;
        String workerName;
    }
}
