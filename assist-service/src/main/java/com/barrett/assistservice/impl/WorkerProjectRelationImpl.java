package com.barrett.assistservice.impl;

import com.barrett.assistservice.bean.WorkerProjectRelation;
import com.barrett.assistservice.mapper.WorkerProjectRelationMapper;
import com.barrett.assistservice.service.WorkerProjectRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerProjectRelationImpl implements WorkerProjectRelationService {
    @Autowired(required = false)
    WorkerProjectRelationMapper workerProjectRelationMapper;

    @Override
    public int insert(WorkerProjectRelation workerProjectRelation) {
        return workerProjectRelationMapper.insert(workerProjectRelation);
    }

    @Override
    public int update(WorkerProjectRelation workerProjectRelation) {
        return workerProjectRelationMapper.update(workerProjectRelation);
    }

    @Override
    public int delete(int paramInt) {
        return workerProjectRelationMapper.delete(paramInt);
    }

    @Override
    public int deleteAll() {
        return workerProjectRelationMapper.deleteAll();
    }

    @Override
    public int deleteAllByProjectID(int project_id) {
        return workerProjectRelationMapper.deleteAllByProjectID(project_id);
    }

    @Override
    public List<WorkerProjectRelation> getAllById(int paramInt) {
        return workerProjectRelationMapper.getAllById(paramInt);
    }

    @Override
    public int[] getAllWorkerIdByProjectId(int project_id) {
        return workerProjectRelationMapper.getAllWorkerIdByProjectId(project_id);
    }

    @Override
    public List<WorkerProjectRelation> getAll() {
        return workerProjectRelationMapper.getAll();
    }
}
