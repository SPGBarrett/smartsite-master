package com.barrett.assistservice.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.barrett.assistservice.bean.PMWorkInfoForMapping;
import com.barrett.assistservice.bean.PMWorkerInfo;
import com.barrett.assistservice.bean.WorkerProjectRelation;
import com.barrett.assistservice.http.CallPMApis;
import com.barrett.assistservice.service.PMWorkerInfoService;
import com.barrett.assistservice.service.WorkerProjectRelationService;
import com.barrett.assistservice.vm.PMWorkerTree;
import com.barrett.assistservice.vm.WorkerProjectRelationInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    PMWorkerInfoService pmWorkerInfoService;
    @Autowired
    CallPMApis callPMApis;

    @ApiOperation(value = "将结束了的项目的员工全部退场", notes = "将结束了的项目的员工全部退场")
    @ApiImplicitParams({@ApiImplicitParam(name = "project_id", value = "项目编号", dataType = "int", paramType = "query", required = true)})
    @RequestMapping(value = "/WorkerAllExit", method = RequestMethod.POST)
    public String allWorkerExit(@RequestParam(value = "project_id", required = true) int project_id){
        // Get all worker ids bound to this project:
        String result = "";
        int[] workerIds = workerProjectRelationService.getAllWorkerIdByProjectId(project_id);
        if(workerIds != null && workerIds.length > 0){
            result = callPMApis.allWorkerExitCall(workerIds);
        }
        return result;
    }

    @ApiOperation(value = "将员工与项目绑定", notes = "将员工与项目绑定")
    @ApiImplicitParams({@ApiImplicitParam(name = "workerProjectRelationInput", value = "项目与员工关联信息", dataType = "WorkerProjectRelationInput", paramType = "body", required = true)})
    @RequestMapping(value = "/BuildRelation", method = RequestMethod.POST)
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
    @RequestMapping(value = "/ProjectWorkers", method = RequestMethod.POST)
    public int[] getProjectRelatedWorkers(@RequestParam(value = "project_id", required = true) int project_id){
        return workerProjectRelationService.getAllWorkerIdByProjectId(project_id);
    }

    @ApiOperation(value = "获取PM所有员工信息", notes = "获取PM所有员工信息")
    @RequestMapping(value = "/PmWorkerInfoTotal", method = RequestMethod.POST)
    public String getPMWorkerInfo(){
        return callPMApis.getAllWorkerInfo();
    }

    @ApiOperation(value = "获取PM所有员工信息并存入数据库", notes = "获取PM所有员工信息并存入数据库")
    @RequestMapping(value = "/PmWorkerInfoTotalAndSave", method = RequestMethod.POST)
    public String getPMWorkerInfoAndSave(){
        // Clean database:
        pmWorkerInfoService.deleteAll();
        // Get data:
        try{
            callPMApis.getWorkerDataAndSave(1);
            return "Success";
        }catch (Exception e){
            return "Failed";
        }
    }

    @ApiOperation(value = "获取PM所有员工的树形结构", notes = "获取PM所有员工的树形结构")
    @RequestMapping(value = "/PmWorkerTree", method = RequestMethod.POST)
    public List<PMWorkerTree> getPMWorkerTree(){
        return pmWorkerInfoService.getPMWorkerTree();
    }

    @ApiOperation(value = "根据单位和部门获取员工信息", notes = "根据单位和部门获取员工信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "cooperator_id", value = "单位ID", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "group_id", value = "部门ID", dataType = "int", paramType = "query", required = true)})
    @RequestMapping(value = "/WorkerInfoByCompanyAndGroup", method = RequestMethod.POST)
    public List<PMWorkerInfo> getWorkerInfoByCompanyAndDepartment(@RequestParam(value = "cooperator_id", required = true) int cooperator_id, @RequestParam(value = "group_id", required = true) int group_id){
        if(cooperator_id == 0) return null;
        if(group_id == 0){
            return pmWorkerInfoService.getWorkerByCompany(cooperator_id);
        }else {
            return pmWorkerInfoService.getWorkerByCompanyAndDepartment(cooperator_id, group_id);
        }
    }
}
