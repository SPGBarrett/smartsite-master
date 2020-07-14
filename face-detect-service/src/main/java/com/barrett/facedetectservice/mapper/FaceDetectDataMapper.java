package com.barrett.facedetectservice.mapper;

import com.barrett.facedetectservice.bean.FaceDetectData;
import com.barrett.facedetectservice.vm.FaceDetectDataQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FaceDetectDataMapper {
    @Insert({"INSERT INTO face_detect_data(id,guid,time_stamp,alert_major_type,alert_minor_type,card_no,user_info,work_no,card_alert_type,device_no,device_ip,device_port,pic_data) VALUES (#{id},#{guid},#{time_stamp},#{alert_major_type},#{alert_minor_type},#{card_no},#{user_info},#{work_no},#{card_alert_type},#{device_no},#{device_ip},#{device_port},#{pic_data})"})
    int insert(FaceDetectData faceDetectData);

    @Update({"UPDATE face_detect_data SET guid=#{guid},time_stamp=#{time_stamp},alert_major_type=#{alert_major_type},alert_minor_type=#{alert_minor_type},card_no=#{card_no},user_info=#{user_info},work_no=#{work_no},card_alert_type=#{card_alert_type},device_no=#{device_no},device_ip=#{device_ip},device_port=#{device_port},pic_data=#{pic_data} WHERE id=#{id}"})
    int update(FaceDetectData faceDetectData);

    @Delete({"DELETE FROM face_detect_data WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM face_detect_data"})
    int deleteAll();

    @Select({"SELECT * FROM face_detect_data WHERE guid=#{guid}"})
    List<FaceDetectData> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT id,guid,time_stamp,alert_major_type,alert_minor_type,card_no,user_info,work_no,card_alert_type,device_no,device_ip,device_port,pic_data FROM face_detect_data"})
    List<FaceDetectData> getAll();

    @Select({"SELECT * FROM face_detect_data WHERE time_stamp > #{time}"})
    List<FaceDetectData> getAllByTime(@Param("time") long time);

    @Select({"SELECT * FROM face_detect_data WHERE time_stamp > (#{time} - #{span}) AND card_no=#{user}"})
    List<FaceDetectData> getAllByTimeAndUser(@Param("time") long time, @Param("span") long span, @Param("user") String user);

    @Select({"SELECT * FROM face_detect_data WHERE time_stamp > #{time} AND device_ip IN (SELECT device_ip FROM device_ip_map)"})
    List<FaceDetectData> getAllCheckinByTime(@Param("time") long time);

    @Select({"SELECT * FROM face_detect_data WHERE time_stamp > #{time} AND device_ip IN (SELECT device_ip FROM device_ip_map WHERE device_type = 1)"})
    List<FaceDetectData> getAllCheckinByTimeAndDevice(@Param("time") long time);

//    @Select({"SELECT * FROM face_detect_data WHERE time_stamp > (#{time} - #{span}) AND card_no=#{user} AND device_ip IN #{locations}"})
//    List<FaceDetectData> getAllPatrolByTimeAndUser(@Param("time") long time, @Param("span") long span, @Param("user") String user, @Param("locations") List<String> locations);

    // For processing list, can use this script or put a list of string into one single string with separation","
    @Select("<script>"
            + "SELECT * FROM face_detect_data WHERE time_stamp > (#{time} - #{span}) "
            + "AND card_no=#{user} "
            + "AND device_ip IN "
            + "<foreach item='item' index='index' collection='locations' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<FaceDetectData> getAllPatrolByTimeAndUser(@Param("time") long time, @Param("span") long span, @Param("user") String user, @Param("locations") List<String> locations);


    @Select({"SELECT id,guid,time_stamp,alert_major_type,alert_minor_type,card_no,user_info,work_no,card_alert_type,device_no,device_ip,device_port,pic_data FROM face_detect_data WHERE time_stamp > #{time_tag}"})
    @Results(id="getFaceDetectDataAll", value = {
            @Result(property = "device_ip", column = "device_ip"),
            @Result(property = "device_type", column = "device_ip", many = @Many(select = "com.barrett.facedetectservice.mapper.DeviceIPMapMapper.getDeviceNoByIP")),
            @Result(property = "card_no", column = "card_no"),
            @Result(property = "workId", column = "card_no", many = @Many(select = "com.barrett.facedetectservice.mapper.UserDataMapper.getIdByCardNo"))
    })
    List<FaceDetectDataQuery> getFaceDetectInfo(@Param("time_tag") String time_tag);

    @Select({"select a.* from face_detect_data as a where time_stamp = (select max(time_stamp) from face_detect_data where a.card_no=card_no)"})
    List<FaceDetectData> getMostRecentStatus();

    // Device_no is used to identify marking number of inside-device, here, entrance device_no = 1
    @Select({"select a.* from face_detect_data as a where time_stamp = (select max(time_stamp) from face_detect_data where a.card_no=card_no) and device_ip = (select device_ip from device_ip_map where device_no = #{device_no})"})
    List<FaceDetectData> getWorkerInSiteData(@Param("device_no") String device_no);


}
