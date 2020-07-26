package com.barrett.assistservice.mapper;

import com.barrett.assistservice.bean.WorkerProjectRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: assist-service
 * @description: Mybatis mapper for table worker_project_relation
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
@Mapper
public interface WorkerProjectRelationMapper {
    @Insert({"INSERT INTO worker_project_relation(id,project_id,project_guid,project_name,worker_id,worker_guid,worker_name) VALUES (#{id},#{project_id},#{project_guid},#{project_name},#{worker_id},#{worker_guid},#{worker_name})"})
    int insert(WorkerProjectRelation workerProjectRelation);

    @Update({"UPDATE worker_project_relation SET id=#{id},project_id=#{project_id},project_guid=#{project_guid},project_name=#{project_name},worker_id=#{worker_id},worker_guid=#{worker_guid},worker_name=#{worker_name} WHERE id=#{id}"})
    int update(WorkerProjectRelation workerProjectRelation);

    @Delete({"DELETE FROM worker_project_relation WHERE id=#{id}"})
    int delete(@Param("id") int paramInt);

    @Delete({"DELETE FROM worker_project_relation"})
    int deleteAll();

    @Delete({"DELETE FROM worker_project_relation WHERE project_id=#{project_id}"})
    int deleteAllByProjectID(@Param("project_id") int project_id);

    @Select({"SELECT * FROM worker_project_relation WHERE id=#{id}"})
    List<WorkerProjectRelation> getAllById(@Param("id") int paramInt);

    @Select({"SELECT worker_id FROM worker_project_relation WHERE project_id=#{project_id}"})
    int[] getAllWorkerIdByProjectId(@Param("project_id") int project_id);

    @Select({"SELECT id,project_id,project_guid,project_name,worker_id,worker_guid,worker_name FROM worker_project_relation"})
    List<WorkerProjectRelation> getAll();
}
