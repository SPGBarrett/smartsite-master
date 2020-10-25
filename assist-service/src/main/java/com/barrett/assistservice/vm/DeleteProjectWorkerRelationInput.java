package com.barrett.assistservice.vm;

import lombok.Data;

import java.util.List;

@Data
public class DeleteProjectWorkerRelationInput {
    String project_guid;
    List<Integer> worker_ids;
}
