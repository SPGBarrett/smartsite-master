package com.barrett.assistservice.service;

import com.barrett.assistservice.bean.WorkerProjectRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: assist-service
 * @description: business interfaces for worker_project_relation quries
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
public interface WorkerProjectRelationService {
    int insert(WorkerProjectRelation workerProjectRelation);

    int update(WorkerProjectRelation workerProjectRelation);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    int deleteAllByProjectID(@Param("project_id") int project_id);

    List<WorkerProjectRelation> getAllById(@Param("id") int paramInt);

    int[] getAllWorkerIdByProjectId(@Param("project_id") int project_id);

    List<WorkerProjectRelation> getAll();
}
