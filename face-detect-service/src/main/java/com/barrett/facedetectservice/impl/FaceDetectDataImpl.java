package com.barrett.facedetectservice.impl;

import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.mapper.FaceDetectDataMapper;
import com.barrett.facedetectservice.service.FaceDetectDataService;
import com.barrett.facedetectservice.vm.AttendanceReturnParamsSc;
import com.barrett.facedetectservice.vm.FaceDetectDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-10 21:46
 **/
@Service
public class FaceDetectDataImpl implements FaceDetectDataService {
    @Autowired(required = false)
    FaceDetectDataMapper faceDetectDataMapper;

    @Override
    public int insert(FaceDetectData faceDetectData) {
        return faceDetectDataMapper.insert(faceDetectData);
    }

    @Override
    public int insertOrUpdate(FaceDetectData faceDetectData) {
        return faceDetectDataMapper.insertOrUpdate(faceDetectData);
    }

    @Override
    public int update(FaceDetectData faceDetectData) {
        return faceDetectDataMapper.update(faceDetectData);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return faceDetectDataMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return faceDetectDataMapper.deleteAll();
    }

    @Override
    public List<FaceDetectData> getAllByGuid(String paramGuid) {
        return faceDetectDataMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<FaceDetectData> getAll() {
        return faceDetectDataMapper.getAll();
    }

    @Override
    public List<FaceDetectData> getAllByTime(long time) {
        return faceDetectDataMapper.getAllByTime(time);
    }

    @Override
    public List<FaceDetectData> getAllByTimeSpan(long start_time, long end_time) {
        return null;
    }

    @Override
    public List<FaceDetectData> getAllByTimeAndUser(long time, long span, String user) {
        return faceDetectDataMapper.getAllByTimeAndUser(time, span, user);
    }

    @Override
    public List<FaceDetectData> getAllCheckinByTime(long time) {
        return faceDetectDataMapper.getAllCheckinByTime(time);
    }

    @Override
    public List<FaceDetectData> getAllCheckinByTimeSpan(long start_time, long end_time) {
        return faceDetectDataMapper.getAllCheckinByTimeSpan(start_time, end_time);
    }

    @Override
    public List<FaceDetectData> getAllCheckinByTimeAndDevice(long time) {
        return faceDetectDataMapper.getAllCheckinByTimeAndDevice(time);
    }

    @Override
    public List<FaceDetectData> getAllPatrolByTimeAndUser(long time, long span, String user, List<String> locations) {
        return faceDetectDataMapper.getAllPatrolByTimeAndUser(time, span, user, locations);
    }

    @Override
    public List<FaceDetectDataQuery> getFaceDetectInfo(String time_tag) {
        return faceDetectDataMapper.getFaceDetectInfo(time_tag);
    }

    @Override
    public List<FaceDetectData> getMostRecentStatus() {
        return faceDetectDataMapper.getMostRecentStatus();
    }

    @Override
    public List<FaceDetectData> getWorkerInSiteData(String device_no) {
        return faceDetectDataMapper.getWorkerInSiteData(device_no);
    }

    @Override
    public List<AttendanceReturnParamsSc> getAttendanceInfo(String time_tag) {
        return faceDetectDataMapper.getAttendanceInfo(time_tag);
    }
}
