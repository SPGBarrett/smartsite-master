package com.barrett.assistservice.service;

import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.bean.WorkerProjectRelation;
import com.barrett.assistservice.vm.DeleteProjectWorkerRelationInput;
import com.barrett.assistservice.vm.PMWorkerTree;
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

    int deleteAllByProjectGUID(@Param("project_guid") String project_guid);

    int deleteByProjectGUIDAndWorkerIDs(DeleteProjectWorkerRelationInput deleteProjectWorkerRelationInput);

    int deleteByProjectGUIDAndWorkerID(@Param("project_guid") String project_guid, @Param("worker_id") int worker_id);

    List<WorkerProjectRelation> getAllById(@Param("id") int paramInt);

    int[] getAllWorkerIdByProjectId(@Param("project_id") int project_id);

    int[] getAllWorkerIdByProjectGUID(@Param("project_guid") String project_guid);

    List<WorkerProjectRelation> getAll();

    List<PMWorkerInfo> getAllWorkerDetailByProjectId(@Param("project_id") int project_id);

    List<PMWorkerInfo> getAllWorkerDetailByProjectGUID(@Param("project_guid") String project_guid);
}
