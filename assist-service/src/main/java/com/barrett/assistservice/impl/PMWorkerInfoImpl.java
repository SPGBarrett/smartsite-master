package com.barrett.assistservice.impl;

import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.mapper.PMWorkerInfoMapper;
import com.barrett.assistservice.service.PMWorkerInfoService;
import com.barrett.assistservice.vm.GroupInfo;
import com.barrett.assistservice.vm.PMWorkerTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PMWorkerInfoImpl implements PMWorkerInfoService {
    @Autowired(required = false)
    PMWorkerInfoMapper pmWorkerInfoMapper;

    @Override
    public int insert(PMWorkerInfo pmWorkerInfo) {
        return pmWorkerInfoMapper.insert(pmWorkerInfo);
    }

    @Override
    public int update(PMWorkerInfo pmWorkerInfo) {
        return pmWorkerInfoMapper.update(pmWorkerInfo);
    }

    @Override
    public int delete(int paramInt) {
        return pmWorkerInfoMapper.delete(paramInt);
    }

    @Override
    public int deleteAll() {
        return pmWorkerInfoMapper.deleteAll();
    }

    @Override
    public int deleteAllByProjectID(int project_id) {
        return pmWorkerInfoMapper.deleteAllByProjectID(project_id);
    }

    @Override
    public List<PMWorkerInfo> getAllById(int paramInt) {
        return pmWorkerInfoMapper.getAllById(paramInt);
    }

    @Override
    public List<PMWorkerInfo> getWorkerByCompanyAndDepartment(int cooperator_id, int group_id) {
        return pmWorkerInfoMapper.getWorkerByCompanyAndDepartment(cooperator_id, group_id);
    }

    @Override
    public List<PMWorkerInfo> getAllWorkerDetailBAndFilteredByProjectGUID(int cooperator_id, int group_id, String project_guid) {
        return pmWorkerInfoMapper.getAllWorkerDetailBAndFilteredByProjectGUID(cooperator_id, group_id, project_guid);
    }

    @Override
    public List<PMWorkerInfo> getWorkerByCompany(int cooperator_id) {
        return pmWorkerInfoMapper.getWorkerByCompany(cooperator_id);
    }

    @Override
    public List<PMWorkerInfo> getAll() {
        return pmWorkerInfoMapper.getAll();
    }

    @Override
    public List<GroupInfo> getGroupInfoByCooperatorId(int cooperator_id) {
        return pmWorkerInfoMapper.getGroupInfoByCooperatorId(cooperator_id);
    }


    @Override
    public List<PMWorkerTree> getPMWorkerTree() {
        return pmWorkerInfoMapper.getPMWorkerTree();
    }

    @Override
    public List<String> getPMWorkerTreeTest() {
        return pmWorkerInfoMapper.getPMWorkerTreeTest();
    }
}
