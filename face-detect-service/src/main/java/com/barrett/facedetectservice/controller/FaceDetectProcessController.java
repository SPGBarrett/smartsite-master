package com.barrett.facedetectservice.controller;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.bean.UserData;
import com.barrett.facedetectservice.config.DeviceHandleSingleton;
import com.barrett.facedetectservice.error.LoggerService;
import com.barrett.facedetectservice.http.FaceMsgSocketPush;
import com.barrett.facedetectservice.service.DeviceIPMapService;
import com.barrett.facedetectservice.service.FaceDetectDataService;
import com.barrett.facedetectservice.service.UserDataService;
import com.barrett.facedetectservice.util.FaceDetectDeviceControl;
import com.barrett.facedetectservice.util.UniqueIDGenerator;
import com.barrett.facedetectservice.vm.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
//import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: smartsite-master
 * @description: RestController that handles all HKVision face detection related events
 * @author: Barrett
 * @create: 2020-05-09 10:09
 **/
@CrossOrigin
@RestController
@RequestMapping({"/FaceDetect"})
@Api(tags = {"人脸识别门禁后端接口"})
public class FaceDetectProcessController {
    @Autowired
    FaceDetectDeviceControl faceDetectDeviceControl;
    @Autowired
    FaceDetectDataService faceDetectDataService;
    @Autowired
    UserDataService userDataService;
    @Autowired
    DeviceIPMapService deviceIPMapService;
    @Autowired
    FaceMsgSocketPush faceMsgSocketPush;
    @Autowired
    LoggerService loggerService;
    @Value("${facecheck.timespan}")
    long FACE_CHECK_TIMESPAN;


    //********************* Update data related APIs **************************//

    /**
     * @Description: Update user info from external APIs
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "更新用户数据", notes = "从所有考勤打卡机获取最新的人员数据并更新数据库用户信息表")
    @RequestMapping(value = {"/UpdateUserInfo"}, method = {RequestMethod.GET})
    public int updateUserInfo() {
        System.out.println("Update User Info...");
        faceDetectDeviceControl.getAllUserInfoByCard();
        return 0;
    }

    /**
     * @Description: Get all user info from device and update user database by query from MIS database
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "更新用户数据", notes = "从基建MIS获取人员身份证信息，并从所有考勤打卡机获取最新的人员数据并更新数据库用户信息表")
    @RequestMapping(value = {"/UpdateUser"}, method = {RequestMethod.GET})
    public int updateUserData() {
        System.out.println("Update User Data...");
        faceDetectDeviceControl.getUserInfoFromMis();
        faceDetectDeviceControl.getAllUserInfoByCard();
        return 0;
    }


    /**
     * @Description: Update all devices information
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "更新设备信息", notes = "手动修改数据库中设备信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "deviceIPMaps", value = "设备信息数据", dataType = "List<DeviceIPMap>", paramType = "body", required = true)})
    @RequestMapping(value = {"/UpdateDeviceInfoByData"}, method = {RequestMethod.POST})
    public int updateDeviceInfoByData(@RequestBody List<DeviceIPMap> deviceIPMaps) {
        System.out.println("Update Device Information...");
        deviceIPMapService.deleteAll();
        for (DeviceIPMap thisMap : deviceIPMaps) {
            thisMap.setGuid(UniqueIDGenerator.getUUIDWithoutDash());
            deviceIPMapService.insert(thisMap);
        }
        return 1;
    }


    /**
     * @Description: Refresh the data and restart connection when device data changed, get device data from external APIs
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "更新设备信息", notes = "从设备模块接口获取最新的设备信息，并更新数据库")
    @RequestMapping(value = {"/UpdateDeviceInfo"}, method = {RequestMethod.GET})
    public int updateDeviceInfo() {
        System.out.println("Update Device Info...");
        faceDetectDeviceControl.getAllDeviceInfo();
        return 0;
    }

    /**
     * @Description: Restart Alarm
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "重新布防", notes = "更新各类数据，并重新对设备布防")
    @RequestMapping(value = {"/UpdateAndRestartAlarm"}, method = {RequestMethod.GET})
    public String updateAndRestartAlarm() {
        // Get current all device IP:
        List<String> currentDeviceIPs = deviceIPMapService.getAllDeviceIP();
        // Get all new devices and update database:
        faceDetectDeviceControl.getAllDeviceInfo();
        // Get all new device info:
        List<DeviceIPMap> newDevices = deviceIPMapService.getAll();
        // Login to and start alarm with those devices not in the original list:
        // Traverse all devices and login and setup alarm:
        for (DeviceIPMap thisIP : newDevices) {
            String userName = "";
            String password = "";
            if (currentDeviceIPs.contains(thisIP.getDevice_ip())) {
                continue;
            }
            if (thisIP.getUsername() != null) {
                userName = thisIP.getUsername();
            }
            if (thisIP.getPassword() != null) {
                password = thisIP.getPassword();
            }
            // Login to device and save login device handle:
            System.out.println(thisIP.getDevice_ip() + ":" + thisIP.getDevice_port());
            System.out.println(userName + "---" + password);
            faceDetectDeviceControl.userLogin(thisIP.getDevice_ip(), Integer.parseInt(thisIP.getDevice_port()), userName, password);
            DeviceHandleSingleton.getInstance().getlUserIDList().add(faceDetectDeviceControl.getlUserID());
            // Set up alarm callback methods and save alarm handle:
            faceDetectDeviceControl.writeLogFiles("布防：" + thisIP.getDevice_ip(), faceDetectDeviceControl.LOG_FILE_PATH);
            faceDetectDeviceControl.setupAlarmChan();
            DeviceHandleSingleton.getInstance().getlAlarmHandleList().add(faceDetectDeviceControl.getlUserID());
            // Query and update all user info:
            faceDetectDeviceControl.getAllUserInfoByCard();
            // Reinit login users and handles:
            faceDetectDeviceControl.dataInit();
        }
        return "更新数据成功！";
    }

    //***************Other Rest APIs ********************//

