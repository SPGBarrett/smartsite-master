package com.barrett.facedetectservice.service;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceIPMapService {
    int insert(DeviceIPMap deviceIPMap);

    int update(DeviceIPMap deviceIPMap);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<DeviceIPMap> getAllByGuid(@Param("guid") String paramGuid);

    List<DeviceIPMap> getAll();

    List<DeviceIPMap> getByIP(@Param("ip") String ip);

    List<String> getDeviceNoByIP(@Param("ip") String ip);

    List<String> getAllDeviceIP();

    List<DeviceIPMap> getByIpAndPort(@Param("ip") String ip, @Param("port") String port);
}
