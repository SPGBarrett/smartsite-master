package com.barrett.assistservice.mapper;

import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.bean.WorkerProjectRelation;
import com.barrett.assistservice.vm.GroupInfo;
import com.barrett.assistservice.vm.PMWorkerTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PMWorkerInfoMapper {
    @Insert({"INSERT INTO pm_worker_info(id,worker_id,name,id_card,time_card,project_id,cooperator_id,group_id,professional_id,cooperator_name,group_name,professional_name,status,modify_time) VALUES (#{id},#{worker_id},#{name},#{id_card},#{time_card},#{project_id},#{cooperator_id},#{group_id},#{professional_id},#{cooperator_name},#{group_name},#{professional_name},#{status},#{modify_time})"})
    int insert(PMWorkerInfo pmWorkerInfo);

    @Update({"UPDATE pm_worker_info SET id=#{id},worker_id=#{worker_id},name=#{name},id_card=#{id_card},time_card=#{time_card},project_id=#{project_id},cooperator_id=#{cooperator_id},group_id=#{group_id},professional_id=#{professional_id},cooperator_name=#{cooperator_name},group_name=#{group_name},professional_name=#{professional_name},status=#{status},modify_time=#{modify_time} WHERE id=#{id}"})
    int update(PMWorkerInfo pmWorkerInfo);

    @Delete({"DELETE FROM pm_worker_info WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM pm_worker_info"})
    int deleteAll();

    @Delete({"DELETE FROM pm_worker_info WHERE project_id=#{project_id}"})
    int deleteAllByProjectID(@Param("project_id") int project_id);

    @Select({"SELECT * FROM pm_worker_info WHERE id=#{id}"})
    List<PMWorkerInfo> getAllById(@Param("id") int paramInt);

    @Select({"SELECT id,worker_id,name,id_card,time_card,project_id,cooperator_id,group_id,professional_id,cooperator_name,group_name,professional_name,status,modify_time FROM pm_worker_info WHERE cooperator_id=#{cooperator_id} AND group_id=#{group_id}"})
    List<PMWorkerInfo> getWorkerByCompanyAndDepartment(@Param("cooperator_id") int cooperator_id, @Param("group_id") int group_id);

    @Select({"SELECT id,worker_id,name,id_card,time_card,project_id,cooperator_id,group_id,professional_id,cooperator_name,group_name,professional_name,status,modify_time FROM pm_worker_info WHERE cooperator_id=#{cooperator_id} AND group_id=#{group_id} AND worker_id NOT IN (SELECT worker_id FROM worker_project_relation WHERE project_guid=#{project_guid})"})
    List<PMWorkerInfo> getAllWorkerDetailBAndFilteredByProjectGUID(@Param("cooperator_id") int cooperator_id, @Param("group_id") int group_id, @Param("project_guid") String project_guid);

    @Select({"SELECT id,worker_id,name,id_card,time_card,project_id,cooperator_id,group_id,professional_id,cooperator_name,group_name,professional_name,status,modify_time FROM pm_worker_info WHERE cooperator_id=#{cooperator_id}"})
    List<PMWorkerInfo> getWorkerByCompany(@Param("cooperator_id") int cooperator_id);

    @Select({"SELECT id,worker_id,name,id_card,time_card,project_id,cooperator_id,group_id,professional_id,cooperator_name,group_name,professional_name,status,modify_time FROM pm_worker_info"})
    List<PMWorkerInfo> getAll();

    @Select({"SELECT DISTINCT group_id FROM pm_worker_info WHERE cooperator_id=#{cooperator_id}"})
    @Results(id = "getGroupInfoByCooperatorId", value = {
            @Result(property = "group_id", column = "group_id"),
            @Result(property = "group_name", column = "group_id", many = @Many(select = "com.barrett.assistservice.mapper.PMWorkerInfoMapper.getGroupNameByGroupId")),
    })
    List<GroupInfo> getGroupInfoByCooperatorId(@Param("cooperator_id") int cooperator_id);

    @Select({"SELECT cooperator_name FROM pm_worker_info WHERE cooperator_id=#{cooperator_id} LIMIT 1"})
    String getCooperatorNameByCooperatorId(@Param("cooperator_id") int cooperator_id);

    @Select({"SELECT group_name FROM pm_worker_info WHERE group_id=#{group_id} LIMIT 1"})
    String getGroupNameByGroupId(@Param("group_id") int group_id);

    @Select({"SELECT DISTINCT cooperator_id FROM pm_worker_info"})
    @Results(id = "getPMWorkerTree", value = {
            @Result(property = "cooperator_id", column = "cooperator_id"),
            @Result(property = "cooperator_name", column = "cooperator_id", many = @Many(select = "com.barrett.assistservice.mapper.PMWorkerInfoMapper.getCooperatorNameByCooperatorId")),
            @Result(property = "group_infos", column = "cooperator_id", many = @Many(select = "com.barrett.assistservice.mapper.PMWorkerInfoMapper.getGroupInfoByCooperatorId")),
    })
    List<PMWorkerTree> getPMWorkerTree();

    @Select({"SELECT DISTINCT cooperator_name,cooperator_id FROM pm_worker_info"})
    List<String> getPMWorkerTreeTest();
}