    /**
     * @Description: Check if a personnel had face detected
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "根据是否刷脸赋予访问权限", notes = "根据传入参数判断时候允许某个用户进入巡检界面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "刷脸打卡的时间戳", dataType = "long", paramType = "query", required = true),
            @ApiImplicitParam(name = "span", value = "允许刷脸的时间阈值", dataType = "long", paramType = "query", required = true),
            @ApiImplicitParam(name = "user", value = "用户的唯一标识-工号", dataType = "String", paramType = "query", required = true)
    })
    @RequestMapping(value = {"/PersonChecked"}, method = {RequestMethod.GET})
    public boolean getCheckedEntry(@RequestParam(value = "time", required = true) long time, @RequestParam(value = "span", required = true) long span,
                                   @RequestParam(value = "user", required = true) String user) {
        System.out.println("Query data to check if a person had go through a face detection device...");
        int dataSize = faceDetectDataService.getAllByTimeAndUser(time, span, user).size();
        if (dataSize >= 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @Description: Get all device information
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取所有设备信息", notes = "获取当前数据库中储存的所有设备信息")
    @RequestMapping(value = {"/GetDeviceInfo"}, method = {RequestMethod.GET})
    public List<DeviceIPMap> getDeviceInfo() {
        System.out.println("Get Device Information...");
        return deviceIPMapService.getAll();
    }

    /**
     * @Description: Check if a personnel had face detected at certain locations
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "根据是否刷脸赋予访问权限", notes = "根据传入参数判断时候允许某个用户进入巡检界面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entryCheckParams", value = "查询权限参数", dataType = "EntryCheckParams", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/PersonCheckedWithLocation"}, method = {RequestMethod.POST})
    public boolean getCheckedEntryWithLocation(@RequestBody EntryCheckParams entryCheckParams) {
        System.out.println("Query data to check if a person had go through a face detection device at certain places...");
        int dataSize = 0;
        // Data check:
        if (entryCheckParams.getTime() == 0) {
            entryCheckParams.setTime(System.currentTimeMillis());
        }
        if (entryCheckParams.getSpan() == 0) {
            entryCheckParams.setSpan(FACE_CHECK_TIMESPAN);
        }
        List<FaceDetectData> dataResult = faceDetectDataService.getAllPatrolByTimeAndUser(entryCheckParams.getTime(), entryCheckParams.getSpan(), entryCheckParams.getUser(), entryCheckParams.getDeviceSN());
        dataSize = dataResult.size();
        if (dataSize >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description: Get all user data from db:
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取所有用户", notes = "从当前数据库中获取所有用户信息")
    @RequestMapping(value = {"/AllUsers"}, method = {RequestMethod.GET})
    public List<UserOutput> getAllUsers() {
        System.out.println("Get all user data from database...");
        List<UserOutput> resultList = new ArrayList<>();
        List<UserData> tmpDataList = userDataService.getAll();
        for (UserData d : tmpDataList) {
            UserOutput thisData = new UserOutput();
            thisData.setWorkerId(d.getCard_no());
            thisData.setIdcard(d.getId_card());
            thisData.setStatus(1);
            resultList.add(thisData);
        }
        return resultList;
    }

    /**
     * @Description: Get checked data starting from a time
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取某时间点后所有刷脸信息", notes = "获取某时间点后所有刷脸信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "时间戳", dataType = "EntryCheckParams", paramType = "query", required = true)
    })
    @RequestMapping(value = {"/AllCheckedFaces"}, method = {RequestMethod.GET})
    public List<FaceDetectData> getAllCheckedFacesOrg(@RequestParam(value = "time", required = true) long time) {
        System.out.println("Get all org checked faces after a certain time from database...");
        return faceDetectDataService.getAllByTime(time);
    }

    /**
     * @Description: Get checked data starting from a time, API provided for others
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    @ApiOperation(value = "获取某时间点后所有刷脸信息", notes = "获取某时间点后所有刷脸信息并以指定格式输出，提供给其他模块使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "时间戳", dataType = "EntryCheckParams", paramType = "query", required = true)
    })
    @RequestMapping(value = {"/AllCheckedFacesOutput"}, method = {RequestMethod.GET})
    public List<FaceDetectOutput> getAllCheckedFaces(@RequestParam(value = "time", required = true) long time) {
        System.out.println("Get all checked faces after a certain time from database...");
        List<FaceDetectOutput> resultList = new ArrayList<>();
        List<FaceDetectData> tmpDataList = faceDetectDataService.getAllCheckinByTime(time);
        for (FaceDetectData d : tmpDataList) {
            FaceDetectOutput thisData = new FaceDetectOutput();
            thisData.setRecordTime(d.getTime_stamp());
            thisData.setDeviceSn(d.getDevice_ip());
            thisData.setWorkerId(d.getCard_no());
            resultList.add(thisData);
        }
        return resultList;
    }

    // Socket push test:
    @ApiOperation(value = "刷脸信息推送测试", notes = "刷脸信息推送测试")
    @RequestMapping(value = {"/faceInfoSocketPushTest"}, method = {RequestMethod.GET})
    public String faceInfoSocketPushTest() {
        String base64ImageTest = "data:image/jpg;base64,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";
        faceMsgSocketPush.pushWorkerMsg(base64ImageTest, "ZN6012", "周冰", "程序员", "勘测公司", "中南电力设计院有限公司", "2", "76");
        return "Msg pushed!";
    }

    // Socket push test:
    @ApiOperation(value = "获取当前在场人员总数", notes = "获取当前在场人员总数")
    @RequestMapping(value = {"/currentWorkerInSite"}, method = {RequestMethod.GET})
    public int getCurrentWorkerNumInSite() {
        return faceDetectDeviceControl.calculateNumOfWorkerInSite();
    }

    // Restful API availability test:
    @ApiOperation(value = "测试API可访问性")
    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "This is a test, the service is running";
    }

    // *** Web API for supermap worker module: ***
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息，并分页提供给别的模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfoQueryParams", value = "用户信息查询参数", dataType = "UserInfoQueryParams", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/userInfo"}, method = {RequestMethod.POST})
    public List<UserInfoReturnParams> getUserInfoByPage(@RequestBody UserInfoQueryParams userInfoQueryParams) {
        List<UserInfoReturnParams> resultList = new ArrayList<>();
        // Start pagination:
        PageHelper.startPage(userInfoQueryParams.getPage(), userInfoQueryParams.getSize());
        // Query data:
        List<UserData> userDataList = userDataService.getAll();
        // Apply pagination, after this, original user list will be modified, leaving only the result of the pagination
        PageInfo<UserData> pageInfo = new PageInfo<UserData>(userDataList);
        // Convert result:
        for (UserData thisData : userDataList) {
            UserInfoReturnParams tmpParams = new UserInfoReturnParams();
            tmpParams.setWorkerId(thisData.getId());
            tmpParams.setName(thisData.getUser_name());
            tmpParams.setIdcard(thisData.getId_card());
            tmpParams.setTimecard(thisData.getCard_no());
            tmpParams.setProjectId(1);
            tmpParams.setCooperatorId(1);
            tmpParams.setGroupId(1);
            tmpParams.setProfessionId(1);
            tmpParams.setCooperatorName("NA");
            tmpParams.setProfessionName("工人");
            tmpParams.setStatus((byte) 1);
            tmpParams.setModifyTime(new Date());
            resultList.add(tmpParams);
        }
        return resultList;
    }

    // *** Web API for supermap worker module: ***
    @ApiOperation(value = "获取考勤打卡记录", notes = "获取某人在某个时间点后的所有考勤记录，并分页提供给其他模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attendanceQueryParams", value = "考勤打卡记录查询参数", dataType = "AttendanceQueryParams", paramType = "body", required = true)
    })
    @RequestMapping(value = {"/attendanceInfo"}, method = {RequestMethod.POST})
    public List<AttendanceReturnParams> getUserInfoByPage(@RequestBody AttendanceQueryParams attendanceQueryParams) {
        List<AttendanceReturnParams> resultList = new ArrayList<>();
        // Start pagination:
        PageHelper.startPage(attendanceQueryParams.getPage(), attendanceQueryParams.getSize());
        // Query data:
        List<FaceDetectDataQuery> faceDetectDataList = faceDetectDataService.getFaceDetectInfo(attendanceQueryParams.getModifyTime());
        // Apply pagination, after this, original user list will be modified, leaving only the result of the pagination
        PageInfo<FaceDetectDataQuery> pageInfo = new PageInfo<FaceDetectDataQuery>(faceDetectDataList);
        // Convert result:
        for (FaceDetectDataQuery thisData : faceDetectDataList) {
            AttendanceReturnParams tmpParams = new AttendanceReturnParams();
            if (thisData.getWorkId() == null || thisData.getWorkId().size() == 0) {
                tmpParams.setWorkerId(0);
            } else {
                tmpParams.setWorkerId(thisData.getWorkId().get(0));
            }
            tmpParams.setProjectId(1);
            tmpParams.setPhoto(thisData.getPic_data());
            tmpParams.setRecordTime(thisData.getTime_stamp());
            if (thisData.getDevice_type() == null || thisData.getDevice_type().size() == 0) {
                continue;
            }
            tmpParams.setType((byte) (Integer.parseInt(thisData.getDevice_type().get(0))));
            tmpParams.setCardNo(thisData.getCard_no());
            resultList.add(tmpParams);
        }
        // Write log:
        loggerService.writeLogFile("AttendenceInfo", attendanceQueryParams.toString(), resultList.toString());
        return resultList;
    }


}
