package com.barrett.facedetectservice.service;

import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.vm.FaceDetectDataQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceDetectDataService {
    int insert(FaceDetectData faceDetectData);

    int update(FaceDetectData faceDetectData);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<FaceDetectData> getAllByGuid(@Param("guid") String paramGuid);

    List<FaceDetectData> getAll();

    List<FaceDetectData> getAllByTime(@Param("time") long time);

    List<FaceDetectData> getAllByTimeAndUser(@Param("time") long time, @Param("span") long span, @Param("user") String user);

    List<FaceDetectData> getAllCheckinByTime(@Param("time") long time);

    List<FaceDetectData> getAllCheckinByTimeAndDevice(@Param("time") long time);

    List<FaceDetectData> getAllPatrolByTimeAndUser(@Param("time") long time, @Param("span") long span, @Param("user") String user, @Param("locations") List<String> locations);

    List<FaceDetectDataQuery> getFaceDetectInfo(@Param("time_tag") String time_tag);

    List<FaceDetectData> getMostRecentStatus();

    List<FaceDetectData> getWorkerInSiteData(@Param("device_no") String device_no);

}
