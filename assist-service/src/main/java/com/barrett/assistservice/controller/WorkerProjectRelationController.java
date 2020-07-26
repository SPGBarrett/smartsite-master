package com.barrett.assistservice.controller;


import com.barrett.assistservice.bean.WorkerProjectRelation;
import com.barrett.assistservice.http.CallPMApis;
import com.barrett.assistservice.service.WorkerProjectRelationService;
import com.barrett.assistservice.vm.WorkerProjectRelationInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: assist-service
 * @description: Java bean for table worker_project_relation
 * @author: Barrett
 * @create: 2020-05-10 16:06
 **/
@CrossOrigin
@RestController
@RequestMapping("/WorkerProjectRelation")
@Api(tags = {"外部模块API接口集合"})
public class WorkerProjectRelationController {

    @Autowired
    WorkerProjectRelationService workerProjectRelationService;
    @Autowired
    CallPMApis callPMApis;

    @ApiOperation(value = "将结束了的项目的员工全部退场", notes = "将结束了的项目的员工全部退场")
    @ApiImplicitParams({@ApiImplicitParam(name = "project_id", value = "项目编号", dataType = "int", paramType = "query", required = true)})
    @RequestMapping(value = "/workerAllExit", method = RequestMethod.POST)
    public int allWorkerExit(@RequestParam(value = "project_id", required = true) int project_id){
        // Get all worker ids bound to this project:
        int[] workerIds = workerProjectRelationService.getAllWorkerIdByProjectId(project_id);
        if(workerIds != null && workerIds.length > 0){
            callPMApis.allWorkerExitCall(workerIds);
        }
        return 0;
    }

    @ApiOperation(value = "将员工与项目绑定", notes = "将员工与项目绑定")
    @ApiImplicitParams({@ApiImplicitParam(name = "workerProjectRelationInput", value = "项目与员工关联信息", dataType = "WorkerProjectRelationInput", paramType = "body", required = true)})
    @RequestMapping(value = "/buildRelation", method = RequestMethod.POST)
    public int buildWorkerProjectRelation(@RequestBody(required = true) WorkerProjectRelationInput workerProjectRelationInput){
        // Clean current binding data:
        workerProjectRelationService.deleteAllByProjectID(workerProjectRelationInput.getProjectId());
        WorkerProjectRelation workerProjectRelation = new WorkerProjectRelation();
        for(WorkerProjectRelationInput.WorkerInfo wi : workerProjectRelationInput.getWorkerInfo()){
            workerProjectRelation.setProject_id(workerProjectRelationInput.getProjectId());
            workerProjectRelation.setProject_guid(workerProjectRelationInput.getProjectGuid());
            workerProjectRelation.setProject_name(workerProjectRelationInput.getProjectName());
            workerProjectRelation.setWorker_id(wi.getWorkerId());
            workerProjectRelation.setWorker_guid(wi.getWorkerGuid());
            workerProjectRelation.setWorker_name(wi.getWorkerName());
            // Save to db:
            workerProjectRelationService.insert(workerProjectRelation);
        }
        return 0;
    }

    @ApiOperation(value = "获取与某个项目关联的所有员工", notes = "获取与某个项目关联的所有员工")
    @ApiImplicitParams({@ApiImplicitParam(name = "project_id", value = "项目编号", dataType = "int", paramType = "query", required = true)})
    @RequestMapping(value = "/projectWorkers", method = RequestMethod.POST)
    public int[] getProjectRelatedWorkers(@RequestParam(value = "project_id", required = true) int project_id){
        return workerProjectRelationService.getAllWorkerIdByProjectId(project_id);
    }

}
