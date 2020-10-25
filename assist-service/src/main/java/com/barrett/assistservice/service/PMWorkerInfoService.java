package com.barrett.assistservice.service;

import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.vm.GroupInfo;
import com.barrett.assistservice.vm.PMWorkerTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: assist-service
 * @description: service for Pingming worker information
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
public interface PMWorkerInfoService {
    int insert(PMWorkerInfo pmWorkerInfo);

    int update(PMWorkerInfo pmWorkerInfo);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    int deleteAllByProjectID(@Param("project_id") int project_id);

    List<PMWorkerInfo> getAllById(@Param("id") int paramInt);

    List<PMWorkerInfo> getWorkerByCompanyAndDepartment(@Param("cooperator_id") int cooperator_id, @Param("group_id") int group_id);

    List<PMWorkerInfo> getAllWorkerDetailBAndFilteredByProjectGUID(@Param("cooperator_id") int cooperator_id, @Param("group_id") int group_id, @Param("project_guid") String project_guid);

    List<PMWorkerInfo> getWorkerByCompany(@Param("cooperator_id") int cooperator_id);

    List<PMWorkerInfo> getAll();

    List<GroupInfo> getGroupInfoByCooperatorId(@Param("cooperator_id") int cooperator_id);

    List<PMWorkerTree> getPMWorkerTree();

    List<String> getPMWorkerTreeTest();
}
