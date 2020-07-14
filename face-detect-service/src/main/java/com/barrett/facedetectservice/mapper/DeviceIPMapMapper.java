package com.barrett.facedetectservice.mapper;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DeviceIPMapMapper {
    @Insert({"INSERT INTO device_ip_map(id,guid,device_ip,device_port,device_type,description,device_no,device_name,device_location,username,password,latitude,longitude) VALUES (#{id},#{guid},#{device_ip},#{device_port},#{device_type},#{description},#{device_no},#{device_name},#{device_location},#{username},#{password},#{latitude},#{longitude})"})
    int insert(DeviceIPMap deviceIPMap);

    @Update({"UPDATE device_ip_map SET guid=#{guid},device_ip=#{device_ip},device_port=#{device_port},device_type=#{device_type},description=#{description},device_no=#{device_no},device_name=#{device_name},device_location=#{device_location},username=#{username},password=#{password},latitude=#{latitude},longitude=#{longitude} WHERE id=#{id}"})
    int update(DeviceIPMap deviceIPMap);

    @Delete({"DELETE FROM device_ip_map WHERE guid=#{guid}"})
    int deleteByGuid(@Param("guid") int paramGuid);

    @Delete({"DELETE FROM device_ip_map"})
    int deleteAll();

    @Select({"SELECT * FROM device_ip_map WHERE guid=#{guid}"})
    List<DeviceIPMap> getAllByGuid(@Param("guid") String paramGuid);

    @Select({"SELECT id,guid,device_ip,device_port,device_type,description,device_no,device_name,device_location,username,password,latitude,longitude FROM device_ip_map"})
    List<DeviceIPMap> getAll();

    @Select({"SELECT * FROM device_ip_map WHERE device_ip=#{ip}"})
    List<DeviceIPMap> getByIP(@Param("ip") String ip);

    @Select({"SELECT device_type FROM device_ip_map WHERE device_ip=#{ip}"})
    List<String> getDeviceTypeByIP(@Param("ip") String ip);

    @Select({"SELECT device_no FROM device_ip_map WHERE device_ip=#{ip}"})
    List<String> getDeviceNoByIP(@Param("ip") String ip);

    @Select({"SELECT device_ip FROM device_ip_map"})
    List<String> getAllDeviceIP();
}
